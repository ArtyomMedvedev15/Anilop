package com.inventoryhobbyservice.repository;

import com.inventoryhobbyservice.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Inventory findByUserId(Long userId);

    @Query("SELECT iv FROM Inventory iv WHERE iv.userId=:userId")
    Inventory getByUserId(@Param("userId") Long userId);
}
