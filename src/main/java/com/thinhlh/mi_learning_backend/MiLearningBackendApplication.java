package com.thinhlh.mi_learning_backend;

import com.thinhlh.mi_learning_backend.config.EnvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
public class MiLearningBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiLearningBackendApplication.class, args);
    }
}

