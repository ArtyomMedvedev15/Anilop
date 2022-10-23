package com.hobbiesservice.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static configuration.TestContainerConfiguration.postgres;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
class HobbyServiceImplTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:9.6.18-alpine")
            .withDatabaseName("prop")
            .withUsername("postgres")
            .withPassword("pass")
            .withExposedPorts(5432);

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgres.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "pass");
    }

    @Test
    void createHobby() {
    }

    @Test
    void updateHobby() {
    }

    @Test
    void deleteHobby() {
    }

    @Test
    void getById() {
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
}