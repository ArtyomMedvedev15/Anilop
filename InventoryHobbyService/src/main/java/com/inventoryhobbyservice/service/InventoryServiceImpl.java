package com.inventoryhobbyservice.service;

import com.inventoryhobbyservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean IsInStock(String skuCode) {
        return true;

    }
}
