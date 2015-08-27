package Controller;

import View.*;
import Model.*;
import java.awt.Color;
import java.awt.Frame;

public class ChessBoardController implements Controller {
	
	private final View view;
	private final Mover mover;
	private final Model model;
	private final HiFrame hiFrame;
	private int [] from = new int[2];
	private boolean selected=false;
	
	public ChessBoardController(View view, Model model, HiFrame hiFrame){
		this.view = view;
		this.model=model;
		this.hiFrame=hiFrame;
		this.mover = new Mover(view.getModel(), view, this);
		view.setController(this);
	}
	
	@Override
	public void onClick(int x,int y) {
		int [] coordinates= new int[2];
		coordinates[0]=x;
		coordinates[1]=y;
		
		//non già selezionata partenza e seleziono una mia pedina
		if(model.at(coordinates)!=null && model.at(coordinates).getColor() == model.getChessBoard().getTurn() ){
			this.from[0]=x;
			this.from[1]=y;
			this.selected=true;
			view.selectCase(coordinates); //implement view
		}
		
		else if (selected && from[0]==x && from[1]==y) {
			this.selected=false;
			view.clearCase(); //implement view
		}
		else if (selected && ((from[0]==x && from[1]!=y) || (from[0]!=x && from[1]==y) || (from[0]!=x && from[1]!=y)))
			if ( model.at(from).isLegalMove(from,coordinates)) {
				mover.moveAt(coordinates);
				selected=false;
				from[0]=10;
				from[1]=10;
			}
			else if (model.at(coordinates) != null)
				//aggiungi pezzo mangiato a lista
				//verifica se è re (gestione scacco)
				;
			else view.illegalMove(coordinates); //implement view
		else view.illegalMove(from);
		if(mover.therIsAWinner())
			view.showFinalDialog(); //implement view

		if(model.at(coordinates) instanceof Pawn && 
				(y==0 && model.at(coordinates).getColor()==Color.white) || (y==7 && model.at(coordinates).getColor()==Color.black)) {
			view.showPromotionDialog(); //implement view
		} 
		
	}
	
	public int[] getFrom(){
		return from;
	}
	
	@Override
	public void setupNewGame() {
		model.setConfiguration(new ChessBoard());
		HiFrame frame= new HiFrame();
		frame.createLayout(this);
		frame.pack();
        frame.setVisible(true);
	}
	
	@Override
	public void history() {
		
	}
	
	@Override
	public void jumped() {
		
	}
	
	@Override
	public void start() {
		String whiteGamer = hiFrame.getWhite().getText();
		String blackGamer = hiFrame.getBlack().getText();
		view.getFrame().setLabel(whiteGamer,blackGamer);
		
		model.setConfiguration(new ChessBoard(1));
		hiFrame.dispose();

	}

	@Override
	public void quitGame() {
		System.exit(0);
	}

}
