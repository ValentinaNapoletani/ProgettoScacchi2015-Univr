package Model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * L'interfaccia della configurazione,la scacchiera.
 * 
 * @author Napoletani Valentina VR377688
 */

public interface Configuration {
	
	/**
	 * Il metodo ritorna il pezzo ad una determinata posizione.
	 * 
	 * @param coordinates Le coordinate del pezzo.
	 * @return Ritorna il pezzo nella posizione indicata dal parametro.
	 */
	public Piece at(Position coordinates);
	
	/** Muove un pezzo sulla scacchiera.
	 * 
	 * @param initialCoord Le coordiante iniziali da cui spostare il pezzo.
	 * @param finalCoord Le coordiante finali di arrivo del pezzo.
	 * @return La scacchiera con il pezzo spostato.
	 */
	public ChessBoard moveAt(Position initialCoord, Position finalCoord);
	
	/**
	 * Il metodo inizializza i pezzi allo stato iniziale della partita.
	 */
	public void initializePieces();
	
	/** Ritorna la matrice dei pezzi.
	 * 
	 * @return Ritorna la matrice dei pezzi.
	 */
	public Piece[][] getChessboardMatrix();
	
	/** Verifica l'ugualianza tra due scacchiere.
	 * Due scacchiere sono uguali se casella per casella contengono lo stesso pezzo oppure non contengono pezzi.
	 * 
	 * @param obj L'oggetto da confrontare.
	 * @return Ritorna true se le scacchiere sono uguali,false altrimenti.
	 */
	public boolean equals(Object obj);
	
	/** Ritorna il colore del turno.
	 * 
	 * @return Ritorna il colore del turno.
	 */
	public Color getTurn();
	
	/**
	 * Imposta il turno del colore passato come paramentro. Utile per i test.
	 *
	 * @param c Colore del turno da impostare.
	 */
	public void setTurn(Color c);
	
	/**
	 * Imposta il turno controllando quello attuale.
	 */
	public void setTurn();
	
	/** Il metodo ritorna la lista dei pezzi avversari.
	 * 
	 * @return Ritorna l'ArrayList dei pezzi avversari.
	 */
	public ArrayList<Piece> getOpponentsPieces();
	
	/** Il metodo ritorna la lista dei pezzi del colore corrispondente al turno.
	 * 
	 * @return Ritorna l'ArrayList dei pezzi del colore corrispondente al turno.
	 */
	public ArrayList<Piece> getMyPieces();
	
	/**Ritorna la posizione del re del colore corrispondente al turno.
	 * 
	 * @return Ritorna la posizione del re del colore corrispondente al turno.
	 */
	public Position getMyKingCoord();
	
	/**Ritorna la posizione del re avversario.
	 * 
	 * @return Ritorna la posizione del re avversario.
	 */
	public	Position getEnemyKingCoord();
	
	/**
	 * Gestisce la promozione del pedone, modificando la scacchiera attuale.
	 * 
	 * @param pos La posizione in cui scambiare il pedone con un altro pezzo per promuoverlo.
	 * @param piece Il pezzo da far tornare in gioco.
	 */
	public void pieceSwap(Position pos,Piece piece);
}
