package Model;

import java.awt.Color;
import java.util.ArrayList;

public class Bishop extends Piece {
	
	public Bishop(Color color, Position coordinates, String unicode) {
		super(color,coordinates,unicode);	
	}

	@Override
	public boolean isLegalMove(Position from, Position to) {
		
		return getValidPosition(from).contains(to) ? true : false;
		
	}
	
	@Override
	public ArrayList<Position> getValidPosition(Position initialCoord){
				
		int x2=initialCoord.x;
		int y2=initialCoord.y;
		ArrayList<Position> validPosition = new ArrayList<>();
		
		//alto dx
		while(x2<8 && y2>0)
			if(x2!=initialCoord.x && y2!=initialCoord.y)
				validPosition.add(new Position(x2++,y2--));
			else {
				x2++;
				y2--;
			}
				
		//alto sx
		x2=initialCoord.x;
		y2=initialCoord.y;
		while(x2>0 && y2>0)
			if(x2!=initialCoord.x && y2!=initialCoord.y)
				validPosition.add(new Position(x2--,y2--));
			else {
				x2--;
				y2--;
			}
				
		//basso sx
		x2=initialCoord.x;
		y2=initialCoord.y;
		while(x2>0 && y2<8)
			if(x2!=initialCoord.x && y2!=initialCoord.y)
				validPosition.add(new Position(x2--,y2++));
			else {
				x2--;
				y2++;
			}
				
		//basso dx
		x2=initialCoord.x;
		y2=initialCoord.y;
		while(x2<8 && y2<8)
			if(x2!=initialCoord.x && y2!=initialCoord.y)
				validPosition.add(new Position(x2++,y2++));
			else {
				x2++;
				y2++;
			}
		
		return validPosition;
	}
	
	public boolean savingKing(ArrayList<Piece> pieces,Position kingCoord){
		
		for (Piece p: pieces)
			if( p.getValidPosition(p.getCoordinates()).contains(kingCoord))
				return true;
		return false;
	}
}