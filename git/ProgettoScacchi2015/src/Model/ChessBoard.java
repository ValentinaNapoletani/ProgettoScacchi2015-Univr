package Model;

import java.awt.Color;
import java.util.ArrayList;

public class ChessBoard implements Configuration {

	private Piece[][] chessBoardMatrix;
	private static Color turn;
	
	public ChessBoard(int i) {
		
		this.chessBoardMatrix = new Piece[8][8];
		
			initializePieces();
			turn= Color.white;
		
	}
	
	public ChessBoard(Color turn, Piece[][] chessBoardMatrix) {
		
		this.chessBoardMatrix = new Piece[8][8];
		
		for(int y = 0; y < 8; y++)
			for(int x = 0; x < 8; x++)
				this.chessBoardMatrix[x][y] = chessBoardMatrix[x][y];
		
	}
	
	public ChessBoard() {
		
		this.chessBoardMatrix = new Piece[8][8];
		
	}

	
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

	@Override
	public Piece at(Position coordinates) {
		if (chessBoardMatrix[coordinates.x][coordinates.y]!=null)
		return chessBoardMatrix[coordinates.x][coordinates.y];
		else return null;
	}

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
	
	public Piece[][] getChessboardMatrix(){
		return chessBoardMatrix;
	}
	
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
	
	public Color getTurn() {
		return turn;
	}
	
	public void setTurn(Color c){
		turn=c;
	}

	
	public void setTurn() {
		if (getTurn()==Color.white)
			turn=Color.black;
		else turn=Color.white;
		
	}
	
	public ArrayList<Piece> getOpponentsPieces() {
		ArrayList<Piece> Pieces= new ArrayList<> ();
		for (int y=0; y<8; y++)
			for (int x=0; x<8; x++)
				if(chessBoardMatrix[x][y]!=null && chessBoardMatrix[x][y].getColor()!=getTurn() )
					Pieces.add(chessBoardMatrix[x][y]);	
		return Pieces;	
	}
	
	public ArrayList<Piece> getMyPieces() {
		ArrayList<Piece> Pieces= new ArrayList<> ();
		for (int y=0; y<8; y++)
			for (int x=0; x<8; x++)
				if(chessBoardMatrix[x][y]!=null && chessBoardMatrix[x][y].getColor()==getTurn() )
					Pieces.add(chessBoardMatrix[x][y]);		
		return Pieces;	
	}
	
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
	
	public void pieceSwap(Position pos,Piece piece){
		
		chessBoardMatrix[pos.x][pos.y]=piece;
		piece.setCoordinates(pos);
	}
}
