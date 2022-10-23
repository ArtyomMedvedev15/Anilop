package com.hobbiesservice.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbiesservice.domain.Hobby;
import com.hobbiesservice.domain.Status;
import com.hobbiesservice.domain.Type;
import com.hobbiesservice.dto.HobbyRequest;
import com.hobbiesservice.dto.HobbyResponse;
import com.hobbiesservice.repository.HobbyRepository;
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

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class HobbyServiceImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HobbyRepository hobbyRepository;

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
    void CreateHobby_WithStatus_201() throws Exception {
       HobbyRequest hobbyRequestTest =  getHobbyRequest();
       String hobby_request_json = objectMapper.writeValueAsString(hobbyRequestTest);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/hobby/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(hobby_request_json))
                .andExpect(status().isCreated());
        assertEquals(2, hobbyRepository.findAll().size());
    }



    @Test
    void UpdateHobby_WithStatus_200() throws Exception {
        HobbyRequest hobbyRequestTest =  getHobbyRequest();
        hobbyRequestTest.setName("Updated name");
        String hobby_request_json = objectMapper.writeValueAsString(hobbyRequestTest);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/hobby/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isOk());
        assertEquals("Updated name", hobbyRepository.findHobbyByName("Updated name").getName());
    }

    @Test
    void GetHobbyById_WithStatus_200() throws Exception {
        Hobby hobbyResponse = hobbyRepository.findHobbyByName("Temp");
        System.out.println("HOBB22" + hobbyResponse);
        String hobby_request_json = objectMapper.writeValueAsString(hobbyResponse);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/hobby/7777")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hobby_request_json))
                .andExpect(status().isOk());
        assertEquals("Temp", hobbyRepository.findHobbyByName("Temp").getName());
    }

    @Test
    void deleteHobby() {
    }



    @Test
    void getAllHobbies() {
    }

    @Test
    void findByName() {
    }

    @Test
    void findByType() {
    }

    @Test
    void findByAuthor() {
    }

    private HobbyRequest getHobbyRequest() {
        return HobbyRequest.builder()
                .id(777L)
                .name("Test Hobby")
                .describe("Test Hobby")
                .duration(new Date())
                .logoPath("Test Hobby")
                .type(Type.DRAWING)
                .author_id(1L)
                .rating(50)
                .build();
    }
}