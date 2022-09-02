package com.rocktech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
public class DockerMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DockerMicroserviceApplication.class, args);
    }

}
