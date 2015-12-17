package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import Model.ChessBoard;
import Model.Piece;
import Model.Queen;
import Model.Position;

public class QueenTest {

	@Test
	public void testMove() {
		
		ChessBoard chessboard = new ChessBoard();
		Queen queen=new Queen(Color.black,new Position(3,4),"\u265E");
		
		chessboard.getChessboardMatrix()[3][4]=(Piece) queen;
		
		assertTrue(chessboard.getChessboardMatrix()[3][4].isLegalMove(new Position(3,4),new Position(3,7)));
		assertTrue(chessboard.getChessboardMatrix()[3][4].isLegalMove(new Position(3,4),new Position(4,3)));
		assertTrue(chessboard.getChessboardMatrix()[3][4].isLegalMove(new Position(3,4),new Position(6,4)));
		
		assertFalse(chessboard.getChessboardMatrix()[3][4].isLegalMove(new Position(3,4),new Position(7,1)));
	}

}
