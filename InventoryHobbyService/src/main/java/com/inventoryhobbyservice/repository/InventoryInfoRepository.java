package com.inventoryhobbyservice.repository;

import com.inventoryhobbyservice.domain.InventoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InventoryInfoRepository extends JpaRepository<InventoryInfo, Long> {
    @Query("select ih from InventoryInfo ih where ih.user_inventory_id=:user_inventory_id and ih.hobby_id=:hobby_id")
    InventoryInfo findByUserIdAndSerialId(@Param("hobby_id") Long hobby_id, @Param("user_inventory_id") Long user_inventory_id);

    @Query("select ih from InventoryInfo ih where ih.serial_id=:serial_id")
    InventoryInfo findBySerialId(@Param("serial_id") UUID serial_id);

    @Query("select ih from InventoryInfo ih where ih.user_inventory_id=:user_inventory_id")
    List<InventoryInfo>findByUserInventoryId(@Param("user_inventory_id") Long user_inventory_id);
    @Modifying
    @Query("delete from InventoryInfo where serial_id=:serial_id")
    void deleteBySerialId(@Param("serial_id") UUID serial_id);

}
