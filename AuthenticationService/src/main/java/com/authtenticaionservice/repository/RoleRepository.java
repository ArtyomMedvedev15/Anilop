package com.authtenticaionservice.repository;

 import com.authtenticaionservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findbyName(String namerole);
}
