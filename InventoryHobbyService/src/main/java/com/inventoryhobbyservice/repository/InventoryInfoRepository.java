package com.inventoryhobbyservice.repository;

import com.inventoryhobbyservice.domain.InventoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryInfoRepository extends JpaRepository<InventoryInfo,Long> {
    InventoryInfo findByUserInventoryIdAndHobbyInventoryId(Long userInventoryId,Long hobbyInventoryId);
}
