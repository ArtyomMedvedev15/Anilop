package com.authtenticaionservice.repository;

 import com.authtenticaionservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findRoleByName(String namerole);
}
