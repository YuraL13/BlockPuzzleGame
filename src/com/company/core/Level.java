package com.company.core;

import com.company.Reader;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Piece> pieces;
    private final Field field;
    private final List<String> input;
    private int levelCouner;

    private final int levelNumber;

    public Level(int x){

        this.levelNumber = x;
        pieces = new ArrayList<>(); //Creates empty array for pieces
        input = new ArrayList<>(loadLevel());//Load names for pieces from file

        field = new Field(Integer.parseInt(input.get(0)) , Integer.parseInt(input.get(1)));


        input.remove(0);
        input.remove(0);//Removes field sizes from input so only names of pieces are left


        pieces = generate();
    }

    public Level(){

        this.levelNumber = 1;
        pieces = new ArrayList<>(); //Creates empty array for pieces
        input = new ArrayList<>(loadLevel());//Load names for pieces from file

        field = new Field(Integer.parseInt(input.get(0)) , Integer.parseInt(input.get(1)));


        input.remove(0);
        input.remove(0);//Removes field sizes from input so only names of pieces are left


        pieces = generate();
    }

    private List<String> loadLevel(){
        Reader reader = new Reader(levelNumber);

        return reader.readLevelFromFile();
    }

    public int getLevelCouner() {
        return levelCouner;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Field getField() {
        return field;
    }

    public List<Piece> generate(){
        Piece p = new Piece();

        for (String s : input) {
            pieces.add(new Piece(s));
        }

        return pieces;

    }

}
