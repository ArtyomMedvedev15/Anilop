package com.inventoryhobbyservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryhobbyservice.domain.InventoryInfo;
import com.inventoryhobbyservice.dto.InventoryInfoRequest;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.repository.InventoryInfoRepository;
import com.inventoryhobbyservice.repository.InventoryRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class InventoryServiceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryInfoRepository inventoryInfoRepository;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:9.6.18-alpine")
            .withDatabaseName("prop")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(5432)
            .withInitScript("sql/init.sql");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgreSQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "postgres");
    }

    @Test
    void addHobbyToInventory() throws Exception {
        InventoryInfoRequest inventoryInfoResponse = getInventoryInfoToDtoReq();
        String hobby_request_json = objectMapper.writeValueAsString(inventoryInfoResponse);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/inventory/addtoinventory/777/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isCreated());
        assertEquals(1, inventoryInfoRepository.findByUserInventoryId(777L).size());
    }

    @Test
    void deleteInventory() {
    }

    @Test
    void findAllInventoryByUserInventoryId() {
    }

    private InventoryInfoResponse getInventoryInfoToDtoRes(){
        return InventoryInfoResponse.builder()
                .id(123L)
                .userInventoryId(777L)
                .hobbyInventoryId(123L)
                .serial_id(UUID.randomUUID())
                .build();
    }

    private InventoryInfoRequest getInventoryInfoToDtoReq(){
        return InventoryInfoRequest.builder()
                .userInventoryId(777L)
                .hobbyInventoryId(123L)
                .build();
    }
}