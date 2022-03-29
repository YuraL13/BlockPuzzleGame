package com.company.consoleui;

import com.company.core.GameState;
import com.company.service.ScoreServiceJDBC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Basically wrapper for ConsoleUI.
 * Allows to play every level in the row
 */
public class Game {

    private ConsoleUI console;
    private String currentPlayer;

    public Game(){
    }

    public void play(){
        long count = 1;
        try (Stream<Path> files = Files.list(Paths.get("C:\\Users\\yural\\Desktop\\Mine\\Study TUKE\\Code\\Block Puzzle\\src\\main\\resources\\levels"))) {
            count = files.count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        askPlayer();
        System.out.println("Welcome: " + currentPlayer);
        System.out.println("Try you skills and become the best");
        int level = 1;
        ScoreServiceJDBC score = new ScoreServiceJDBC();
        do {
            score.startTimer();
            console = new ConsoleUI(level);
            console.play();
            var gameState = console.getLevel().getField().getState();

            if(gameState == GameState.SOLVED && level != count){
                console.getLevel().getField().setState(GameState.PLAYING);
                level++;
            }
            var time = score.stopTimer();
            System.out.println("TIME: " + time + " seconds");

            var levelScore =  score.countScore(time, console.getLevel().getField().size());

            System.out.println("Your score for this level is: " + levelScore);
            try {
                score.addScoreToDB(currentPlayer, levelScore, level);
            } catch (SQLException e) {
                System.out.println("Score was not recorded to database");
                break;
            }

        }while (level != count);

        System.out.println("YOU SOLVED ALL THE LEVELS!!!!");

    }

    private void askPlayer(){
        Scanner inp = new Scanner(System.in);

        do{
            System.out.println("Enter you username: ");
            currentPlayer = inp.nextLine();
        }while (currentPlayer.length() > 32);
    }

    public void play(String path){//Requires path to folder with levels
        long count = 1;
        try (Stream<Path> files = Files.list(Paths.get(path))){
            count = files.count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int level = 1;

        do {
            console = new ConsoleUI(level);
            console.play();
            var gameState = console.getLevel().getField().getState();

            if(gameState == GameState.SOLVED && level != count){
                console.getLevel().getField().setState(GameState.PLAYING);
                level++;
            }
        }while (level != count);

        System.out.println("YOU SOLVED ALL THE LEVELS!!!!");

    }



}
