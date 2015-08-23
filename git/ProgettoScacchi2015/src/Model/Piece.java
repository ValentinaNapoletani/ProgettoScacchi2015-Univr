package Model;

import java.awt.Color;

public abstract class Piece {
	
	private Color color; 
	protected int[] coordinates;
	private String unicode;
	//private boolean mangiato,promosso;
	
	
	public Piece(Color color,int[] coordinates ,String unicode) {
		this.color = color;
		this.coordinates = coordinates;
		this.unicode = unicode;
	} 
	
	public Color getColor() {
		return color;
	}
	
	public int[] getCoordinates() {
		return coordinates;
	}
	
	public String getUnicode() {
		return unicode;
	}
	
	public boolean equals(Object obj){
		if (obj instanceof Piece) {
			if( getColor() == ((Piece) obj).getColor() && getUnicode() == ((Piece) obj).getUnicode() 
					&& getCoordinates()[1] == ((Piece) obj).getCoordinates()[1] && getCoordinates()[0]==((Piece) obj).getCoordinates()[0])
				return true;
		}
		return false;
	} 
	
	public abstract boolean isLegalMove(int[] finalCoord);
	
}