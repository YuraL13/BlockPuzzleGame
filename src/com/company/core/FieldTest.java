package com.company.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    Field field = new Field(5, 5);
    Piece piece = new Piece("2x4");

    @Test
    void isGameFinished() {

    }

    @Test
    void putPiece() {
        assertTrue(field.putPiece(piece.getPiece(), 0, 0, 1));
        field.clearFiled();
        assertTrue(field.putPiece(piece.getPiece(), 1, 0, 1));
        field.clearFiled();
        assertTrue(field.putPiece(piece.getPiece(), 2, 0, 1) );
        field.clearFiled();
        assertTrue(field.putPiece(piece.getPiece(), 3, 0, 1) );
        field.clearFiled();
        assertTrue(field.putPiece(piece.getPiece(), 0, 1, 1) );
        field.clearFiled();
        assertTrue(field.putPiece(piece.getPiece(), 1, 1, 1) );
        field.clearFiled();
        assertTrue(field.putPiece(piece.getPiece(), 2, 1, 1) );
        field.clearFiled();

        assertFalse(field.putPiece(piece.getPiece(), 1, 3, 1) );
        assertFalse(field.putPiece(piece.getPiece(), 0, 2, 1) );
        assertFalse(field.putPiece(piece.getPiece(), 0, 4, 1) );
        assertFalse(field.putPiece(piece.getPiece(), 4, 0, 1) );

    }
}