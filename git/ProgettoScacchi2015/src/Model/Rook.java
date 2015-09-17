package Model;

import java.awt.Color;
import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Color color, Position coordinates,String unicode) {
		super(color,coordinates,unicode);	
	}

	@Override
	public boolean isLegalMove(Position from,Position to) {
		
		return getValidPosition(from).contains(to) ? true : false;	
	}
	
	@Override
	public ArrayList<Position> getValidPosition(Position initialCoord){
		
		 ArrayList<Position> validPosition = new ArrayList<>();
		
		for(int y=0; y<8;y++)
			if(y!=initialCoord.y)
				validPosition.add(new Position(initialCoord.x,y));
		
		for(int x=0; x<8;x++)
			if(x!=initialCoord.x)
				validPosition.add(new Position(x,initialCoord.y));
	
	
	return validPosition;
	}
	
	public boolean savingKing(ArrayList<Piece> pieces,Position kingCoord){
		
		for (Piece p: pieces)
			if( p.getValidPosition(p.getCoordinates()).contains(kingCoord))
				return true;
		return false;
	}

}
