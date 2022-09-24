package com.authtenticaionservice;

import com.authtenticaionservice.domain.AppUser;
import com.authtenticaionservice.domain.Role;
import com.authtenticaionservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class,args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(UserService userService){
        return args -> {
            userService.saveRole(new Role(null,"ROLE_USER"));
            userService.saveRole(new Role(null,"ROLE_MANAGER"));
            userService.saveRole(new Role(null,"ROLE_ADMIN"));
            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));

            userService.saveUser(AppUser.builder()
                    .id(null)
                    .name("Tim")
                    .username("Tim01")
                    .password("12345678")
                    .roles(new ArrayList<>()).build());
            userService.saveUser(AppUser.builder()
                    .id(null)
                    .name("Jon")
                    .username("Jon01")
                    .password("12345678")
                    .roles(new ArrayList<>()).build());

            userService.addRoleToUser("Tim01","ROLE_USER");
            userService.addRoleToUser("Jon01","ROLE_ADMIN");
        };
    }
}