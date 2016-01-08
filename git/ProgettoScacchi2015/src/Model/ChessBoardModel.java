package Model;

import View.View;

/**
 * Classe che implementa il modello.
 * 
 * @author Napoletani Valentina VR377688
 */

public class ChessBoardModel implements Model {
	
	
	private ChessBoard chessBoard;
	private View view;
	
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param chessboard La configurazione della scacchiera
	 */
	public ChessBoardModel( ChessBoard chessBoard ){
		this.chessBoard=chessBoard;
	}

	/**
	 * Metodo che gestisce informazioni sullo stato.
	 * 
	 * @param coordinates Coordinate sulla scacchiera.
	 * @return Ritorna il pezzo alla posizione passata come parametro. 
	 */
	@Override
	public Piece at(Position coordinates) {
		return chessBoard.at(coordinates);
	}

	
	/**
	 * Metodo che gestisce informazioni sullo stato.
	 *
	 * @return Ritorna la scacchiera attuale. 
	 */
	@Override
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	/**
	 * Metodo che gestisce modifiche dello stato.
	 * Imposta la configuarazione della scacchiera
	 * 
	 * @param chessBoard Scacchiera da impostare.
	 */
	@Override
	public void setConfiguration(ChessBoard chessBoard) {
		if(!(this.getChessBoard().equals(chessBoard))) {
			this.chessBoard = chessBoard;
			if( view != null)
				view.onConfigurationChange();
		}
	}

	/**
	 * Metodo che gestisce modifiche dello stato.
	 * Imposta la vista della scacchiera
	 * 
	 * @param chessBoard La vista da impostare.
	 */
	@Override
	public void setView(View view) {
		this.view=view;
	}
	
	/**
	 * Gestisce la promozione del pedone. Sostituisce il pezzo nella scacchiera e cambia la vista.
	 * 
	 *@param pos La posizione in cui scambiare il pedone con un altro pezzo per promuoverlo.
	 * @param piece Il pezzo da far tornare in gioco.
	 */
	@Override
	public void pieceSwap(Position pos,Piece piece){
		chessBoard.pieceSwap(pos,piece);
		view.onConfigurationChange();
	}

}
