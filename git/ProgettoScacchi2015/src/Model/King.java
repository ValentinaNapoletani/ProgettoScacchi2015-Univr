package Model;

import java.awt.Color;
import java.util.ArrayList;

/**Classe che implementa l'alfiere.
 * 
 * @author Napoletani Valentina VR377688
 */

public class King extends Piece {
	
	/**Costruttore del re.
	 * 
	 * @param color Il colore del pezzo.
	 * @param coordinates Le coordinate del pezzo.
	 * @param unicode Unicode del re.
	 */	
	public King(Color color, Position coordinates,String unicode) {
		super(color,coordinates,unicode);	
	} 

	/** Medoto che verifica se una mossa è legale, 
	 *  considerando solamente il modo corretto di mossa per il re e non gli altri pezzi.
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