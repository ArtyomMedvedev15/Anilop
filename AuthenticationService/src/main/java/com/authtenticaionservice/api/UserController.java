package com.authtenticaionservice.api;

import com.authtenticaionservice.domain.AppUser;
import com.authtenticaionservice.domain.Role;
import com.authtenticaionservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<AppUser>saveUser(@RequestBody AppUser user){
        URI uri_save_user = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri_save_user).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role){
        URI uri_save_role = URI.create(ServletUriComponentsBuilder.fromCurrentRequestUri().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri_save_role).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addroleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @Data
    class RoleToUserForm{
        private String username;
        private String roleName;
    }

}
