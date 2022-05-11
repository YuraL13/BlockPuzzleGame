package com.company.server;

import com.company.service.*;
import com.company.service.commentservices.CommentService;
import com.company.service.commentservices.CommentServiceJPA;
import com.company.service.scoreservices.ScoreService;
import com.company.service.scoreservices.ScoreServiceJPA;
import com.company.service.userservices.CurrentPlayer;
import com.company.service.userservices.CurrentPlayerImp;
import com.company.service.userservices.UserService;
import com.company.service.userservices.UserServiceJPA;
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

    @Bean
    public CommentService commentService(){return new CommentServiceJPA();}

    @Bean
    public UserService userService(){return new UserServiceJPA();}

    @Bean
    public CurrentPlayer currentPlayer(){ return new CurrentPlayerImp();}
}
