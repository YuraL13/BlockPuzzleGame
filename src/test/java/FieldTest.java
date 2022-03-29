
import com.company.core.Field;
import com.company.core.Piece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    Field field = new Field(5, 5);
    Piece piece = new Piece("2x4");

    @Test
    void isGameFinished() {
        assertFalse(field.isGameFinished());
        field.putPiece(piece.getPiece(), 0, 0, 1);
        assertFalse(field.isGameFinished());
        field.putPiece(piece.getPiece(), 2, 0, 1);
        assertFalse(field.isGameFinished());
        field.putPiece(new Piece("1x4").getPiece(), 4, 0, 2);
        assertFalse(field.isGameFinished());
        field.putPiece(new Piece("5x1").getPiece(), 0, 4, 1);

        assertTrue(field.isGameFinished());

        field = new Field(10, 10);


        for(int i = 0; i < field.getColCount(); i++){
            assertFalse(field.isGameFinished());
            field.putPiece(new Piece("1x5").getPiece(), 0, i, 1);
        }

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