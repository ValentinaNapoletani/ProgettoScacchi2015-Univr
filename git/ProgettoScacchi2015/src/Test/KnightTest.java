package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import Model.*;
import java.awt.Color;

public class KnightTest {

	@Test
	public void testValidMove() {
		
		ChessBoard chessboard = new ChessBoard();
		Knight knight=new Knight(Color.black,new Position(3,4),"\u265E");
		
		chessboard.getChessboardMatrix()[3][4]=(Piece) knight;
		
		assertTrue(chessboard.getChessboardMatrix()[3][4].isLegalMove(new Position(3,4),new Position(1,5)));
	}
	
	@Test
	public void testNotValidMove() {
		
		ChessBoard chessboard = new ChessBoard();
		Knight knight=new Knight(Color.black,new Position(3,4),"\u265E");
		
		chessboard.getChessboardMatrix()[3][4]=(Piece) knight;
		
		assertFalse(chessboard.getChessboardMatrix()[3][4].isLegalMove(new Position(3,4),new Position(6,8)));
	}

}
