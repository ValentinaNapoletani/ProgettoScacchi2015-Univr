package Model;

import java.awt.Color;

public class Pawn extends Piece {
	
	private boolean promoted;
	
	public Pawn(Color color, int[] coordinates ,String unicode) {
		super(color,coordinates,unicode);	
		this.promoted=false;
	} 
	
		
	public boolean getPromoted(){
		return promoted;
	}

	@Override
	public boolean isLegalMove(int[] initialCoord,int[] finalCoord) {
		
		int x = initialCoord[0];
		int y = initialCoord[1];
		
		//Controllo bordo scacchiera
		if(finalCoord[0]>7 || finalCoord[0]<0 || finalCoord[1]>7 || finalCoord[1]<0 )
			return false;
		
		if(y==0 || y==7) {
			promoted=true;
			return false;
		}
		
		if(getColor()==Color.WHITE) {
			if (y==6) 
				return (finalCoord[0]==x+1 && finalCoord[1]==y-1) || (finalCoord[0]==x-1 && finalCoord[1]==y-1)|| (finalCoord[0]==x && (finalCoord[1]==y-1 || finalCoord[1]==y-2));
			else return (finalCoord[0]==x+1 && finalCoord[1]==y-1) || (finalCoord[0]==x-1 && finalCoord[1]==y-1) || (finalCoord[1]==y-1 && finalCoord[0]==x);
			 
				
		}		
		else if (getColor()==Color.BLACK) {
			if (y==1) //doppio passo iniziale
				return (finalCoord[0]==x+1 && finalCoord[1]==y+1) || (finalCoord[0]==x-1 && finalCoord[1]==y+1) || (finalCoord[0]==x && (finalCoord[1]==y+1 || finalCoord[1]==y+2));
			else return (finalCoord[0]==x+1 && finalCoord[1]==y+1) || (finalCoord[0]==x-1 && finalCoord[1]==y+1) || (finalCoord[1]==y+1 && finalCoord[0]==x);
		}
		
		return false;
	}
}