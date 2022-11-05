package com.hobbiesservice.repository;

import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby,Long> {
        @Query(value = "SELECT h FROM Hobby h WHERE h.name LIKE CONCAT('%',:name,'%')")
        List<Hobby> findHobbyByName(@Param("name")String name);
        List<Hobby>findByType(Type type);

        @Query("SELECT h FROM Hobby h WHERE h.author_id = :author_id")
        List<Hobby>findByAuthorId(@Param("author_id") Long author_id);
}