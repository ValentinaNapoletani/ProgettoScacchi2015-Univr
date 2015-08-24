package Model;

//import java.util.ArrayList;

//import View.View;
import javax.swing.*;
import java.awt.Color;

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
		
	    int[] coordinates=new int[2];
		
		//pedoni bianchi
		
		for(int  i = 0; i < 8; i++) {
			coordinates[0]=i;
			coordinates[1]=6;
			chessBoardMatrix[i][6] = new Pawn(Color.white,coordinates,"\u265F");
		}
		
		//pedoni neri
		for( int i = 0; i < 8; i++) {
			coordinates[0]=i;
			coordinates[1]=1;
			chessBoardMatrix[i][1] = new Pawn(Color.black,coordinates,"\u265F");
		}
		
		// altri pezzi neri 
		
		coordinates[0]=0;
		coordinates[1]=0;
		
		chessBoardMatrix[coordinates[0]++][0] = new Rook(Color.black,coordinates,"\u265C");
		chessBoardMatrix[coordinates[0]++][0] = new Knight(Color.black,coordinates,"\u265E");
		chessBoardMatrix[coordinates[0]++][0] = new Bishop(Color.black,coordinates,"\u265D");
		chessBoardMatrix[coordinates[0]++][0] = new Queen(Color.black,coordinates,"\u265B");
		chessBoardMatrix[coordinates[0]++][0] = new King(Color.black,coordinates,"\u265A");
		chessBoardMatrix[coordinates[0]++][0] = new Bishop(Color.black,coordinates,"\u265D");
		chessBoardMatrix[coordinates[0]++][0] = new Knight(Color.black,coordinates,"\u265E");
		chessBoardMatrix[coordinates[0]++][0] = new Rook(Color.black,coordinates,"\u265C");
		
		
		// altri pezzi bianchi 
		
		coordinates[0]=0;
		coordinates[1]=7;
		
		chessBoardMatrix[coordinates[0]++][7] = new Rook(Color.white,coordinates,"\u265C");
		chessBoardMatrix[coordinates[0]++][7] = new Knight(Color.white,coordinates,"\u265E");
		chessBoardMatrix[coordinates[0]++][7] = new Bishop(Color.white,coordinates,"\u265D");
		chessBoardMatrix[coordinates[0]++][7] = new Queen(Color.white,coordinates,"\u265B");
		chessBoardMatrix[coordinates[0]++][7] = new King(Color.white,coordinates,"\u265A");
		chessBoardMatrix[coordinates[0]++][7] = new Bishop(Color.white,coordinates,"\u265D");
		chessBoardMatrix[coordinates[0]++][7] = new Knight(Color.white,coordinates,"\u265E");
		chessBoardMatrix[coordinates[0]][7] = new Rook(Color.white,coordinates,"\u265C");
	}

	
	@Override
	public Piece at(int[] coordinates) {
		if (chessBoardMatrix[coordinates[0]][coordinates[1]]!=null)
		return chessBoardMatrix[coordinates[0]][coordinates[1]];
		else return null;
	}

	@Override
	public ChessBoard moveAt(int[] initialCoord, int[] finalCoord) {
		
		Piece toMove = chessBoardMatrix[initialCoord[0]][initialCoord[1]];
		ChessBoard result=null;
		
		setTurn();
		result = new ChessBoard(getTurn(),chessBoardMatrix);
		
		result.chessBoardMatrix[initialCoord[0]][initialCoord[1]] = null;
		
		//this.chessBoardMatrix[initialCoord[0]][initialCoord[1]]=null;
		
		//this.chessBoardMatrix[finalCoord[0]][finalCoord[1]]=toMove;
		
		//setTurn();
		result.chessBoardMatrix[finalCoord[0]][finalCoord[1]] = toMove;
		//return new ChessBoard( getTurn(), chessBoardMatrix );
		return result;
		
	}
	
	public boolean equals(Object obj) {
		
		int[] coordinates=new int[2];
		boolean equals=false;
		
		if (obj instanceof ChessBoard) 
			for (int y=0;y<8;y++) {
				coordinates[1]=y;
				for (int x=0;x<8;x++) {
					coordinates[0]=x;
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

	
	public void setTurn() {
		if (getTurn()==Color.white)
			turn=Color.black;
		else turn=Color.white;
	}
	
}
