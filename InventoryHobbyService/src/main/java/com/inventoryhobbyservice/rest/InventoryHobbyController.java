package com.inventoryhobbyservice.rest;

import com.inventoryhobbyservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryHobbyController {

    private InventoryService inventoryService;

    @GetMapping("/verify/{sku_code}")
    public ResponseEntity<Boolean> IsInStock(@PathVariable("sku_code")String skuCode){
        return ResponseEntity.ok().body(inventoryService.IsInStock(skuCode));
    }

}
