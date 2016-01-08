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
	@Test
	public void testCheckMoves() {
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(3,6),new Position(3,5));
		chessboard= c1.moveAt(new Position(4,1),new Position(4,2));
		c1= chessboard.moveAt(new Position(3,7),new Position(3,6));
		chessboard=c1.moveAt(new Position(5,0),new Position(1,4));
		
		Model model=new ChessBoardModel(c1);
		CheckMateController controller2=new CheckMateController(model);			
		//il re non può essere scoperto
		assertFalse(controller2.checkMoves(new Position(3,6),new Position(3,7)));
		
		chessboard = new ChessBoard(1);
		c1= chessboard.moveAt(new Position(4,6),new Position(4,4));
		chessboard= c1.moveAt(new Position(3,1),new Position(3,3));
		c1= chessboard.moveAt(new Position(4,4),new Position(4,3));
		chessboard= c1.moveAt(new Position(3,3),new Position(3,4));
		c1= chessboard.moveAt(new Position(4,3),new Position(4,2));
		chessboard= c1.moveAt(new Position(3,4),new Position(3,5));
		
		model=new ChessBoardModel(chessboard);
		controller2=new CheckMateController(model);
		//il re non può mettersi in scacco
		assertFalse(controller2.checkMoves(new Position(4,7),new Position(4,6)));
		
		c1= chessboard.moveAt(new Position(3,7),new Position(6,4));
		chessboard= c1.moveAt(new Position(5,1),new Position(5,2));
		c1= chessboard.moveAt(new Position(6,4),new Position(7,3));
		
		model=new ChessBoardModel(c1);
		controller2=new CheckMateController(model);
		
		//il re deve togliersi dallo scacco ma non può in questo caso
		assertFalse(controller2.checkMoves(new Position(4,0),new Position(5,1)));;
	}
	
	@Test
	public void testIsCheck() {
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(3,6),new Position(3,4));
		chessboard= c1.moveAt(new Position(4,1),new Position(4,2));	
		c1= chessboard.moveAt(new Position(7,6),new Position(7,5));
		chessboard=c1.moveAt(new Position(5,0),new Position(1,4));
		
		Model model=new ChessBoardModel(chessboard);
	
		controller2=new CheckMateController(model);
		
		//il re è in scacco
		assertTrue(controller2.isCheck(new Position(4,7)).equals(new Bishop(Color.black,new Position(1,4),"\u265D")));
		
		//la regina salva il re che esce dallo scacco
		c1=chessboard.moveAt(new Position(3,7),new Position(3,6));
		c1.moveAt(new Position(0,1),new Position(0,2));
		
		model=new ChessBoardModel(c1);
	
		controller2=new CheckMateController(model);
		assertEquals(controller2.isCheck(new Position(4,7)),null);

	}

	@Test
	public void testIsCheckMate() {
		
		ChessBoard chessboard = new ChessBoard(1);
		ChessBoard c1= chessboard.moveAt(new Position(4,6),new Position(4,5));
		chessboard= c1.moveAt(new Position(5,1),new Position(5,2));	
		c1= chessboard.moveAt(new Position(0,6),new Position(0,5));
		chessboard=c1.moveAt(new Position(6,1),new Position(6,3));
		c1=chessboard.moveAt(new Position(3,7),new Position(7,3));
		
		Model model=new ChessBoardModel(c1);
	
		controller2=new CheckMateController(model);
		
		assertTrue(controller2.getSafeCoordinates(model.at(new Position(7,3))).isEmpty() &&
				controller2.getGoodPieces(model.at(new Position(7,3))).isEmpty());
	}

}
