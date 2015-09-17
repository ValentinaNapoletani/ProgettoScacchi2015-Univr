package Model;

import java.awt.Color;
import java.util.ArrayList;

public class King extends Piece {
	
	public King(Color color, Position coordinates,String unicode) {
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
		
		for (int y=y2-1;y<=y2+1;y++)
			for(int x=x2-1;x<=x2+1;x++)
				if(x>=0 && x<8 && y>=0 && y<8){
					validPosition.add(new Position(x,y));
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