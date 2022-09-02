package com.rocktech.compose;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {

    // use docker-compose.yaml file instead of init.js file
    private static final int MONGO_PORT = 27017;
    public static final String MONGO = "mongo";
    private static final String MONGO_URI_FORMAT = "mongodb://job_user:job_password@%s:%s/job";
    @ClassRule
    private static final DockerComposeContainer<?> compose = new DockerComposeContainer<>(new File("docker-compose.yaml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        compose
                .withEnv("HOST_PORT", "0")
                .withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort())
                .start();

        String host = compose.getServiceHost(MONGO, MONGO_PORT);
        var port = compose.getServicePort(MONGO, MONGO_PORT);
        String uri = String.format(MONGO_URI_FORMAT, host, port);

        registry.add("spring.data.mongodb.uri", () -> uri);

    }

}
