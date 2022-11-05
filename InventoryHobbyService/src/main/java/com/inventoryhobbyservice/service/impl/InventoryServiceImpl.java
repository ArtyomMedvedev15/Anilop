package com.inventoryhobbyservice.service.impl;

import com.inventoryhobbyservice.domain.Inventory;
import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoRequest;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.dto.InventoryCreateRequest;
import com.inventoryhobbyservice.dto.InventoryCreateResponse;
import com.inventoryhobbyservice.repository.InventoryInfoRepository;
import com.inventoryhobbyservice.repository.InventoryRepository;
import com.inventoryhobbyservice.service.api.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private InventoryInfoRepository inventoryInfoRepository;

    private InventoryRepository inventoryRepository;

    @Override
    public InventoryInfoResponse addHobbyToInventory(InventoryInfoRequest inventoryInfoRequestDto, Long idInventory) {
        InventoryInfo inventoryInfoCheck = inventoryInfoRepository.findByUserIdAndHobbyId(inventoryInfoRequestDto.getHobbyInventoryId(),
                inventoryRepository.getByUserId(idInventory).getId());
        if (inventoryInfoCheck == null) {
            InventoryInfo saveInventoryInfo = InventoryInfo.builder()
                    .hobby_id(inventoryInfoRequestDto.getHobbyInventoryId())
                    .user_inventory_id(inventoryRepository.getByUserId(idInventory).getId())
                    .created(new Date())
                    .serial_id(UUID.randomUUID())
                    .build();
            inventoryInfoRepository.save(saveInventoryInfo);
            log.info("Add hobby to inventory user {}", saveInventoryInfo);
            return getInventoryInfoToDto(saveInventoryInfo);
        } else {
            log.warn("Hobby already in inventory this user");
            return null;
        }
    }

    @Override
    public Boolean deleteInventory(UUID serial_id, Long userInventoryId) {
        Optional<Inventory> inventoryCheck = Optional.ofNullable(inventoryRepository.getByUserId(userInventoryId));
        if (inventoryCheck.isPresent()) {
            inventoryInfoRepository.deleteBySerialIdAndInventoryId(serial_id, inventoryRepository.getByUserId(userInventoryId).getId());
            log.info("Hobby with serial id {} was deleted", serial_id);
            return true;
        } else {
            log.info("Inventory with id {} doesn't exists", userInventoryId);
            return false;
        }
    }

    @Override
    public List<InventoryInfoResponse> findAllInventoryByUserInventoryId(Long userInventoryId) {
        Optional<Inventory> inventoryCheck = Optional.ofNullable(inventoryRepository.getByUserId(userInventoryId));
        if (inventoryCheck.isPresent()) {
            List<InventoryInfo> inventoryInfoList = inventoryInfoRepository.findByUserInventoryId(inventoryRepository.getByUserId(userInventoryId).getId());
            log.info("Find all hobby in inventory for user with id {}", userInventoryId);
            return inventoryInfoList.stream().map(this::getInventoryInfoToDto).toList();
        } else {
            log.warn("Inventory with id {} doesn't exists", userInventoryId);
            return null;
        }
    }

    @Override
    public InventoryCreateResponse createUserInventory(InventoryCreateRequest inventoryCreateRequest) {
        Optional<Inventory> inventoryIsExists = Optional.ofNullable(inventoryRepository.findByUserId(inventoryCreateRequest.getUserId()));
        if (inventoryIsExists.isEmpty()) {
            Inventory inventoryDomain = Inventory.builder()
                    .userId(inventoryCreateRequest.getUserId())
                    .created(new Date())
                    .build();
            log.info("Create inventory for user with id {}", inventoryCreateRequest.getUserId());
            return getInventoryToDto(inventoryRepository.save(inventoryDomain));
        } else {
            log.error("Inventory for user with id {} already created", inventoryCreateRequest.getUserId());
            return null;
        }
    }

    private InventoryInfoResponse getInventoryInfoToDto(InventoryInfo info) {
        return InventoryInfoResponse.builder()
                .id(info.getId())
                .userInventoryId(info.getUser_inventory_id())
                .hobbyInventoryId(info.getHobby_id())
                .serial_id(info.getSerial_id())
                .build();
    }

    private InventoryCreateResponse getInventoryToDto(Inventory inventory) {
        return InventoryCreateResponse.builder()
                .id(inventory.getId())
                .userId(inventory.getUserId())
                .created(inventory.getCreated())
                .build();
    }


}
