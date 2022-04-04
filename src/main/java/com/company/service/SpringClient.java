package com.company.service;

import com.company.consoleui.ConsoleUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

@SpringBootApplication
@Configuration
public class SpringClient {
    public static void main(String[] args) {
        //SpringApplication.run(SpringClient.class);

        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public ConsoleUI console() {
        return new ConsoleUI();
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }

}
