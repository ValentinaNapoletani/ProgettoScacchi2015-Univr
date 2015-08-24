package Model;

import java.awt.Color;

public class Rook extends Piece {
	
	public Rook(Color color, int[] coordinates,String unicode) {
		super(color,coordinates,unicode);	
	}

	@Override
	public boolean isLegalMove(int[] initialCoord, int[] finalCoord) {
		
		int x = initialCoord[0];
		int y = initialCoord[1];

		//Controllo bordo scacchiera
		if(finalCoord[0]>7 || finalCoord[0]<0 || finalCoord[1]>7 || finalCoord[1]<0 )
			return false;
		
		return finalCoord[0]==x || finalCoord[1]==y;
	}

}
