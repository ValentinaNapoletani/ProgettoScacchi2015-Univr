package Model;

import java.awt.Color;
import java.util.ArrayList;

public class Pawn extends Piece {
	
	public Pawn(Color color, Position coordinates ,String unicode) {
		super(color,coordinates,unicode);	
		
	} 
	
	@Override
	public boolean isLegalMove(Position from,Position to) {
		
		return getValidPosition(from).contains(to) ? true : false;	
	}
	
	@Override
	public ArrayList<Position> getValidPosition(Position initialCoord){
		
		int x=initialCoord.x;
		int y=initialCoord.y;
		ArrayList<Position> validPosition = new ArrayList<>();
		
		if(getColor()==Color.WHITE) {
			validPosition.add(new Position(x-1,y-1));
			validPosition.add(new Position(x+1,y-1));
			validPosition.add(new Position(x,y-1));
			 
			if (initialCoord.y==6)
				validPosition.add(new Position(x,y-2));
		}
		else if (getColor()==Color.BLACK) {
			validPosition.add(new Position(x-1,y+1));
			validPosition.add(new Position(x+1,y+1));
			validPosition.add(new Position(x,y+1));
					
			if (initialCoord.y==1)
				validPosition.add(new Position(x,y+2));
		}
		
		return validPosition;
	}

}