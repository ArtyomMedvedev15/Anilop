package com.inventoryhobbyservice.service;

import com.inventoryhobbyservice.domain.InventoryInfo;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryInfo addHobbyToInventory(InventoryInfo addInventoryHobby,Long idInventory);
    InventoryInfo deleteInventory(UUID serial_id, Long userInventoryId);
    List<InventoryInfo>findAllInventoryByUserInventoryId(Long userInventoryId);



}
