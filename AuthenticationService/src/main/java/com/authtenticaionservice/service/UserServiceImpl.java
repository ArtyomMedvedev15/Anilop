package com.authtenticaionservice.service;

import com.authtenticaionservice.domain.AppUser;
import com.authtenticaionservice.domain.Role;
import com.authtenticaionservice.repository.RoleRepo;
 import com.authtenticaionservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if(user==null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found inn the database");
        }else{
            log.info("User found in the database:{}",username);
        }
        Collection<SimpleGrantedAuthority>authorities = new ArrayList<>();
        user.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(username,user.getPassword(),authorities);
    }
    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user to the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role to the database");
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser findUser = userRepo.findByUsername(username);
        Role role = roleRepo.findRoleByName(roleName);
        findUser.getRoles().add(role);
        log.info("Add role {} to the user with {}",roleName,username);

    }

    @Override
    public AppUser getUser(String username) {
        log.info("Get user with username - {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Get all user");
        return userRepo.findAll();
    }


}
