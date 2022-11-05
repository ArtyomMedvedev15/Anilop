package com.inventoryhobbyservice.rest;

import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.*;
import com.inventoryhobbyservice.service.api.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
@Slf4j
public class InventoryHobbyController {

    private InventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<InventoryCreateResponse>createUserInventory(@RequestBody InventoryCreateRequest inventoryCreateRequest){
        InventoryCreateResponse inventoryCreateResponse = inventoryService.createUserInventory(inventoryCreateRequest);
        if(inventoryCreateResponse !=null) {
            log.info("{} Create inventory for user with id {} with endpoint {}",new Date(), inventoryCreateRequest.getUserId(),"/create");
            return ResponseEntity.ok().body(inventoryCreateResponse);
        }else{
            log.error("{} Invetory for user with id {} already created with endpoint {}", new Date(),inventoryCreateRequest.getUserId(),"/create");
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/addtoinventory/{userId}/{idhobby}")
    public ResponseEntity<InventoryInfoResponse>addHobbyToInventory(@PathVariable("userId")Long userId,
                                                            @PathVariable("idhobby")Long idhobby){
        InventoryInfoRequest inventoryInfoFromDto = InventoryInfoRequest.builder()
                .userInventoryId(userId)
                .hobbyInventoryId(idhobby)
                .build();

        InventoryInfoResponse inventoryInfoResponse = inventoryService.addHobbyToInventory(inventoryInfoFromDto,userId);

        if(inventoryInfoResponse!=null) {
            log.info("{} Add hobby to inventory with id user {} with endpoint {}",new Date(),userId,"/addtoinventory");
            return ResponseEntity.ok().body(inventoryInfoResponse);
        }else{
            log.error("{} This hobby already in user id {} with endpoint {}",new Date(),userId,"/addtoinventory");
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<InventoryInfoResponse>>findAllInventoryInfoByUserId(@PathVariable("userid")Long userId){
       List<InventoryInfoResponse> hobbyInventories = inventoryService.findAllInventoryByUserInventoryId(userId);
        if(hobbyInventories!=null){
            log.info("{} Find all hobby in inventory user with id {} with endpoint {}",new Date(),userId,"/{userid}");
            return ResponseEntity.ok().body(hobbyInventories);
        }else {
            log.error("{} Inventory with id {} doesn't exists with endpoint {}",new Date(),userId,"/{userid}");
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void>deleteInventoryInfo(@RequestBody InventoryInfoDeleteRequest inventoryInfoDeleteRequest){
        Boolean isDelete = inventoryService.deleteInventory(inventoryInfoDeleteRequest.getSerial_id(), inventoryInfoDeleteRequest.getUserId());
        if(isDelete) {
            log.warn("{} Hobby with id {} was delete from inventory with endpoint {}",new Date(),inventoryInfoDeleteRequest.getSerial_id(),"/delete");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            log.error("{} Inventory with id {} doesn't exists {}",new Date(),inventoryInfoDeleteRequest.getUserId(),"/delete");
            return ResponseEntity.badRequest().body(null);
        }
    }

}
