package com.inventoryhobbyservice.service.api;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoRequest;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.dto.InventoryCreateRequest;
import com.inventoryhobbyservice.dto.InventoryCreateResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryInfoResponse addHobbyToInventory(InventoryInfoRequest inventoryInfoRequestDto, Long idInventory);
    Boolean deleteInventory(UUID serial_id, Long userInventoryId);
    List<InventoryInfoResponse>findAllInventoryByUserInventoryId(Long userInventoryId);

    InventoryCreateResponse createUserInventory(InventoryCreateRequest inventoryCreateRequest);


}
