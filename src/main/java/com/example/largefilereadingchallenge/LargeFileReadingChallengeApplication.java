package com.example.largefilereadingchallenge;

import com.example.largefilereadingchallenge.configuration.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfiguration.class})
public class LargeFileReadingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LargeFileReadingChallengeApplication.class, args);
    }

}
