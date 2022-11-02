package com.inventoryhobbyservice.service.api;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryInfo addHobbyToInventory(InventoryInfo addInventoryHobby,Long idInventory);
    Boolean deleteInventory(UUID serial_id, Long userInventoryId);
    List<InventoryInfoResponse>findAllInventoryByUserInventoryId(Long userInventoryId);



}
