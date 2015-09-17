package Controller;

import Model.*;
import java.awt.Color;

public class Mover {
	
	private Model model;
	private final ChessBoardController controller;
	private final CheckMateController controller2;
	
	public Mover(Model model,ChessBoardController controller, CheckMateController controller2) {
		
		this.model=model;
		this.controller=controller;
		this.controller2=controller2;
		
	}
	
	public void moveAt(Position coordinates){
		 if (isLegalMove(coordinates) ) {	 
			 if ( model.at(coordinates) != null && !(model.at(coordinates) instanceof King) ) {
					if( model.at(coordinates).getColor()== Color.white)
						controller.getwhitePieces().add(model.at(coordinates));
					else controller.getblackPieces().add(model.at(coordinates));
			 
			 }
			 model.setConfiguration(model.getChessBoard().moveAt(controller.getFrom(),coordinates));
			 
		 }
		// else view.illegalMove(coordinates); 
			 
	}
	
	public boolean isLegalMove(Position coordinates){
		
		//La destinazione non contiene una pedina con colore uguale al turno
		 if (model.at(coordinates)==null || model.at(coordinates).getColor() != model.getChessBoard().getTurn()) {
			 //controllo mosse pedone
			 if ( model.at(controller.getFrom()) instanceof Pawn ) {
			 
				 if ( model.at(coordinates) == null && coordinates.x == controller.getFrom().x)
					 return true;
				 else if ( model.at(coordinates) != null && coordinates.x != controller.getFrom().x)
					 return true;
				 else return false;
			 }
		 		 
			 //controllo che non si saltino pedine (escluso cavallo)
			 if (!(model.at(controller.getFrom()) instanceof Knight)) {
				 if(freePath(coordinates))
					 	return true;
			 }
			 else if (model.at(controller.getFrom()) instanceof Knight)
				 return true;
		 }
		 return false;
	}
	
	//verifica se c'e un vincitore e ne ritorna il colore
	public Color therIsAWinner(){
		
		Color loser=model.getChessBoard().getTurn();
		Color winner;
		
		if(controller2.isCheckMate()){
			if (loser==Color.white)
			winner=Color.black;
			else winner=Color.white;
			return winner;
		}
		return null;
	}
	
	public boolean freePath(Position coordinates){
		Position pos=new Position(coordinates.x,coordinates.y);
		
	    //spostamento verticale
		if(coordinates.x == controller.getFrom().x) {
			pos.x=coordinates.x;
			//in giù
			if(coordinates.y > controller.getFrom().y) {
				pos.y=(controller.getFrom().y)+1;
				while(pos.y != coordinates.y) {
					if(model.at(pos) != null)
						return false;
					(pos.y)++;
				}
			}
			//in su
			else if(coordinates.y < controller.getFrom().y) {
				pos.y=(controller.getFrom().y)-1;
				while(pos.y!= coordinates.y) {
					if(model.at(pos) != null)
						return false;
					(pos.y)--;
				}
			}
		}
		
		//spostamento orizzontale
		else if(coordinates.y == controller.getFrom().y) {
			pos.y=coordinates.y;
			//a dx
			if(coordinates.x > controller.getFrom().x) {
				pos.x=(controller.getFrom().x)+1;
				while(pos.x != coordinates.x) {
					if(model.at(pos) != null)
						return false;
				pos.x++;
				}
			}
			else if(coordinates.x < controller.getFrom().x) {
				pos.x=(controller.getFrom().x)-1;
				while(pos.x != coordinates.x) {
					if(model.at(pos) != null)
						return false;
					(pos.x)--;
				}
			}
			
		}
		//spostamento obliquo
		else {
			//vado verso dx
			if(coordinates.x > controller.getFrom().x)
				if(coordinates.y < controller.getFrom().y){ //su
					pos.y=(controller.getFrom().y)-1;
					pos.x=(controller.getFrom().x)+1;
					while(pos.x != coordinates.x && pos.y != coordinates.y) {
						if(model.at(pos) != null)
							return false;
						(pos.x)++;
						(pos.y)--;
					}
				}		
				else if (coordinates.y > controller.getFrom().y) {//giù
					pos.y=(controller.getFrom().y)+1;
					pos.x=(controller.getFrom().x)+1;
					while(pos.x != coordinates.x && pos.y != coordinates.y) {
						if(model.at(pos) != null)
							return false;
						(pos.x)++;
						(pos.y)++;
					}
				}
				//vado verso sx
				else if(coordinates.x < controller.getFrom().x)
					if(coordinates.y < controller.getFrom().y){ //su
						pos.y=(controller.getFrom().y)-1;
						pos.x=(controller.getFrom().x)-1;
						while(pos.x != coordinates.x && pos.y != coordinates.y) {
							if(model.at(pos) != null)
								return false;
							(pos.x)--;
							(pos.y)--;
						}	
					}
					else if (coordinates.y > controller.getFrom().y) {//giù
						pos.y=(controller.getFrom().y)+1;
						pos.x=(controller.getFrom().x)-1;
						while(pos.x != coordinates.x && pos.y != coordinates.y) {
							if(model.at(pos) != null)
								return false;
							(pos.x)--;
							(pos.y)++;
						}
					}	
		}
		
		return true;
	}
} 
