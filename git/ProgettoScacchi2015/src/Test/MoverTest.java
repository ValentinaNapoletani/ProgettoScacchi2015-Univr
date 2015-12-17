package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import Controller.*;
import Model.*;
import View.*;

public class MoverTest {
	
	ChessBoard chessboard = new ChessBoard();
	Model model=new ChessBoardModel(chessboard);
	HiFrame hiframe=new HiFrame();
	ChessFrame frame=new ChessFrame(hiframe);		
	ChessBoardPanel view=new ChessBoardPanel(model,frame);
	ChessBoardController controller1=new ChessBoardController(view,model,hiframe,frame);
	CheckMateController controller2=new CheckMateController(model);	
	Mover mover=new Mover(model,view,controller1,controller2);

	//I pezzi non possono mangiare altri pezzi del loro colore
	@Test
	public void testColor() {
		
		chessboard.getChessboardMatrix()[2][6]=(Piece) new Queen(Color.black,new Position(2,6),"\u265B");
		chessboard.getChessboardMatrix()[3][7]=(Piece) new Bishop(Color.black,new Position(3,7),"\u265D");
		
		assertFalse(mover.isLegalMove(new Position(3,7), new Position(2,6)));
		
	}
	
	//Tutti i pezzi tranne il cavallo devono avere il percorso libero
	@Test
	public void testFreePath(){
		
		chessboard.getChessboardMatrix()[2][6]=(Piece) new Queen(Color.black,new Position(2,6),"\u265B");
		chessboard.getChessboardMatrix()[3][7]=(Piece) new Bishop(Color.black,new Position(3,7),"\u265D");
		chessboard.getChessboardMatrix()[0][4]=(Piece) new Queen(Color.white,new Position(0,4),"\u265B");
		
		assertFalse(mover.isLegalMove(new Position(3,7), new Position(0,4)));
		
		chessboard.getChessboardMatrix()[2][6]=null;
		assertTrue(mover.isLegalMove(new Position(3,7), new Position(0,4)));
		
		chessboard.getChessboardMatrix()[1][5]=(Piece) new Knight(Color.white,new Position(1,5),"\u265E");
		assertTrue(mover.isLegalMove(new Position(1,5), new Position(3,4)));
	}
	
	//test per verificare l'aggiunta di un pezzo alla lista se viene mangiato
	@Test
	public void testjumped(){
		
		Piece queen=(Piece) new Queen(Color.black,new Position(0,2),"\u265B");
		chessboard.getChessboardMatrix()[0][4]=(Piece) new Queen(Color.white,new Position(0,4),"\u265B");
		chessboard.getChessboardMatrix()[0][2]=queen;

		
		chessboard.setTurn(Color.white);
		hiframe.createLayout(controller1);
		mover.moveAt(new Position(0,4), new Position(0,2), null);
		
		assertTrue(controller1.getblackPieces().contains(queen));
	}
	
	//test per verifivare la presenza di un vincitore
	@Test
	public void testWinner(){
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(4,6),new Position(4,4));
		ChessBoard c2= c1.moveAt(new Position(5,1),new Position(5,3));
		ChessBoard c3= c2.moveAt(new Position(0,6),new Position(0,5));
		ChessBoard c4=c3.moveAt(new Position(6,1),new Position(6,3));
		ChessBoard c5= c4.moveAt(new Position(3,7),new Position(7,3));
		
		Model model=new ChessBoardModel(c5);
		ChessBoardController controller1=new ChessBoardController(view,model,hiframe,frame);
		CheckMateController controller2=new CheckMateController(model);	
		Mover mover=new Mover(model,view,controller1,controller2);
		
		assertEquals(Color.white,mover.therIsAWinner(c5.getChessboardMatrix()[7][3]));
	}

}
