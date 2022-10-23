package configuration;

import org.junit.ClassRule;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerConfiguration {

    @ClassRule
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:9.6.18-alpine")
            .withInitScript("init-db.sql");

}
