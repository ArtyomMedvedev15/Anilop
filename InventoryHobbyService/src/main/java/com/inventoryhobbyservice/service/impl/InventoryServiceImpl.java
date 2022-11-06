package com.inventoryhobbyservice.service.impl;

import com.inventoryhobbyservice.domain.Inventory;
import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.*;
import com.inventoryhobbyservice.repository.InventoryInfoRepository;
import com.inventoryhobbyservice.repository.InventoryRepository;
import com.inventoryhobbyservice.service.api.InventoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryInfoRepository inventoryInfoRepository;
    private final WebClient.Builder webClientBuilder;
    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryInfoResponse addHobbyToInventory(InventoryInfoRequest inventoryInfoRequestDto, Long idInventory) {
        InventoryInfo inventoryInfoCheck = inventoryInfoRepository.findByUserIdAndHobbyId(inventoryInfoRequestDto.getHobbyInventoryId(),
                inventoryRepository.getByUserId(idInventory).getId());

        HobbyResponse hobbyResponse = webClientBuilder.build().get()
                .uri("http://hobbies-service/api/v1/hobby/"+inventoryInfoRequestDto.getHobbyInventoryId())
                .retrieve()
                .bodyToMono(HobbyResponse.class)
                .block();

        if (inventoryInfoCheck == null && hobbyResponse!=null) {
            InventoryInfo saveInventoryInfo = InventoryInfo.builder()
                    .hobby_id(inventoryInfoRequestDto.getHobbyInventoryId())
                    .user_inventory_id(inventoryRepository.getByUserId(idInventory).getId())
                    .created(new Date())
                    .serial_id(UUID.randomUUID())
                    .build();
            inventoryInfoRepository.save(saveInventoryInfo);
            log.info("{} Add hobby with name {} to inventory user {}", new Date(),hobbyResponse.getName(),saveInventoryInfo);
            return getInventoryInfoToDto(saveInventoryInfo);
        } else {
            log.warn("{} Hobby already in inventory this user" , new Date());
            return null;
        }
    }

    @Override
    public Boolean deleteInventory(UUID serial_id, Long userInventoryId) {
        Optional<Inventory> inventoryUserCheck = Optional.ofNullable(inventoryRepository.getByUserId(userInventoryId));
        if (inventoryUserCheck.isPresent()) {
            inventoryInfoRepository.deleteBySerialIdAndInventoryId(serial_id, inventoryRepository.getByUserId(userInventoryId).getId());
            log.info("{} Hobby with serial id {} was deleted",new Date(), serial_id);
            return true;
        } else {
            log.info("{} Inventory with id {} doesn't exists",new Date(), userInventoryId);
            return false;
        }
    }

    @Override
    public List<InventoryInfoResponse> findAllInventoryByUserInventoryId(Long userInventoryId) {
        Optional<Inventory> inventoryUserCheck = Optional.ofNullable(inventoryRepository.getByUserId(userInventoryId));
        if (inventoryUserCheck.isPresent()) {
            List<InventoryInfo> inventoryHobbiesUser = inventoryInfoRepository.findByUserInventoryId(inventoryRepository.getByUserId(userInventoryId).getId());
            log.info("{} Find all hobby in inventory for user with id {}",new Date(), userInventoryId);
            return inventoryHobbiesUser.stream().map(this::getInventoryInfoToDto).toList();
        } else {
            log.warn("{} Inventory with id {} doesn't exists",new Date(), userInventoryId);
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
            log.info("{} Create inventory for user with id {}",new Date(), inventoryCreateRequest.getUserId());
            return getInventoryToDto(inventoryRepository.save(inventoryDomain));
        } else {
            log.error("{} Inventory for user with id {} already created", new Date(),inventoryCreateRequest.getUserId());
            return null;
        }
    }

    private InventoryInfoResponse getInventoryInfoToDto(InventoryInfo info) {
        log.info("{} Map Inventory Info domain to Inventory info response DTO", new Date());
        return InventoryInfoResponse.builder()
                .id(info.getId())
                .userInventoryId(info.getUser_inventory_id())
                .hobbyInventoryId(info.getHobby_id())
                .serial_id(info.getSerial_id())
                .build();
    }

    private InventoryCreateResponse getInventoryToDto(Inventory inventory) {
        log.info("{} Map Inventory Info domain to Inventory info create respones DTO", new Date());
        return InventoryCreateResponse.builder()
                .id(inventory.getId())
                .userId(inventory.getUserId())
                .created(inventory.getCreated())
                .build();
    }


}
