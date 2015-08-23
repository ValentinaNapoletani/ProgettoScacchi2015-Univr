package Model;

import java.awt.Color;

public class Bishop extends Piece {
	
	public Bishop(Color color, int[] coordinates, String unicode) {
		super(color,coordinates,unicode);	
	}

	@Override
	public boolean isLegalMove(int[] finalCoord) {
		
		int x = getCoordinates()[0];
		int y = getCoordinates()[1];

		//Controllo bordo scacchiera
		if(finalCoord[0]>7 || finalCoord[0]<0 || finalCoord[1]>7 || finalCoord[1]<0 )
			return false;
		
		return x+y==finalCoord[0]+finalCoord[1] || x-y==finalCoord[0]-finalCoord[1];
		
	}

}
