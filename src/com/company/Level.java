package com.company;

import java.awt.*;
import java.lang.reflect.GenericArrayType;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Point[]> level;
    private Field field;
    private int levelCouner;

    private final int levelNumber;

    public Level(int x){
        //Because initiation is array with pieces starts with 0
        level = new ArrayList<>();

        switch (x){
            case 1 -> {
                field = new Field(5 , 5);
            }
            case 2 -> {
                field = new Field(7, 4);
            }
        }

        this.levelNumber = x;

    }

    public int getLevelCouner() {
        return levelCouner;
    }

    public List<Point[]> getLevel() {
        return level;
    }



    public Field getField() {
        return field;
    }

    public List<Point[]> generate(int x){
        Piece p = new Piece();

        switch (x){
            case 1 -> {
                return generate1(p);
            }
            case 2 -> {
                return generate2(p);
            }
            default -> throw new IllegalStateException("Such level does not exist: " + x);
        }

    }

    public List<Point[]> generate(){
        Piece p = new Piece();

        switch (levelNumber){
            case 1 -> {
                return generate1(p);
            }
            case 2 -> {
                return generate2(p);
            }
            default -> throw new IllegalStateException("Such level does not exist: " + levelNumber);
        }
    }

    private List<Point[]> generate1(Piece p){

        level.add(p.piece2x4());
        level.add(p.pieceD1());
        level.add(p.pieceT1());
        level.add(p.piece5x1());

        return level;
    }

    private List<Point[]> generate2(Piece p){
        field = new Field(5, 5);

        level.add(p.piece2x4());
        level.add(p.pieceD1());
        level.add(p.pieceT1());
        level.add(p.piece5x1());
        level.add(p.pieceZ1());

        return level;
    }

}
