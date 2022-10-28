package com.inventoryhobbyservice.service;

import com.inventoryhobbyservice.domain.Inventory;
import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.repository.InventoryInfoRepository;
import com.inventoryhobbyservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService{

    private InventoryInfoRepository inventoryInfoRepository;

    private InventoryRepository inventoryRepository;

    @Override
    public InventoryInfo addHobbyToInventory(InventoryInfo addInventoryHobby,Long idInventory) {
        InventoryInfo inventoryInfoCheck = inventoryInfoRepository.findByUserInventoryIdAndHobbyInventoryId(addInventoryHobby.getUserInventoryId(),addInventoryHobby.getHobbyInventoryId());
        if(inventoryInfoCheck==null){
            addInventoryHobby.setSerial_id(UUID.randomUUID());
            inventoryInfoRepository.save(addInventoryHobby);
            inventoryRepository.getById(idInventory).getHobbylist().add(addInventoryHobby);
            log.info("Add hobby to inventory user {}",addInventoryHobby);
            return addInventoryHobby;
        }else {
            log.warn("Hobby already in inventory this user");
            return null;
        }
    }

    @Override
    public InventoryInfo deleteInventory(UUID serial_id, Long userInventoryId) {
        return null;
    }

    @Override
    public List<InventoryInfoResponse> findAllInventoryByUserInventoryId(Long userInventoryId) {
        Optional<Inventory> inventoryCheck = Optional.of(inventoryRepository.getById(userInventoryId));
        if(inventoryCheck.isPresent()) {
            List<InventoryInfo> inventoryInfoList = inventoryRepository.getById(userInventoryId).getHobbylist();
            log.info("Find all hobby in inventory for user with id {}",userInventoryId);
            return inventoryInfoList.stream().map(this::getInventoryInfoToDto).toList();
        }else{
            log.warn("Inventory with id {} doesn't exists",userInventoryId);
            return null;
        }
    }

    private InventoryInfoResponse getInventoryInfoToDto(InventoryInfo info){
        return InventoryInfoResponse.builder()
                .id(info.getId())
                .userInventoryId(info.getUserInventoryId())
                .hobbyInventoryId(info.getHobbyInventoryId())
                .serial_id(info.getSerial_id())
                .build();
    }
}
