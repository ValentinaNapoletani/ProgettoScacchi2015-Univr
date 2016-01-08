package View;

import Model.*;
import java.awt.Color;
import Controller.*;

/**
 * L'interfaccia della vista.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public interface View {
	
	/**
	 * Ritorna il modello.
	 * @return il modello.
	 */
	Model getModel();
	
	/**
	 * Ritorna la finestra di gioco.
	 * @return la finestra di gioco.
	 */
	ChessFrame getFrame();
	
	/**
	 * Imposta il controller.
	 */
	void setController(ChessBoardController controller);
	
	/** 
	 * Mostra la finestra finale, che mosta il vincitore.
	 * 
	 * @param color Il colore del vincitore
	 * @param hiframe La finestra iniziale con il nome dei giocatori.
	 */
	void showFinalDialog(Color color,HiFrame hiFrame);
	
	/**
	 * Mostra la finestra di promozione del pedone.
	 * 
	 * @param p La posizione del pedone da promuovere.
	 */
	void showPromotionDialog(Position p);
	
	/**
	 * Seleziona la casella cliccata dal giocatore, colorandone il bordo di blu.
	 * @param o Il bottone cliccato dall'utente.
	 */
	void selectCase(Object o);
	
	/** Ripristina il colore della casella di partenza dopo che la mossa è stata effettuata oppure dopo un ripensamento del giocatore sulla pedina da spostare.
	 * @param o Il bottone il cui colore del bordo è da ripristinare.
	 */
	void clearCase(Object o);
	
	/**Colora il bordo del bottone di arrivo di rosso se la mossa non è possibile.
	 * @param o Il bottone il cui colore del bordo è da ripristinare.
	 */
	void illegalMove(Object o);
	
	/**
	 * Gestisce i cambiamenti di configurazione del modello e modifica adeguatamente la vista.
	 * Modifica inoltre l'etichetta del turno.
	 */
	void onConfigurationChange();

}
