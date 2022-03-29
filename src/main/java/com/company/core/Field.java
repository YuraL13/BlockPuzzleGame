package com.company.core;

import java.awt.*;
import java.util.List;

public class Field {

    public int[][] field;
    public List<Piece> pieces;
    private final int rowCount;
    private final int colCount;
    private GameState state = GameState.PLAYING;

    public Field(int rowCount, int colCount)
    {
        this.rowCount = rowCount;
        this.colCount = colCount;

        field = new int[rowCount][colCount];

        //Generates empty field
        for (int i = 0; i < rowCount; i++){
            for(int j = 0; j < colCount; j++){
                field[i][j] = 0;
            }
        }
    }

    public boolean isGameFinished(){
        for(int i = 0; i < rowCount; i++){
            for(int j = 0; j < colCount; j++){
                if(field[i][j] == 0){
                    return false;
                }
            }
        }
        state = GameState.SOLVED;
        return true;
    }

    public int getColCount() {
        return colCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public boolean putPiece(Piece points, int x, int y){

        if(!isMoveValid(points.getPiece(), x, y)) return false;

        for(Point p : points.getPiece()){
            field[p.x + x][p.y + y] = points.getColor();
        }
        return true;
    }

    public boolean putPiece(Point[] points, int x, int y, int c){

        if(!isMoveValid(points, x, y)) return false;

        for(var p : points){
            field[p.x + x][p.y + y] = c;
        }


        return true;
    }

    private boolean isMoveValid(Point[] points, int x, int y){

        if(x >= rowCount || x < 0 || y >= colCount || y < 0){
            return false;
        }

        for (Point p : points){
            if(p.x + x >= rowCount || p.y + y >= colCount){
                return false;
            }
            if(field[p.x + x][p.y + y] != 0){
                return false;
            }
        }

        return true;
    }

    public void printField(){
        String output = ".";
        System.out.print("   ");
        for(int i = 0; i < colCount; i++){
            System.out.print(i + " ");//Not the best way to do thi but it works
        }

        System.out.println();
        for (int i = 0; i < rowCount; i++){
            System.out.print(i + "| ");
            for(int j = 0; j < colCount; j++){
                if(field[i][j] != 0){
                    output = String.valueOf(field[i][j]);
                }
                else {
                    output = ".";
                }
                System.out.print(output + " ");
            }
            System.out.println();
        }
    }

    public void clearFiled(){
        for (int i = 0; i < rowCount; i++){
            for(int j = 0; j < colCount; j++){
                field[i][j] = 0;
            }
        }
    }

    public int size(){
        return rowCount*colCount;
    }

}
