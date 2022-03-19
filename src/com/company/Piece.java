package com.company;

import java.awt.*;
import java.util.List;

public class Piece {
    List<Point> points;

    public Piece(){

    }

    private void test_swap(Point[] piece){
        int k = 0;
        for(Point p : piece){
            k = p.x;
            p.x = p.y;
            p.y = k;
        }
    }

    public Point[] piece1x3(){
        Point[] piece = new Point[3];
        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);
        return piece;
    }

    public Point[] piece3x1(){
        Point[] piece = new Point[3];
        piece[0] = new Point(0, 0);
        piece[1] = new Point(1, 0);
        piece[2] = new Point(2, 0);
        return piece;
    }

    public Point[] piece2x2(){
        Point[] piece = new Point[4];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(1, 0);
        piece[3] = new Point(1, 1);

        return piece;
    }

    public Point[] piece1x5(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);
        piece[3] = new Point(0, 3);
        piece[4] = new Point(0, 4);

        return piece;
    }
    public Point[] piece5x1(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(1, 0);
        piece[2] = new Point(2, 0);
        piece[3] = new Point(3, 0);
        piece[4] = new Point(4, 0);
        return piece;
    }

    public Point[] piece2x4(){
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

    public Point[] pieceD(){
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

    public Point[] pieceT(){
        Point[] piece = new Point[5];

        piece[0] = new Point(0, 0);
        piece[1] = new Point(0, 1);
        piece[2] = new Point(0, 2);

        piece[3] = new Point(1, 1);
        piece[4] = new Point(2, 1);
//        piece[5] = new Point(1, 3);

        return piece;
    }

    public Point[] pieceT1(){
        var p = pieceT();
        test_swap(p);
        return p;
    }

    public Point[] pieceD1(){
        var p = pieceD();
        test_swap(p);
        return p;
    }

    public Point[] pieceZ(){
        Point[] p = new Point[4];

        p[0] = new Point(0, 0);
        p[1] = new Point(0, 1);
        p[2] = new Point(1, 1);
        p[3] = new Point(1, 2);

        return p;
    }

    public Point[] pieceZ1(){
        var p = pieceZ();

        test_swap(p);
        return p;
    }

}
