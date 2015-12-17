package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import Controller.CheckMateController;
import Controller.ChessBoardController;
import Controller.Mover;
import Model.Bishop;
import Model.ChessBoard;
import Model.ChessBoardModel;
import Model.Model;
import Model.Position;
import View.ChessBoardPanel;
import View.ChessFrame;
import View.HiFrame;

public class CheckMateTest {
	
	ChessBoard chessboard = new ChessBoard();
	Model model=new ChessBoardModel(chessboard);
	HiFrame hiframe=new HiFrame();
	ChessFrame frame=new ChessFrame(hiframe);		
	ChessBoardPanel view=new ChessBoardPanel(model,frame);
	ChessBoardController controller1=new ChessBoardController(view,model,hiframe,frame);
	CheckMateController controller2=new CheckMateController(model);	
	Mover mover=new Mover(model,view,controller1,controller2);
	//OTTIMIZZA con solo 2 chessboard
	@Test
	public void testCheckMoves() {
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(3,6),new Position(3,5));
		ChessBoard c2= c1.moveAt(new Position(4,1),new Position(4,2));
		ChessBoard c3= c2.moveAt(new Position(3,7),new Position(3,6));
		ChessBoard c4=c3.moveAt(new Position(5,0),new Position(1,4));
		
		Model model=new ChessBoardModel(c4);
		CheckMateController controller2=new CheckMateController(model);			
		//il re non può essere scoperto
		assertFalse(controller2.checkMoves(new Position(3,6),new Position(3,7)));
		
		chessboard = new ChessBoard(1);
		c1= chessboard.moveAt(new Position(4,6),new Position(4,4));
		c2= c1.moveAt(new Position(3,1),new Position(3,3));
		c3= c2.moveAt(new Position(4,4),new Position(4,3));
		c4= c3.moveAt(new Position(3,3),new Position(3,4));
		ChessBoard c5= c4.moveAt(new Position(4,3),new Position(4,2));
		ChessBoard c6= c5.moveAt(new Position(3,4),new Position(3,5));
		
		model=new ChessBoardModel(c6);
		controller2=new CheckMateController(model);
		//il re non può mettersi in scacco
		assertFalse(controller2.checkMoves(new Position(4,7),new Position(4,6)));
		
		ChessBoard c7= c6.moveAt(new Position(3,7),new Position(6,4));
		ChessBoard c8= c7.moveAt(new Position(5,1),new Position(5,2));
		ChessBoard c9= c8.moveAt(new Position(6,4),new Position(7,3));
		
		model=new ChessBoardModel(c9);
		controller2=new CheckMateController(model);
		
		//il re deve togliersi dallo scacco ma non può in questo caso
		assertFalse(controller2.checkMoves(new Position(4,0),new Position(5,1)));;
	}
	
	@Test
	public void testIsCheck() {
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(3,6),new Position(3,4));
		ChessBoard c2= c1.moveAt(new Position(4,1),new Position(4,2));	
		ChessBoard c3= c2.moveAt(new Position(7,6),new Position(7,5));
		ChessBoard c4=c3.moveAt(new Position(5,0),new Position(1,4));
		
		Model model=new ChessBoardModel(c4);
	
		controller2=new CheckMateController(model);
		
		//il re è in scacco
		assertTrue(controller2.isCheck(new Position(4,7)).equals(new Bishop(Color.black,new Position(1,4),"\u265D")));
		//assertTrue(!(controller2.isCheck(new Position(4,7)).equals(null)));
		
		//la regina salva il re che esce dallo scacco
		ChessBoard c5=c4.moveAt(new Position(3,7),new Position(3,6));
		c5.moveAt(new Position(0,1),new Position(0,2));
		
		model=new ChessBoardModel(c5);
	
		controller2=new CheckMateController(model);
		assertEquals(controller2.isCheck(new Position(4,7)),null);

	}

	@Test
	public void testIsCheckMate() {
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(4,6),new Position(4,5));
		ChessBoard c2= c1.moveAt(new Position(5,1),new Position(5,2));	
		ChessBoard c3= c2.moveAt(new Position(0,6),new Position(0,5));
		ChessBoard c4=c3.moveAt(new Position(6,1),new Position(6,3));
		ChessBoard c5=c4.moveAt(new Position(3,7),new Position(7,3));
		
		Model model=new ChessBoardModel(c5);
	
		controller2=new CheckMateController(model);
		
		assertTrue(controller2.getSafeCoordinates(model.at(new Position(7,3))).isEmpty() &&
				controller2.getGoodPieces(model.at(new Position(7,3))).isEmpty());
	}

}
