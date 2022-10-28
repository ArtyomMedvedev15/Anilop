package com.inventoryhobbyservice.service;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.repository.InventoryInfoRepository;
import com.inventoryhobbyservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<InventoryInfo> findAllInventoryByUserInventoryId(Long userInventoryId) {
        return null;
    }
}
