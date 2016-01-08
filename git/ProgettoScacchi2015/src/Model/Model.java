package Model;

import View.*;

/**
 * L'interfaccia del modello.
 * 
 * @author Napoletani Valentina VR377688
 */

public interface Model {
	
	//status information
	/**
	 * Metodo che gestisce informazioni sullo stato.
	 * 
	 * @param coordinates Coordinate sulla scacchiera.
	 * @return Ritorna il pezzo alla posizione passata come parametro. 
	 */
	public Piece at(Position coordinates);
	
	/**
	 * Metodo che gestisce informazioni sullo stato.
	 *
	 * @return Ritorna la scacchiera attuale. 
	 */
	public ChessBoard getChessBoard();
	
	//change status
	/**
	 * Metodo che gestisce modifiche dello stato.
	 * Imposta la configuarazione della scacchiera
	 * 
	 * @param chessBoard Scacchiera da impostare.
	 */
	public void setConfiguration(ChessBoard chessBoard);
	
	/**
	 * Metodo che gestisce modifiche dello stato.
	 * Imposta la vista della scacchiera
	 * 
	 * @param chessBoard La vista da impostare.
	 */
	public void setView(View view);
	
	/**
	 * Metodo che gestisce modifiche dello stato.
	 * Gestisce la promozione del pedone, modificando la scacchiera attuale.
	 * 
	 * @param pos La posizione in cui scambiare il pedone con un altro pezzo per promuoverlo.
	 * @param piece Il pezzo da far tornare in gioco.
	 */
	public void pieceSwap(Position pos,Piece piece);
}
