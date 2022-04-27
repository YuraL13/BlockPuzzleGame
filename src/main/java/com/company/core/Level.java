package com.company.core;

import com.company.consoleui.Reader;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Piece> pieces;
    private final Field field;
    private final List<String> input;

    private GameState state;

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
    private int levelCouner;

    public int getLevelNumber() {
        return levelNumber;
    }

    private final int levelNumber;

    public Level(int x){

        this.levelNumber = x;
        pieces = new ArrayList<>(); //Creates empty array for pieces
        input = new ArrayList<>(loadLevel());//Load names for pieces from file

        field = new Field(Integer.parseInt(input.get(0)) , Integer.parseInt(input.get(1)));


        input.remove(0);
        input.remove(0);//Removes field sizes from input so only names of pieces are left

        pieces = generate();
        this.state = field.getState();
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

    public boolean makeMove(Piece piece, int x, int y){
        var res =  field.putPiece(piece, x, y);
        if(res){
            removePiece(piece);
        }
        if(field.isGameFinished()){
            state = GameState.SOLVED;
        }
        return res;
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

    public boolean removePiece(int i){
        var res = pieces.remove(i);
        return res != null;
    }

    public boolean removePiece(Piece p){
        return pieces.remove(p);
    }

}
