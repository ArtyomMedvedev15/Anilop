package com.hobbiesservice.repository;

import com.hobbiesservice.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {
        Hobby findHobbyByName(String name);
}
