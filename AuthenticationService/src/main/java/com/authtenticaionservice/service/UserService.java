package com.authtenticaionservice.service;

import com.authtenticaionservice.domain.AppUser;
import com.authtenticaionservice.domain.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser>getUsers();
}
