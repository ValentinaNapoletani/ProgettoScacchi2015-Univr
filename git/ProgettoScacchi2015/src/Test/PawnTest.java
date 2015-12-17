package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import Model.*;
import View.*;
import Controller.*;
import java.awt.Color;

public class PawnTest {
	
	//pedone non si muove all'indietro
	@Test
	public void testMoveBackward() {
		ChessBoard chessboard = new ChessBoard();
		
		chessboard.getChessboardMatrix()[0][5]=(Piece) new Pawn(Color.white,new Position(0,5),"\u265F");
		boolean b=chessboard.getChessboardMatrix()[0][5].isLegalMove(new Position(0,5),new Position(0,6));
				
		assertEquals(false,b);
	}
	
	//pedone si muove di due passi se non si è ancora mosso
	@Test
	public void testdoublejump(){
		
		Piece piece=(Piece) new Pawn(Color.white,new Position(5,6),"\u265F");
			
		assertTrue(piece.getValidPosition(new Position(5,6)).contains(new Position(5,4)));
	}
	
	//Il pedone si muove in diagonale se può mangiare
	@Test
	public void testMoveDiagonally(){
		
		ChessBoard chessboard = new ChessBoard();
		Model model=new ChessBoardModel(chessboard);
		HiFrame hiframe=new HiFrame();
		ChessFrame frame=new ChessFrame(hiframe);		
		ChessBoardPanel view=new ChessBoardPanel(model,frame);
		ChessBoardController controller1=new ChessBoardController(view,model,hiframe,frame);
		CheckMateController controller2=new CheckMateController(model);
		
		Mover mover=new Mover(model,view,controller1,controller2);
		
		chessboard.getChessboardMatrix()[0][5]=(Piece) new Pawn(Color.white,new Position(0,5),"\u265F");
		chessboard.getChessboardMatrix()[1][4]=(Piece) new Bishop(Color.black,new Position(1,4),"\u265D");
		
		assertTrue(mover.isLegalMove(new Position(0,5), new Position(1,4)));
		
		chessboard.getChessboardMatrix()[1][4]=null;
		assertFalse(mover.isLegalMove(new Position(0,5), new Position(1,4)));
		
	}
	
	

}
