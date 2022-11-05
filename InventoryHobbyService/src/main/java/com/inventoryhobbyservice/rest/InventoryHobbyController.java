package com.inventoryhobbyservice.rest;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.*;
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
        InventoryResponse inventoryResponse = inventoryService.createUserInventory(inventoryRequest);
        if(inventoryResponse!=null) {
            log.info("Create inventory for user with id {} with endpoint",inventoryRequest.getUserId());
            return ResponseEntity.ok().body(inventoryResponse);
        }else{
            log.error("Invetory for user with id {} already created",inventoryRequest.getUserId());
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/addtoinventory/{userId}/{idhobby}")
    public ResponseEntity<InventoryInfo>addHobbyToInventory(@PathVariable("userId")Long userId,
                                                            @PathVariable("idhobby")Long idhobby){
        InventoryInfo inventoryInfoFromDto = InventoryInfo.builder()
                .user_inventory_id(userId)
                .hobby_id(idhobby)
                .build();
        InventoryInfo inventoryInfoResponse = inventoryService.addHobbyToInventory(inventoryInfoFromDto,userId);

        if(inventoryInfoResponse!=null) {
            log.info("Add hobby to inventory with id user{}",userId);
            return ResponseEntity.ok().body(inventoryInfoResponse);
        }else{
            log.error("This hobby already in user id {}",userId);
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
        Boolean isDelete = inventoryService.deleteInventory(inventoryInfoDeleteRequest.getSerial_id(), inventoryInfoDeleteRequest.getUserId());
        if(isDelete) {
            log.warn("Hobby with id {} was delete from inventory",inventoryInfoDeleteRequest.getSerial_id());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            log.error("Inventory with id {} doesn't exists",inventoryInfoDeleteRequest.getUserId());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
