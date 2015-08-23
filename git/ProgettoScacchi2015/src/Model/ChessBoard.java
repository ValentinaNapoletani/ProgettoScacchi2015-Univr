package Model;

//import java.util.ArrayList;

//import View.View;
import javax.swing.*;
import java.awt.Color;

public class ChessBoard implements Configuration {

	private Piece[][] chessBoardMatrix;
	int[] coordinates=new int[2];
	
	public ChessBoard(int i) {
		
		this.chessBoardMatrix = new Piece[8][8];
		 
		initializePieces();
	}
	
	public ChessBoard() {
		
		this.chessBoardMatrix = new Piece[8][8];
		
	}
	
	public void initializePieces(){
		
		
		//pedoni bianchi
		
		for(int i = 0; i < 8; i++) {
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
		
		//altri pezzi bianchi
		
		coordinates[1]=7;
		coordinates[0]=0;
		
		chessBoardMatrix[coordinates[0]][7] = new Rook(Color.white,coordinates,"\u265C");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new Knight(Color.white,coordinates,"\u265E");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new Bishop(Color.white,coordinates,"\u265D");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new Queen(Color.white,coordinates,"\u265B");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new King(Color.white,coordinates,"\u265A");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new Bishop(Color.white,coordinates,"\u265D");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new Knight(Color.white,coordinates,"\u265E");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][7] = new Rook(Color.white,coordinates,"\u265C");
		
		//altri pezzi neri
		
		coordinates[1]=0;
		coordinates[0]=0;
		
		chessBoardMatrix[coordinates[0]][0] = new Rook(Color.black,coordinates,"\u265C");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new Knight(Color.black,coordinates,"\u265E");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new Bishop(Color.black,coordinates,"\u265D");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new Queen(Color.black,coordinates,"\u265B");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new King(Color.black,coordinates,"\u265A");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new Bishop(Color.black,coordinates,"\u265D");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new Knight(Color.black,coordinates,"\u265E");
		coordinates[0]++;
		chessBoardMatrix[coordinates[0]][0] = new Rook(Color.black,coordinates,"\u265C");
	
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
		
		chessBoardMatrix[initialCoord[0]][initialCoord[1]]=null;
		
		chessBoardMatrix[finalCoord[0]][finalCoord[1]]=toMove;
		
		//NB:cotrolla il return
		return null;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof ChessBoard) 
			for (int y=0;y<8;y++) {
				coordinates[1]=y;
				for (int x=0;x<8;x++) {
					coordinates[0]=x;
					if (chessBoardMatrix[x][y]==null ^ ((ChessBoard) obj).at(coordinates)==null )
						return false;
					if(chessBoardMatrix[x][y].equals(((ChessBoard) obj).at(coordinates)))
						return true;
					else return false;
				}
			}
		return false;	
	}
	
	
	
}
