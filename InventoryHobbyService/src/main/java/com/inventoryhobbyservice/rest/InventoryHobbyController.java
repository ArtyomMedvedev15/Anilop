package com.inventoryhobbyservice.rest;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.*;
import com.inventoryhobbyservice.repository.InventoryRepository;
import com.inventoryhobbyservice.service.api.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
@Slf4j
public class InventoryHobbyController {

    private InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<InventoryResponse>createUserInventory(@RequestBody InventoryRequest inventoryRequest){
        InventoryResponse inventoryResponse = InventoryService.createUserInventory(inventoryRequest);
        return ResponseEntity.ok().body();
    }


    @GetMapping("/addtoinventory/{idinventory}/{idhobby}")
    public ResponseEntity<InventoryInfo>addHobbyToInventory(@PathVariable("idinventory")Long idinventory,
                                                            @PathVariable("idhobby")Long idhobby){
        InventoryInfo inventoryInfoFromDto = InventoryInfo.builder()
                .user_inventory_id(idinventory)
                .hobby_id(idhobby)
                .build();
        InventoryInfo inventoryInfoResponse = inventoryService.addHobbyToInventory(inventoryInfoFromDto,idinventory);

        if(inventoryInfoResponse!=null) {
            log.info("Add hobby to inventory with id inventory{}",idinventory);
            return ResponseEntity.ok().body(inventoryInfoResponse);
        }else{
            log.error("This hobby already in inventory {}",idinventory);
            return ResponseEntity.badRequest().body(null);

        }
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<InventoryInfoResponse>>findAllInventoryInfoByUserId(@PathVariable("userid")Long userId){
       List<InventoryInfoResponse> hobbyListInventory = inventoryService.findAllInventoryByUserInventoryId(userId);
        if(hobbyListInventory!=null){
            log.info("Find all hobby in inventory user with id {}",userId);
            return ResponseEntity.ok().body(hobbyListInventory);
        }else {
            log.error("Inventory with id {} doesn't exists",userId);
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void>deleteInventoryInfo(@RequestBody InventoryInfoDeleteRequest inventoryInfoDeleteRequest){
        Boolean isDelete = inventoryService.deleteInventory(inventoryInfoDeleteRequest.getSerial_id(), inventoryInfoDeleteRequest.getUserInventoryId());
        if(isDelete) {
            log.warn("Hobby with id {} was delete from inventory",inventoryInfoDeleteRequest.getSerial_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            log.error("Inventory with id {} doesn't exists",inventoryInfoDeleteRequest.getUserInventoryId());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
