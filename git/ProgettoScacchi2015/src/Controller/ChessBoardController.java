package Controller;

import View.*;
import Model.*;

public class ChessBoardController implements Controller {
	
	private final View view;
	//private final Mover Mover;
	private final Model model;
	
	public ChessBoardController(View view, Model model){
		this.view = view;
		this.model=model;
	//	this.mover = new Mover(view.getModel());
		
		view.setController(this);
	}

	@Override
	public void onClick(int x, int y) {
		//mover.moveAt(x,y);
		//if(mover.therIsAWinner())
		//	view.showFinalDialog();

		//da finire
	}

	@Override
	public void setupNewGame() {
		model.setConfiguration(new ChessBoard(1));
	}
	@Override
	public void quitGame() {
		System.exit(0);
	}
}
