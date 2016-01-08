package Test;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;
import Model.ChessBoard;
import Model.Position;
import Model.Rook;
import Model.ChessBoardModel;
import Model.Model;

public class ChessboardTest {

	//Testa l'ugualianza tra due scacchiere.
	@Test
	public void test() {
		
		ChessBoard c=new ChessBoard(1);
		ChessBoard c2=new ChessBoard(1);
		
		assertTrue(c.equals(c2));
		
		ChessBoard c3= c2.moveAt(new Position(3,6),new Position(3,5));
		
		assertFalse(c.equals(c3));
	}
	
	@Test
	public void enemyPiecesTest() {
		
		ChessBoard c=new ChessBoard(1);

		ChessBoard c2= c.moveAt(new Position(3,6),new Position(3,5));
		
		//La lista dei pezzi avversari contiene la torre bianca in posizione (0,7).
		assertTrue(c2.getOpponentsPieces().contains(new Rook(Color.white,new Position(0,7),"\u265C")));
		//Il re nero non è in posozione (0,1);
		assertFalse(c2.getMyKingCoord().equals(new Position(0,1)));
		
	}
	
	@Test
	public void promotionTest(){
		
		ChessBoard c = new ChessBoard(1);
		
		//senza controlli sulle mosse mangio un pezzo nero
		ChessBoard c1=c.moveAt(new Position(3,6),new Position(0,0));
		
		//senza controlli sulle mosse metto il pedone in posizione per la promozione.
		ChessBoard c2= c1.moveAt(new Position(0,1), new Position(0,7));
		Model model=new ChessBoardModel(c2);
		
		c2.pieceSwap(new Position(0,7),new Rook(Color.black,new Position(0,0),"\u265C"));
		//Verifico che lo scambio sia stato effettuato correttamete.
		assertEquals(model.at(new Position(0,7)),new Rook(Color.black,new Position(0,7),"\u265C"));
	}

}
