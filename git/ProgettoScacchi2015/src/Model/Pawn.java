package Model;

import java.awt.Color;
import java.util.ArrayList;

/**Classe che implementa il pedone.
 * 
 * @author Napoletani Valentina VR377688
 */
public class Pawn extends Piece {
	
	/**Costruttore del pedone.
	 * 
	 * @param color Il colore del pezzo.
	 * @param coordinates Le coordinate del pezzo.
	 * @param unicode Unicode del pedone.
	 */	
	public Pawn(Color color, Position coordinates ,String unicode) {
		super(color,coordinates,unicode);	
		
	} 
	
	/** Medoto che verifica se una mossa è legale, 
	 *  considerando solamente il modo corretto di mossa per il pedone e non gli altri pezzi.
	 * 
	 * @param from Posizione di partenza del pezzo.
	 * @param to Posizione di arrivo del pezzo.
	 * @return   True se la mossa è corretta,false altrimenti.
	 */
	@Override
	public boolean isLegalMove(Position from,Position to) {
		
		return getValidPosition(from).contains(to) ? true : false;	
	}
	
	/**
	 * Metodo che ritorna la lista di posizioni valide per un pezzo.
	 * 
	 * @param initialCoord La posizione iniziale del pezzo.
	 * @return La lista di posizioni valide per un pezzo.
	 */
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