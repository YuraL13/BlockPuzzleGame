package com.company.consoleui;

import com.company.core.GameState;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Basically wrapper for ConsoleUI.
 * Allows to play every level in the row
 */
public class Game {

    private ConsoleUI console;


    public Game(){
    }

    public void play(){
        long count = 1;
        try (Stream<Path> files = Files.list(Paths.get("C:\\Users\\yural\\Desktop\\Mine\\Study TUKE\\Code\\Block Puzzle\\src\\main\\resources\\levels"))) {
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
