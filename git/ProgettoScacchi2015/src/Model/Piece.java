package Model;

import java.awt.Color;
import java.util.ArrayList;

/**Classe astratta che descrive un pezzo sulla scacchiera.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public abstract class Piece {
	
	private Color color; 
	protected Position coordinates;
	private String unicode;
		
	/**
	 * Costruttore del pezzo.
	 * 
	 * @param color Il colore del pezzo.
	 * @param coordinates Le coordinate del pezzo.
	 * @param unicode Unicode del pezzo.
	 */
	public Piece(Color color,Position coordinates ,String unicode ) {
		this.color = color;
		this.coordinates = coordinates;
		this.unicode = unicode;
	
	} 
	
	/**
	 * Metodo che ritorna il colore del pezzo.
	 * @return color Il colore del pezzo.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Metodo che ritorna le coordinate del pezzo.
	 * @return coordinates Le coordinate del pezzo.
	 */
	public Position getCoordinates() {
		return coordinates;
	}
	
	/**Imposta le coordinate di un pezzo.
	 * 
	 * @param coord Le coordinate da impostare.
	 */
	public void setCoordinates(Position coord){
		coordinates.x=coord.x;
		coordinates.y=coord.y;
	}
	
	/**
	 * Metodo che ritorna lo unicode del pezzo.
	 * @return unicode Unicode del pezzo.
	 */
	public String getUnicode() {
		return unicode;
	}
	
	/** Verifica l'ugualianza tra due pezzi.
	 * Due pezzi sono uguali se hanno lo stesso colore,coordinata e unicode.
	 * 
	 * @param obj L'oggetto da confrontare.
	 * @return    True se i pezzi sono uguali,false altrimenti.
	 */
	public boolean equals(Object obj){
		if (obj instanceof Piece) {
			if( getColor() == ((Piece) obj).getColor() && getUnicode() == ((Piece) obj).getUnicode() 
					&& getCoordinates().y == ((Piece) obj).getCoordinates().y && getCoordinates().x==((Piece) obj).getCoordinates().x)
				return true;
		}
		return false;
	} 
	
	/** Medoto astratto che verifica se una mossa è legale per un determinato pezzo, 
	 *  considerando solamente il modo corretto di mossa per lo specifico pezzo.
	 * 
	 * @param from Posizione di partenza del pezzo.
	 * @param to Posizione di arrivo del pezzo.
	 * @return   True se la mossa è corretta,false altrimenti.
	 */
	public abstract boolean isLegalMove(Position from,Position to);
	
	/**
	 * Metodo astratto che ritorna la lista di posizioni valide per un pezzo.
	 * 
	 * @param initialCoord La posizione iniziale del pezzo.
	 * @return La lista di posizioni valide per un pezzo.
	 */
	public abstract ArrayList<Position> getValidPosition(Position initialCoord);
	
}