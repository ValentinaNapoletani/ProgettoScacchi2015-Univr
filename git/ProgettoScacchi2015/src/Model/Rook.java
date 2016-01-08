package Model;

import java.awt.Color;
import java.util.ArrayList;

/**Classe che implementa la torre.
 * 
 * @author Napoletani Valentina VR377688
 */
public class Rook extends Piece {

	/**Costruttore della torre.
	 * 
	 * @param color Il colore del pezzo.
	 * @param coordinates Le coordinate del pezzo.
	 * @param unicode Unicode della torre.
	 */	
	public Rook(Color color, Position coordinates,String unicode) {
		super(color,coordinates,unicode);	
	}

	/** Medoto che verifica se una mossa è legale, 
	 *  considerando solamente il modo corretto di mossa per la torre e non gli altri pezzi.
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
		
		 ArrayList<Position> validPosition = new ArrayList<>();
		
		for(int y=0; y<8;y++)
			if(y!=initialCoord.y)
				validPosition.add(new Position(initialCoord.x,y));
		
		for(int x=0; x<8;x++)
			if(x!=initialCoord.x)
				validPosition.add(new Position(x,initialCoord.y));
	
	
	return validPosition;
	}
	
}
