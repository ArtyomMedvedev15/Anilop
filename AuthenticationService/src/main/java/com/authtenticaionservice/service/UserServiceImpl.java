package com.authtenticaionservice.service;

import com.authtenticaionservice.domain.AppUser;
import com.authtenticaionservice.domain.Role;
import com.authtenticaionservice.repository.RoleRepo;
 import com.authtenticaionservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user to the database");
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
