package io.github.wesleyosantos91.cucumber.config;

import jakarta.validation.constraints.NotNull;
import org.junit.After;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainerConfig  {

    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.29")
            .withDatabaseName("transactions")
            .withUsername("app")
            .withPassword("app");

    @After
    public void stopContainer() {
        mySQLContainer.stop();
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(final @NotNull ConfigurableApplicationContext applicationContext) {
            mySQLContainer.start();

            TestPropertyValues values =
                    TestPropertyValues.of(
                            "spring.datasource.password=" + mySQLContainer.getPassword(),
                            "spring.datasource.username=" + mySQLContainer.getUsername(),
                            "spring.datasource.url=" + mySQLContainer.getJdbcUrl(),
                            "spring.flyway.url=" + mySQLContainer.getJdbcUrl(),
                            "spring.flyway.user=" + mySQLContainer.getUsername(),
                            "spring.flyway.password=" + mySQLContainer.getPassword());

            values.applyTo(applicationContext);

        }
    }
}