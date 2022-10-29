package com.inventoryhobbyservice.rest;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoDeleteRequest;
import com.inventoryhobbyservice.dto.InventoryInfoRequest;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryHobbyController {

    private InventoryService inventoryService;

    @GetMapping("/verify/{sku_code}")
    public ResponseEntity<Boolean> IsInStock(@PathVariable("sku_code")String skuCode){
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/addtoinventory/{idinventory}")
    public ResponseEntity<InventoryInfo>addHobbyToInventory(@PathVariable("idinventory")Long idinventory,
                                                            @RequestBody InventoryInfoRequest inventoryInfoRequest){
        InventoryInfo inventoryInfoFromDto = InventoryInfo.builder()
                .user_inventory_id(inventoryInfoRequest.getUserInventoryId())
                .hobby_id(inventoryInfoRequest.getHobbyInventoryId())
                .build();
        InventoryInfo inventoryInfoResponse = inventoryService.addHobbyToInventory(inventoryInfoFromDto,idinventory);

        return ResponseEntity.ok().body(inventoryInfoResponse);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<InventoryInfoResponse>>findAllInventoryInfoByUserId(@PathVariable("userid")Long userId){
        return ResponseEntity.ok().body(inventoryService.findAllInventoryByUserInventoryId(userId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void>deleteInventoryInfo(@RequestBody InventoryInfoDeleteRequest inventoryInfoDeleteRequest){
        inventoryService.deleteInventory(inventoryInfoDeleteRequest.getSerial_id(),inventoryInfoDeleteRequest.getUserInventoryId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
