package com.rocktech.generic;

import com.rocktech.dto.Service;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public abstract class BaseTest {
    private static final int MONGO_PORT = 27017;
    private static final String MONGO_URI_FORMAT = "mongodb://candidate_user:candidate_password@%s:%s/candidate";
    private static final String INIT_JS  = "/docker-entrypoint-initdb.d/init.js";

    private static final String JOB_INIT_MOCK  = "/config/init.json";

    private static final Service MONGO = Service.create(
            "mongo",
            27017,
            "0",
            "mongodb://candidate_user:candidate_password@%s:%s/candidate",
            "HOST_PORT1"
    );

    private static final Service MOCK_JOB = Service.create(
            "mockserver/mockserver",
            1080,
            "0",
            "http://%s:%s/job/",
            "HOST_PORT2"
    );

    @Container
    private static final GenericContainer<?> mango = new
            GenericContainer(DockerImageName.parse(MONGO.getName()))
            .withExposedPorts(MONGO.getPort())
            .withClasspathResourceMapping(
                    "data/init.js", INIT_JS,
                    BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort());

    @Container
    private static final GenericContainer<?> mock = new
            GenericContainer(DockerImageName.parse(MOCK_JOB.getName()))
            .withExposedPorts(MOCK_JOB.getPort())
            .withClasspathResourceMapping(
                    "data/job-init.json", JOB_INIT_MOCK,
                    BindMode.READ_ONLY)
            .waitingFor(Wait.forListeningPort());

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mango.start();
        mock.start();
        String uri = String.format(MONGO.getUri(), mango.getHost(), mango.getMappedPort(MONGO.getPort()));
        String uriMock = String.format(MOCK_JOB.getUri(), mock.getHost(), mock.getMappedPort(MOCK_JOB.getPort()));

        registry.add("spring.data.mongodb.uri", () -> uri);
        registry.add("job.service.uri", () -> uriMock);

    }

}
