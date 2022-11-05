package com.inventoryhobbyservice.service.impl;

import com.inventoryhobbyservice.domain.Inventory;
import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.dto.InventoryRequest;
import com.inventoryhobbyservice.dto.InventoryResponse;
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
    public InventoryInfo addHobbyToInventory(InventoryInfo addInventoryHobby,Long idInventory) {
        InventoryInfo inventoryInfoCheck = inventoryInfoRepository.findByUserIdAndHobbyId(addInventoryHobby.getHobby_id(),
                inventoryRepository.getByUserId(idInventory).getId());
        if(inventoryInfoCheck==null){
            addInventoryHobby.setUser_inventory_id(inventoryRepository.getByUserId(idInventory).getId());
            addInventoryHobby.setCreated(new Date());
            addInventoryHobby.setSerial_id(UUID.randomUUID());
            inventoryInfoRepository.save(addInventoryHobby);
            log.info("Add hobby to inventory user {}",addInventoryHobby);
            return addInventoryHobby;
        }else {
            log.warn("Hobby already in inventory this user");
            return null;
        }
    }

    @Override
    public Boolean deleteInventory(UUID serial_id, Long userInventoryId) {
        Optional<Inventory> inventoryCheck = Optional.ofNullable(inventoryRepository.getByUserId(userInventoryId));
        if(inventoryCheck.isPresent()) {
            inventoryInfoRepository.deleteBySerialIdAndInventoryId(serial_id,inventoryRepository.getByUserId(userInventoryId).getId());
            log.info("Hobby with serial id {} was deleted",serial_id);
            return true;
        }else {
            log.info("Inventory with id {} doesn't exists",userInventoryId);
            return false;
        }
    }

    @Override
    public List<InventoryInfoResponse> findAllInventoryByUserInventoryId(Long userInventoryId) {
        Optional<Inventory> inventoryCheck = Optional.ofNullable(inventoryRepository.getByUserId(userInventoryId));
        if(inventoryCheck.isPresent()) {
            List<InventoryInfo> inventoryInfoList = inventoryInfoRepository.findByUserInventoryId(inventoryRepository.getByUserId(userInventoryId).getId());
            log.info("Find all hobby in inventory for user with id {}",userInventoryId);
            return inventoryInfoList.stream().map(this::getInventoryInfoToDto).toList();
        }else{
            log.warn("Inventory with id {} doesn't exists",userInventoryId);
            return null;
        }
    }

    @Override
    public InventoryResponse createUserInventory(InventoryRequest inventoryRequest) {
        Optional<Inventory> inventoryIsExists = Optional.ofNullable(inventoryRepository.findByUserId(inventoryRequest.getUserId()));
        if(inventoryIsExists.isEmpty()){
            Inventory inventoryDomain = Inventory.builder()
                    .userId(inventoryRequest.getUserId())
                    .created(new Date())
                    .build();
            log.info("Create inventory for user with id {}",inventoryRequest.getUserId());
            return getInventoryToDto(inventoryRepository.save(inventoryDomain));
        }else {
            log.error("Inventory for user with id {} already created",inventoryRequest.getUserId());
            return null;
        }
    }

    private InventoryInfoResponse getInventoryInfoToDto(InventoryInfo info){
        return InventoryInfoResponse.builder()
                .id(info.getId())
                .userInventoryId(info.getUser_inventory_id())
                .hobbyInventoryId(info.getHobby_id())
                .serial_id(info.getSerial_id())
                .build();
    }

    private InventoryResponse getInventoryToDto(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .userId(inventory.getUserId())
                .created(inventory.getCreated())
                .build();
    }


}
