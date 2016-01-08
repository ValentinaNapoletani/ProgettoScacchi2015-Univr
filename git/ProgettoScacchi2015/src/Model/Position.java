package Model;

/**Classe che implementa una posizione sulla scacchiera.
 * 
 * @author Napoletani Valentina VR377688
 *
 */

public class Position {
	
	public int x;
	public int y;
	
	/**
	 * Costruttore di una posizione specifica, con le due coordinate intere già stabilite.
	 * @param x L'ascissa.
	 * @param y L'ordinata.
	 */
	public Position(int x,int y){
		this.x=x;
		this.y=y;	
	}
	
	/**
	 * Costruttore di una posizione generica, ancora da inizializzare.
	 */
	public Position(){	
	}
	
	/** Ritorna la coordinata x
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/** Ritorna la coordinata y
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Verifica se due posizioni sulla scacchiera sono uguali,cioè se hanno le stesse coordinate.
	 */
	public boolean equals(Object obj){

		if(obj instanceof Position) 
			if(this.x==((Position)obj).x && this.y==((Position)obj).y)
				return true;
		return false;
	}

}
