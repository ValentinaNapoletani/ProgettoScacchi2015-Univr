package Model;

import java.awt.Color;
import java.util.ArrayList;

/**Classe che implementa il cavallo.
 * 
 * @author Napoletani Valentina VR377688
 */
public class Knight extends Piece {
	
	/**Costruttore del cavallo.
	 * 
	 * @param color Il colore del pezzo.
	 * @param coordinates Le coordinate del pezzo.
	 * @param unicode Unicode del cavallo.
	 */	
	public Knight(Color color, Position coordinates ,String unicode) {
		super(color,coordinates,unicode);	
	}
	
	/** Medoto che verifica se una mossa è legale, 
	 *  considerando solamente il modo corretto di mossa per il cavallo e non gli altri pezzi.
	 * 
	 * @param from Posizione di partenza del pezzo.
	 * @param to Posizione di arrivo del pezzo.
	 * @return   True se la mossa è corretta,false altrimenti.
	 */
	@Override
	public boolean isLegalMove(Position from, Position to) {
		
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
}