package Model;

import java.awt.Color;

public class King extends Piece {
	
	public King(Color color, int[] coordinates,String unicode) {
		super(color,coordinates,unicode);	
	} 

	@Override
	public boolean isLegalMove(int[] finalCoord) {
		
		int x = getCoordinates()[0];
		int y = getCoordinates()[1];
		
		//Controllo bordo scacchiera
				if(finalCoord[0]>7 || finalCoord[0]<0 || finalCoord[1]>7 || finalCoord[1]<0 )
					return false;
				
		return (finalCoord[0]==x+1 && finalCoord[1]==y+1)|| (finalCoord[0]==x-1 && finalCoord[1]==y-1) || 
			   (finalCoord[0]==x+1 && finalCoord[1]==y-1) || (finalCoord[0]==x-1 && finalCoord[1]==y-1) ||
			    (finalCoord[0]==x && finalCoord[1]==y-1) || (finalCoord[0]==x && finalCoord[1]==y+1) ||
			    (finalCoord[0]==x+1 && finalCoord[1]==y) || (finalCoord[0]==x-1 && finalCoord[1]==y);
	}

}
