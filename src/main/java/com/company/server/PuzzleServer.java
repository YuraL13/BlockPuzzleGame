package com.company.server;

import com.company.service.SaveGame;
import com.company.service.SaveGameJPA;
import com.company.service.ScoreService;
import com.company.service.ScoreServiceJPA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "com.company.entity")
public class PuzzleServer {
    public static void main(String[] args) {
        SpringApplication.run(PuzzleServer.class, args);
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }

    @Bean
    public SaveGame saveGame(){return new SaveGameJPA();}

}
