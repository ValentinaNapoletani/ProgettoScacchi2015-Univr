package Controller;

import Model.*;
import java.awt.Color;
import View.*;

/**
 * Classe che gestisce i movimenti sulla scacchiera.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public class Mover {
	
	private Model model;
	private View view;
	private final ChessBoardController controller;
	private final CheckMateController controller2;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param model Il modello.
	 * @param view La vista.
	 * @param controller Il controllor che gestisce l'interazione vista-modello.
	 * @param controller2 Il controller controlla la gestione dello scacco e dello scacco matto. 
	 */
	public Mover(Model model,View view,ChessBoardController controller, CheckMateController controller2) {
		
		this.model=model;
		this.view=view;
		this.controller=controller;
		this.controller2=controller2;
		
	}
	
	/**
	 * Gestisce il movimento dei pezzi sulla scacchiera, verificando se la mossa sia possibile.
	 * Il metodo inoltre aggiunge un eventuale pezzo mangiato ad una lista.
	 *  
	 * @param from Posizione di partenza del pezzo.
	 * @param coordinates Posizione di arrivo del pezzo.
	 * @param o Il bottone con le coordinate di arrivo.
	 */
	public void moveAt(Position from,Position coordinates,Object o){
		 if (isLegalMove(from,coordinates) ) {	 
			 //se mangio un pezzo lo aggiungo alla lista dei mangiati
			 if ( model.at(coordinates) != null && !(model.at(coordinates) instanceof King) ) {
					if( model.at(coordinates).getColor()== Color.white)
						controller.getwhitePieces().add(model.at(coordinates));
					else controller.getblackPieces().add(model.at(coordinates));
			 
			 }
			 model.setConfiguration(model.getChessBoard().moveAt(from,coordinates));
			 
		 }
		 else view.illegalMove(o);
			 
	}
	
	/**
	 * Verifica se la mossa è corretta.
	 * 
	 * @param from Posizione di partenza del pezzo.
	 * @param coordinates Posizione di arrivo del pezzo.
	 * @return true se la mossa è corretta,false altrimenti.
	 */
	public boolean isLegalMove(Position from,Position coordinates){
		
		//La destinazione non contiene una pedina con colore uguale al turno
		 if (model.at(coordinates)==null || model.at(coordinates).getColor() != model.at(from).getColor()) {
			 //controllo mosse pedone
			 if ( model.at(from) instanceof Pawn ) {
			 
				 if ( model.at(coordinates) == null && coordinates.x == from.x)
					 return true;
				 else if ( model.at(coordinates) != null && coordinates.x != from.x)
					 return true;
				 else return false;
			 }
		 		 
			 //controllo che non si saltino pedine (escluso cavallo)
			 if (!(model.at(from) instanceof Knight)) {
				 if(freePath(from,coordinates))
					 	return true;
			 }
			 else if (model.at(from) instanceof Knight)
				 return true;
		 }
		 return false;
	}
	
	/** Verifica se c'e un vincitore e ne ritorna il colore.
	 * 
	 * @param enemy Il pezzo che mette in scacco matto il re.
	 * @return Il colore del vincitore.
	 */
	public Color therIsAWinner(Piece enemy){
		
		Color loser=model.getChessBoard().getTurn();
		Color winner;
		
		if(controller2.isCheckMate(enemy)){
			if (loser==Color.white)
			winner=Color.black;
			else winner=Color.white;
			return winner;
		}
		return null;
	}
	
	/**
	 * Calcola se il percorso tra due pezzi è vuoto. 
	 * 
	 * @param from Coordinate iniziale del percorso.
	 * @param to Coordinata finale del precorso.
	 * @return true se il percorso è vuoto,fase altrimenti.
	 */
	public boolean freePath(Position from ,Position to){
		
		Position pos=new Position(to.x,to.y);
		
	    //spostamento verticale
		if(to.x == from.x) {
			pos.x=to.x;
			//in giù
			if(to.y > from.y) {
				pos.y=(from.y)+1;
				while(pos.y != to.y) {
					if(model.at(pos) != null)
						return false;
					(pos.y)++;
				}
			}
			//in su
			else if(to.y < from.y) {
				pos.y=(from.y)-1;
				while(pos.y!= to.y) {
					if(model.at(pos) != null)
						return false;
					(pos.y)--;
				}
			}
		}
		
		//spostamento orizzontale
		else if(to.y == from.y) {
			pos.y=to.y;
			//a dx
			if(to.x > from.x) {
				pos.x=(controller.getFrom().x)+1;
				while(pos.x != to.x) {
					if(model.at(pos) != null)
						return false;
				pos.x++;
				}
			}
			else if(to.x < from.x) {
				pos.x=(from.x)-1;
				while(pos.x != to.x) {
					if(model.at(pos) != null)
						return false;
					(pos.x)--;
				}
			}
			
		}
		//spostamento obliquo
		else {
			//vado verso dx
			if(to.x > from.x) {
				if(to.y < from.y){ //su
					pos.y=(from.y)-1;
					pos.x=(from.x)+1;
					while(pos.x != to.x && pos.y != to.y) {
						if(model.at(pos) != null)
							return false;
						(pos.x)++;
						(pos.y)--;
					}
				}	
				else if (to.y > from.y) {//giù
					pos.y=(from.y)+1;
					pos.x=(from.x)+1;
					while(pos.x != to.x && pos.y != to.y) {
						if(model.at(pos) != null)
							return false;
						(pos.x)++;
						(pos.y)++;
					}
				}
			}
			//vado verso sx
			else if(to.x < from.x) {
				if(to.y < from.y){ //su
					pos.y=(from.y)-1;
					pos.x=(from.x)-1;
					while(pos.x != to.x && pos.y != to.y) {
						if(model.at(pos) != null)
							return false;
						(pos.x)--;
						(pos.y)--;
					}	
				}
				else if (to.y > from.y) {//giù
					pos.y=(from.y)+1;
					pos.x=(from.x)-1;
					while(pos.x != to.x && pos.y != to.y) {
						if(model.at(pos) != null)
							return false;
						(pos.x)--;
						(pos.y)++;
					}
				}	
			}
		}
		
		return true;
	}
} 
