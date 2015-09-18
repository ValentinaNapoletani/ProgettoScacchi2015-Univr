package Model;

import java.awt.Color;
import java.util.ArrayList;


public abstract class Piece {
	
	private Color color; 
	protected Position coordinates;
	private String unicode;

	//private boolean mangiato,promosso;
	
	
	public Piece(Color color,Position coordinates ,String unicode ) {
		this.color = color;
		this.coordinates = coordinates;
		this.unicode = unicode;
	
	} 
	
	public Color getColor() {
		return color;
	}
	
	public Position getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Position coord){
		coordinates.x=coord.x;
		coordinates.y=coord.y;
	}
	
	
	public String getUnicode() {
		return unicode;
	}
	

	public boolean equals(Object obj){
		if (obj instanceof Piece) {
			if( getColor() == ((Piece) obj).getColor() && getUnicode() == ((Piece) obj).getUnicode() 
					&& getCoordinates().y == ((Piece) obj).getCoordinates().y && getCoordinates().x==((Piece) obj).getCoordinates().x)
				return true;
		}
		return false;
	} 
	
	public abstract boolean isLegalMove(Position from,Position to);
	public abstract ArrayList<Position> getValidPosition(Position initialCoord);
	
}