package com.company.core;

import javax.print.attribute.standard.OrientationRequested;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Piece {

    private Point[] piece;
    private int color = 1;

    //Add color
    public Piece(){
        Random r = new Random();
        color = r.nextInt(1, 9);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Piece(String input){
        piece = processInput(input);

        Random r = new Random();
        color = r.nextInt(1, 9);
    }

    public Point[] getPiece() {
        return piece;
    }

    private Point[] processInput(String input){
        return switch (input) {
            case "1x3" -> piece1x3();
            case "3x1" -> piece3x1();
            case "2x2" -> piece2x2();
            case "1x5" -> piece1x5();
            case "5x1" -> piece5x1();
            case "2x4" -> piece2x4();
            case "1x4" -> piece1x4();
            case "G" -> pieceG();
            case "D" -> pieceD();
            case "T" -> pieceT();
            case "Z" -> pieceZ();
            case "D1" -> pieceD1();
            case "T1" -> pieceT1();
            case "Z1" -> pieceZ1();
            default -> throw new IllegalStateException("Such piece does not exist:  " + input);
        };
    }

    private void test_swap(Point[] piece){
        int k = 0;
        for(Point p : piece){
            k = p.x;
            p.x = p.y;
            p.y = k;
        }
    }

    private Point[] pieceG(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);
        piece[3] = new Point(1, 2);
        piece[4] = new Point(2, 2);

        return piece;
    }
    private Point[] piece1x3(){
        Point[] piece = new Point[3];
        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);
        return piece;
    }

    private Point[] piece3x1(){
        Point[] piece = new Point[3];
        piece[0] = new Point(0, 0);
        piece[1] = new Point(1, 0);
        piece[2] = new Point(2, 0);
        return piece;
    }

    private Point[] piece2x2(){
        Point[] piece = new Point[4];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(1, 0);
        piece[3] = new Point(1, 1);

        return piece;
    }

    private Point[] piece1x4(){
        Point[] piece = new Point[4];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);
        piece[3] = new Point(0, 3);

        return piece;
    }

    private Point[] piece1x5(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);
        piece[3] = new Point(0, 3);
        piece[4] = new Point(0, 4);

        return piece;
    }
    private Point[] piece5x1(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(1, 0);
        piece[2] = new Point(2, 0);
        piece[3] = new Point(3, 0);
        piece[4] = new Point(4, 0);
        return piece;
    }

    private Point[] piece2x4(){
        Point[] piece = new Point[8];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(1, 0);
        piece[3] = new Point(1, 1);
        piece[4] = new Point(0, 2);
        piece[5] = new Point(1, 2);
        piece[6] = new Point(0, 3);
        piece[7] = new Point(1 ,3);

        return piece;
    }

    private Point[] pieceD(){
        Point[] piece = new Point[9];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(1, 0);
        piece[2] = new Point(2, 0);
        piece[3] = new Point(1, 0);
        piece[4] = new Point(2, 1);
        piece[5] = new Point(2, 2);
        piece[6] = new Point(2, 1);
        piece[7] = new Point(1, 2);
        piece[8] = new Point(0, 2);

        return piece;
    }

    private Point[] pieceT(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);

        piece[3] = new Point(1, 1);
        piece[4] = new Point(2, 1);
//        piece[5] = new Point(1, 3);

        return piece;
    }

    private Point[] pieceT1(){
        var p = pieceT();
        test_swap(p);
        return p;
    }

    private Point[] pieceD1(){
        var p = pieceD();
        test_swap(p);
        return p;
    }

    private Point[] pieceZ(){
        Point[] p = new Point[4];

        p[0] = new Point(0, 0);
        p[1] = new Point(0, 1);
        p[2] = new Point(1, 1);
        p[3] = new Point(1, 2);

        return p;
    }

    private Point[] pieceZ1(){
        var p = pieceZ();

        test_swap(p);
        return p;
    }

}
