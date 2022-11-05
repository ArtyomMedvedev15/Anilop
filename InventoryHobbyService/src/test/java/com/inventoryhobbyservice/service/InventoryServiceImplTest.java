package com.inventoryhobbyservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryhobbyservice.dto.InventoryInfoDeleteRequest;
import com.inventoryhobbyservice.dto.InventoryInfoRequest;
import com.inventoryhobbyservice.dto.InventoryInfoResponse;
import com.inventoryhobbyservice.dto.InventoryRequest;
import com.inventoryhobbyservice.repository.InventoryInfoRepository;
import com.inventoryhobbyservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
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
@Sql(value = "classpath:/sql/initData.sql")
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
    void AddHobbyToInventoryUser_WithStatus200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/inventory/addtoinventory/777/124")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        assertEquals(2, inventoryInfoRepository.findByUserInventoryId(11L).size());
    }

    @Test
    void AddHobbyToInventoryUser_WithStatus400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/inventory/addtoinventory/777/123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        assertEquals(2, inventoryInfoRepository.findByUserInventoryId(11L).size());
    }

    @Test
    void FindAllInventoryByUserInventoryId_WithStatus200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/inventory/778")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(1, inventoryInfoRepository.findByUserInventoryId(2L).size());
    }

    @Test
    void FindAllInventoryByUserInventoryId_WithStatus400() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/inventory/8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        assertEquals(0, inventoryInfoRepository.findByUserInventoryId(78L).size());
    }

    @Test
    void DeleteHobbyFromInventoryUser_WithStatusNoContent() throws Exception {
        InventoryInfoDeleteRequest inventoryInfoDeleteRequest = InventoryInfoDeleteRequest.builder()
                .userId(3L)
                .serial_id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .build();
        String hobby_request_json = objectMapper.writeValueAsString(inventoryInfoDeleteRequest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/inventory/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isNoContent());
        assertEquals(1, inventoryInfoRepository.findByUserInventoryId(3L).size());
    }

    @Test
    void DeleteHobbyFromInventoryUser_WithStatusBadRequest() throws Exception {
        InventoryInfoDeleteRequest inventoryInfoDeleteRequest = InventoryInfoDeleteRequest.builder()
                .userId(413L)
                .serial_id(UUID.fromString("a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12"))
                .build();
        String hobby_request_json = objectMapper.writeValueAsString(inventoryInfoDeleteRequest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/v1/inventory/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isBadRequest());
    }


    @Test
    void CreateUserInventory_WithStatus200() throws Exception {
        InventoryRequest inventoryRequest = InventoryRequest.builder()
                .userId(3L)
                .build();
        String hobby_request_json = objectMapper.writeValueAsString(inventoryRequest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/inventory/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isOk());
        assertNotNull(inventoryRepository.getByUserId(3L));
    }

    @Test
    void CreateUserInventory_WithStatus400() throws Exception {
        InventoryRequest inventoryRequest = InventoryRequest.builder()
                .userId(777L)
                .build();
        String hobby_request_json = objectMapper.writeValueAsString(inventoryRequest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/inventory/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isBadRequest());
     }

}