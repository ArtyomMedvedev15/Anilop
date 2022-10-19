package com.hobbiesservice.repository;

import com.hobbiesservice.domain.Hobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {
}
