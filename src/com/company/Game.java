package com.company;

import com.company.core.Field;
import com.company.core.Level;
import com.company.core.Piece;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    //TODO: Remake Game to ConsoleUI
    //No game logic in this class
    //Split into folders

    private Level level;

    public Game(int x){
        level = new Level(x);
    }

    public void gameLoop() {
        boolean gameState = true;
        Scanner s = new Scanner(System.in);

        Level Level = new Level(1);

        var levelPieces = Level.generate();
        printPieces(levelPieces);
        Level.getField().printField();
        int c = 0;

        while (gameState) {
            c++;
            System.out.print("Choose piece: ");
            int choice = s.nextInt();

            Pattern patern = Pattern.compile("[0-9] [0-9]");

            System.out.print("Enter coordinates(x y): \n");
            var coords = s.nextLine();
            coords = s.nextLine();

            Matcher m = patern.matcher(coords);
            if (m.matches()) {
                coords = coords.replaceAll(" ", "");
            } else {
                System.out.println("Wrong format");
                continue;
            }

            int x = coords.toCharArray()[0] - 48;
            int y = coords.toCharArray()[1] - 48;

            makeMove(levelPieces.get(choice - 1), level.getField(), y, x, c);

            if (isGameFinished(level.getField())) {
                gameState = false;
            }
        }
    }

    public void printPieces(List<Piece> levelPieces){
        for (int i = 0; i < levelPieces.size(); i++) {
            System.out.println(i + 1 + ".");
            printPiece(levelPieces.get(i).getPiece());
            System.out.print("\n--------------\n");
        }
    }


    public static boolean isGameFinished(Field f){
        for(int i = 0; i < f.getRowCount(); i++){
            for(int j = 0; j < f.getColCount(); j++){
                if(f.field[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void makeMove(Piece piece, Field f, int x, int y, int c){

        var move = f.putPiece(piece.getPiece(), x, y, c);

        if(move) {
            f.printField();
        }
        else{
            f.printField();
            System.out.println("!--------NOT VALID MOVE----------!");
        }

        System.out.println("------------");
    }

    public static void printPiece(Point[] p){
        String[][] arr = new String[p.length][p.length];

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length-1; j++){
                arr[i][j] = " ";
            }
        }

        for (Point a : p){
            arr[a.x][a.y] = "X";
        }

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length-1; j++){
                System.out.print(arr[i][j] + ' ');
            }
            if(Arrays.stream(arr[i]).toList().contains("X"))
                System.out.println();
        }
    }

}
