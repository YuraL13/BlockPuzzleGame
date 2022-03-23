package com.company.consoleui;

import com.company.core.GameState;
import com.company.core.Level;
import com.company.core.Piece;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleUI {

    private Level level;
    private int levelNumber;
    public ConsoleUI(int x){
        this.levelNumber = x;
        level = new Level(x);
    }

    public void play() {
        Scanner s = new Scanner(System.in);

        var pieces = level.getPieces();
        var field = level.getField();

        printPieces(pieces);
        field.printField();

        while (level.getField().getState() != GameState.SOLVED) {
            System.out.print("Choose piece(to clear the field enter -1): ");
            int choice = s.nextInt();

            if(choice == -1){ // To clear the field
                level = new Level(levelNumber);
                pieces = level.getPieces();
                printPieces(level.getPieces());
                level.getField().printField();
                continue;
            }

            if(choice-1 >= pieces.size()){ //Checks is input is valid
                System.out.println("!!!NOT VALID ENTER!!!");
                continue;
            }

            //Enter of coordinates and validation
            Pattern patern = Pattern.compile("[0-9] [0-9]");

            System.out.print("Enter coordinates(x y): ");
            var coords = s.nextLine();
            coords = s.nextLine();

            coords = coords.trim();
            Matcher m = patern.matcher(coords);
            if (m.matches()) {
                coords = coords.replaceAll(" ", "");
            } else {
                System.out.println("Wrong format");
                continue;
            }

            int x = coords.toCharArray()[0] - 48;
            int y = coords.toCharArray()[1] - 48;
            //***************************************

            var currentPiece = pieces.get(choice - 1);

            makeMove(currentPiece, y, x);//x and y are fliped for more understandable input. Would be better to fix implementation but i'd be too long

            if (level.getField().isGameFinished()) {
                System.out.println("!!!CONGRATULATIONS!!!");
                break;
            }
        }
    }

    public Level getLevel() {
        return level;
    }

    public void printPieces(List<Piece> levelPieces){ //Prints list of all pieces
        for (int i = 0; i < levelPieces.size(); i++) {
            System.out.println(i + 1 + ".");
            printPiece(levelPieces.get(i).getPiece());
            System.out.print("\n--------------\n");
        }
    }

    public boolean makeMove(Piece piece, int x, int y){

        var move = level.getField().putPiece(piece, x, y);

        if(move) {
            level.removePiece(piece);
            printPieces(level.getPieces());
            level.getField().printField();
            System.out.println("------------");
        }
        else{
            printPieces(level.getPieces());
            level.getField().printField();
            System.out.println("!--------NOT VALID MOVE----------!");
        }
        return move;
    }

    public static void printPiece(Point[] p){ //Func to print a piece
        //Creates 2D array with blanc spaces and then replaces right coordinates with X
        String[][] arr = new String[p.length][p.length];

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                arr[i][j] = " ";
            }
        }

        for (Point a : p){
            arr[a.x][a.y] = "X";
        }

        for(int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                System.out.print(arr[i][j] + ' ');
            }
            if(Arrays.stream(arr[i]).toList().contains("X"))
                System.out.println();
        }
    }

}
