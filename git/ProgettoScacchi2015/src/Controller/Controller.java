package Controller;

import java.awt.event.*;
import Model.Position;
import Model.Piece;
import View.PromotionDialog;

/**
 * Interfaccia del controller.
 * @author Napoletani Valentina VR377688
 *
 */
public interface Controller {
	
	/**
	 * Gestisce il click dei bottoni della scacchiera.
	 * 
	 * @param x La coordinata x del bottone su cui l'utente ha cliccato.
	 * @param y La coordinata y del bottone su cui l'utente ha cliccato.
	 * @param e L'evento generato dal click del bottone.
	 */
	void onClick(int x, int y, ActionEvent e);
	
	/**
	 * Gestisce l'evento generato al click del bottone di nuovo gioco da parte dell'utente
	 * e genera una finestra dove poter inserire il nome dei giocatori.
	 */
	void setupNewGame();
	/**
	 * Gestisce l'evento generato al click del bottone Quit, chiudendo la finestra di gioco.
	 */
	void quitGame();
	
	/**
	 * Gestisce l'evento generato al click del bottone di start da parte dell'utente.
	 */
	void start();
	
	/**
	 *  Gestisce l'evento di promozione del pedone.
	 *  
	 *  @param pos La posizione in cui scambiare il pedone con un altro pezzo per promuoverlo.
	 *  @param piece Il pezzo da far tornare in gioco.
	 *  @param prom La finestra di promozone del pedone.
	 */
	void promotion(Position pos,Piece piece,PromotionDialog prom);
	
} 
