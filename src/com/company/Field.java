package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Field {

    public int[][] field;
    public List<Piece> pieces;
    private final int rowCount;
    private final int colCount;
    private GameState state = GameState.PLAYING;
    private int partsCount;

    private List<Tile> parts;

    public Field(int rowCount, int colCount)
    {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.partsCount = 5;

        field = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++){
            for(int j = 0; j < colCount; j++){
                field[i][j] = 0;
            }
        }

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

    public boolean putPiece(Point[] points, int x, int y){

        if(!isMoveValid(points, x, y)) return false;

        for(Point p : points){
            field[p.x + x][p.y + y] = 1;
        }
        return true;
    }

    public boolean putPiece(Point[] points, int x, int y, int c){

        if(!isMoveValid(points, x, y)) return false;

        for(Point p : points){
            field[p.x + x][p.y + y] = c;
        }
        return true;
    }

    private boolean isMoveValid(Point[] points, int x, int y){

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
        for (int i = 0; i < rowCount; i++){
            for(int j = 0; j < colCount; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

}
