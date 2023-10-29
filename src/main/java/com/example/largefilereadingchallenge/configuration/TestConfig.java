package com.example.largefilereadingchallenge.configuration;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public Path csvFilePath() {
        return Paths.get("src/test/resources/example_file.csv");
    }
}
