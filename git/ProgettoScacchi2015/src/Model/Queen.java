package Model;

import java.awt.Color;
import java.util.ArrayList;

/**Classe che implementa la regina.
 * 
 * @author Napoletani Valentina VR377688
 */
public class Queen extends Piece {
	
	/**Costruttore della regina.
	 * 
	 * @param color Il colore del pezzo.
	 * @param coordinates Le coordinate del pezzo.
	 * @param unicode Unicode della regina.
	 */	
	public Queen(Color color, Position coordinates,String unicode) {
		super(color,coordinates,unicode);	
	}

	/** Medoto che verifica se una mossa è legale, 
	 *  considerando solamente il modo corretto di mossa per la regina e non gli altri pezzi.
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
		
		int x2=initialCoord.x;
		int y2=initialCoord.y;
		ArrayList<Position> validPosition = new ArrayList<>();
	
		
		for(int y=0; y<8;y++){
			if(y!=initialCoord.y)
				validPosition.add(new Position(initialCoord.x,y));
		}
		
		for(int x=0; x<8;x++){
			if(x!=initialCoord.x)
				validPosition.add(new Position(x,initialCoord.y));
		}
		//alto dx
		while(x2<8 && y2>=0)
			if(x2!=initialCoord.x && y2!=initialCoord.y)
				validPosition.add(new Position(x2++,y2--));
			else {
				x2++;
				y2--;
			}
		
		//alto sx
		x2=initialCoord.x;
		y2=initialCoord.y;
		while(x2>=0 && y2>=0)
			if(x2!=initialCoord.x && y2!=initialCoord.y)
				validPosition.add(new Position(x2--,y2--));
			else {
				x2--;
				y2--;
			}
		
		//basso sx
		x2=initialCoord.x;
		y2=initialCoord.y;
		while(x2>=0 && y2<8)
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
	
}