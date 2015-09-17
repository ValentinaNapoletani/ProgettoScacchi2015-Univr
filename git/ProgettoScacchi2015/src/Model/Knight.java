package Model;

import java.awt.Color;
import java.util.ArrayList;

public class Knight extends Piece {
	

	public Knight(Color color, Position coordinates ,String unicode) {
		super(color,coordinates,unicode);	
	}

	@Override
	public boolean isLegalMove(Position from, Position to) {
		
		return getValidPosition(from).contains(to) ? true : false;
		
	}
	
	@Override
	public ArrayList<Position> getValidPosition(Position initialCoord){
		
		int x=initialCoord.x;
		int y=initialCoord.y;
		ArrayList<Position> validPosition = new ArrayList<>();
		
			validPosition.add(new Position(x-1,y-2));
			validPosition.add(new Position(x+1,y-2));
			validPosition.add(new Position(x-1,y+2));
			validPosition.add(new Position(x+1,y+2));
			validPosition.add(new Position(x-2,y-1));
			validPosition.add(new Position(x+2,y-1));
			validPosition.add(new Position(x-2,y+1));
			validPosition.add(new Position(x+2,y+1));
			
			for(int i=0;i<validPosition.size();i++)
				if(validPosition.get(i).x<0 || validPosition.get(i).y<0 || validPosition.get(i).x>7 || validPosition.get(i).y>7)
					validPosition.remove(i);
			 
		return validPosition;
	}

	public boolean savingKing(ArrayList<Piece> pieces,Position kingCoord){
		
		for (Piece p: pieces)
			if( p.getValidPosition(p.getCoordinates()).contains(kingCoord))
				return true;
		return false;
	}
}
