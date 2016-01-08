package Model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Classe che implementa la configurazione della scacchiera.
 * 
 * @author Napoletani Valentina VR377688
 */

public class ChessBoard implements Configuration {

	private Piece[][] chessBoardMatrix;
	private static Color turn;
	
	/**
	 * Costruttore della classe. Crea la scacchiera inserendo i pezzi nelle posizioni di partenza.
	 * 
	 * @param i Indica che la scacchiera viene inizializzata con i pezzi posti allo stato iniziale. 
	 */	
	public ChessBoard(int i) {
		
		this.chessBoardMatrix = new Piece[8][8];
		
			initializePieces();
			turn= Color.white;
		
	}
	
	/**
	 * Costruttore della classe. La scacchiera viene creata in una configurazione presisa.
	 * 
	 * @param turn Colore del turno corrente.
	 * @param chessBoardMatrix Matrice dei pezzi.
	 */
	public ChessBoard(Color turn, Piece[][] chessBoardMatrix) {
		
		this.chessBoardMatrix = new Piece[8][8];
		
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				this.chessBoardMatrix[x][y] = chessBoardMatrix[x][y];
		
	}
	
	/**
	 * Costruttore della classe. La scacchiera viene creata vuota.
	 */
	public ChessBoard() {
		
		this.chessBoardMatrix = new Piece[8][8];
		
	}

	/**
	 * Il metodo inizializza i pezzi allo stato iniziale della partita.
	 */
	public void initializePieces(){
		
		//pedoni bianchi
		
		for(int  i = 0; i < 8; i++) {
			chessBoardMatrix[i][6] = new Pawn(Color.white,new Position(i,6),"\u265F");
			
		}
		
		//pedoni neri
		for( int i = 0; i < 8; i++) {
			chessBoardMatrix[i][1] = new Pawn(Color.black,new Position(i,1),"\u265F");
			
			
		}
		
		// altri pezzi neri 
		int i=0;
	
		chessBoardMatrix[i][0] = new Rook(Color.black,new Position(i,0),"\u265C"); i++;
		chessBoardMatrix[i][0] = new Knight(Color.black,new Position(i,0),"\u265E");i++;
		chessBoardMatrix[i][0] = new Bishop(Color.black,new Position(i,0),"\u265D");i++;
		chessBoardMatrix[i][0] = new Queen(Color.black,new Position(i,0),"\u265B");i++;
		chessBoardMatrix[i][0] = new King(Color.black,new Position(i,0),"\u265A");i++;
		chessBoardMatrix[i][0] = new Bishop(Color.black,new Position(i,0),"\u265D");i++;
		chessBoardMatrix[i][0] = new Knight(Color.black,new Position(i,0),"\u265E");i++;
		chessBoardMatrix[i][0] = new Rook(Color.black,new Position(i,0),"\u265C");
		
		
		// altri pezzi bianchi 
		i=0;
		
		chessBoardMatrix[i][7] = new Rook(Color.white,new Position(i,7),"\u265C");i++;
		chessBoardMatrix[i][7] = new Knight(Color.white,new Position(i,7),"\u265E");i++;
		chessBoardMatrix[i][7] = new Bishop(Color.white,new Position(i,7),"\u265D");i++;
		chessBoardMatrix[i][7] = new Queen(Color.white,new Position(i,7),"\u265B");i++;
		chessBoardMatrix[i][7] = new King(Color.white,new Position(i,7),"\u265A");i++;
		chessBoardMatrix[i][7] = new Bishop(Color.white,new Position(i,7),"\u265D");i++;
		chessBoardMatrix[i][7] = new Knight(Color.white,new Position(i,7),"\u265E");i++;
		chessBoardMatrix[i][7] = new Rook(Color.white,new Position(i,7),"\u265C");
		
	}

	/**
	 * Il metodo ritorna il pezzo ad una determinata posizione.
	 * 
	 * @param coordinates Le coordinate del pezzo.
	 * @return Ritorna il pezzo nella posizione indicata dal parametro.
	 */
	@Override
	public Piece at(Position coordinates) {
		if (chessBoardMatrix[coordinates.x][coordinates.y]!=null)
		return chessBoardMatrix[coordinates.x][coordinates.y];
		else return null;
	}

	/** Muove un pezzo sulla scacchiera.
	 * 
	 * @param initialCoord Le coordiante iniziali da cui spostare il pezzo.
	 * @param finalCoord Le coordiante finali di arrivo del pezzo.
	 * @return La scacchiera con il pezzo spostato.
	 */
	@Override
	public ChessBoard moveAt(Position initialCoord, Position finalCoord) {
		
		Piece toMove = chessBoardMatrix[initialCoord.x][initialCoord.y];
		ChessBoard result=null;
		
		setTurn();
		result = new ChessBoard(getTurn(),chessBoardMatrix);
		
		result.chessBoardMatrix[initialCoord.x][initialCoord.y] = null;	
		result.chessBoardMatrix[finalCoord.x][finalCoord.y] = toMove;
		at(initialCoord).setCoordinates(finalCoord);
				
		return result;
		
	}
	
	/** Ritorna la matrice dei pezzi.
	 * 
	 * @return Ritorna la matrice dei pezzi.
	 */
	public Piece[][] getChessboardMatrix(){
		return chessBoardMatrix;
	}
	
	/** Verifica l'ugualianza tra due scacchiere.
	 * Due scacchiere sono uguali se casella per casella contengono lo stesso pezzo oppure non contengono pezzi.
	 * 
	 * @param obj L'oggetto da confrontare.
	 * @return Ritorna true se le scacchiere sono uguali,false altrimenti.
	 */
	public boolean equals(Object obj) {
		
		Position coordinates=new Position();
		boolean equals=false;
		
		if (obj instanceof ChessBoard) 
			for (int y=0;y<8;y++) {
				coordinates.y=y;
				for (int x=0;x<8;x++) {
					coordinates.x=x;
					if (chessBoardMatrix[x][y]==null ^ ((ChessBoard) obj).at(coordinates)==null ){
						equals=false;
						return false;
					}
					
					if(chessBoardMatrix[x][y]!=null && ((ChessBoard) obj).at(coordinates)!=null && chessBoardMatrix[x][y].equals(((ChessBoard) obj).at(coordinates))) 
						equals=true;
					else equals=false;
				}
			}
		return equals;	
	}
	
	/** Ritorna il colore del turno.
	 * 
	 * @return Ritorna il colore del turno.
	 */
	public Color getTurn() {
		return turn;
	}
	
	/**
	 * Imposta il turno del colore passato come paramentro. Utile per i test.
	 *
	 * @param c Colore del turno da impostare.
	 */
	public void setTurn(Color c){
		turn=c;
	}

	/**
	 * Imposta il turno controllando quello attuale.
	 */
	public void setTurn() {
		if (getTurn()==Color.white)
			turn=Color.black;
		else turn=Color.white;
		
	}
	
	/** Il metodo ritorna la lista dei pezzi avversari.
	 * 
	 * @return Ritorna l'ArrayList dei pezzi avversari.
	 */
	public ArrayList<Piece> getOpponentsPieces() {
		ArrayList<Piece> Pieces= new ArrayList<> ();
		for (int y=0; y<8; y++)
			for (int x=0; x<8; x++)
				if(chessBoardMatrix[x][y]!=null && chessBoardMatrix[x][y].getColor()!=getTurn() )
					Pieces.add(chessBoardMatrix[x][y]);	
		return Pieces;	
	}
	
	/** Il metodo ritorna la lista dei pezzi del colore corrispondente al turno.
	 * 
	 * @return Ritorna l'ArrayList dei pezzi del colore corrispondente al turno.
	 */
	public ArrayList<Piece> getMyPieces() {
		ArrayList<Piece> Pieces= new ArrayList<> ();
		for (int y=0; y<8; y++)
			for (int x=0; x<8; x++)
				if(chessBoardMatrix[x][y]!=null && chessBoardMatrix[x][y].getColor()==getTurn() )
					Pieces.add(chessBoardMatrix[x][y]);		
		return Pieces;	
	}
	
	/**Ritorna la posizione del re del colore corrispondente al turno.
	 * 
	 * @return Ritorna la posizione del re del colore corrispondente al turno.
	 */
	public Position getMyKingCoord(){
		
		Position whiteKing= new Position();
		Position blackKing= new Position();
		
		for (int y=0; y<8; y++)
			for (int x=0; x<8; x++)
				if(chessBoardMatrix[x][y] instanceof King && chessBoardMatrix[x][y].getColor()==Color.white) {
					whiteKing.x=x;
					whiteKing.y=y;
					if(getTurn()==Color.white)
						return whiteKing;
				}
				else if(chessBoardMatrix[x][y] instanceof King && chessBoardMatrix[x][y].getColor()==Color.black) {
					blackKing.x=x;
					blackKing.y=y;
					if(getTurn()==Color.black)
						return blackKing;
				}
		
		return getTurn()== Color.white ? whiteKing : blackKing;
	}
	
	/**Ritorna la posizione del re avversario.
	 * 
	 * @return Ritorna la posizione del re avversario.
	 */
	public	Position getEnemyKingCoord(){
		
		Position whiteKing= new Position();
		Position blackKing= new Position();
		
		for (int y=0; y<8; y++)
			for (int x=0; x<8; x++)
				if(chessBoardMatrix[x][y] instanceof King && chessBoardMatrix[x][y].getColor()==Color.white) {
					whiteKing.x=x;
					whiteKing.y=y;
				}
				else if(chessBoardMatrix[x][y] instanceof King && chessBoardMatrix[x][y].getColor()==Color.black) {
					blackKing.x=x;
					blackKing.y=y;
				}
		
		return getTurn()== Color.white ? blackKing : whiteKing;
	}
	
	/**
	 * Gestisce la promozione del pedone, modificando la scacchiera attuale.
	 * 
	 * @param pos La posizione in cui scambiare il pedone con un altro pezzo per promuoverlo.
	 * @param piece Il pezzo da far tornare in gioco.
	 */
	public void pieceSwap(Position pos,Piece piece){
		
		chessBoardMatrix[pos.x][pos.y]=piece;
		piece.setCoordinates(pos);
	}
}
