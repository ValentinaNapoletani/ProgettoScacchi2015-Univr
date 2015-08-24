package Controller;

import View.*;
import Model.*;
import java.awt.Color;

public class Mover {
	
	private Model model;
	private final View view;
	private final ChessBoardController controller;
	
	public Mover(Model model, View view, ChessBoardController controller) {
		
		this.model=model;
		this.controller=controller;
		this.view=view;
		
	}
	
	public void moveAt(int[] coordinates){
		 if (isLegalMove(coordinates))
			 model.setConfiguration(model.getChessBoard().moveAt(controller.getFrom(),coordinates));
		 
		 else view.illegalMove(coordinates); 
			 
	}
	
	public boolean isLegalMove(int[] coordinates){
		//La destinazione non contiene una pedina con colore uguale al turno
		 if (model.at(coordinates)==null || model.at(coordinates).getColor() != model.getChessBoard().getTurn())
			 return true;
		 return false;
	}
	
	public boolean therIsAWinner(){
		return false;
	}

} 
