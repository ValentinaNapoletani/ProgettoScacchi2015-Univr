package Controller;

import View.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;


public class ChessBoardController implements Controller {
	
	private final View view;
	private final Mover mover;
	private final Model model;
	private final HiFrame hiFrame;
	private final ChessFrame frame;
	private Position from = new Position();
	private boolean selected=false;
	private ArrayList<Piece> blackjumped = new ArrayList<>();
	private ArrayList<Piece> whitejumped = new ArrayList<>();
	private CheckMateController controller; 
	private JButton startButton;
	
	public ChessBoardController(View view, Model model, HiFrame hiFrame , ChessFrame frame){
		this.view = view;
		this.model=model;
		this.hiFrame=hiFrame;
		this.frame=frame;
		this.controller=new CheckMateController(model);
		this.mover = new Mover(view.getModel(),view,this, controller);
		view.setController(this);
	}
	
	@Override
	public void onClick(int x,int y, ActionEvent e) {
		Position coordinates= new Position(x,y);
		
		//non già selezionata partenza e seleziono una mia pedina
		if(model.at(coordinates)!=null && model.at(coordinates).getColor() == model.getChessBoard().getTurn() && !selected){
			this.from.x=x;
			this.from.y=y;
			this.selected=true;
			startButton=(JButton)e.getSource();
			view.selectCase(startButton); //implement view
			
		}
		//deseleziono pezzo per fare una altra mossa--from[0]==x && from[1]==y
		else if (selected && startButton==(JButton)e.getSource()) {
			this.selected=false;
			startButton=(JButton)e.getSource();
			view.clearCase(startButton);
		}
	
		//seleziono casella di arrivo
		else if (selected && ((from.x==x && from.y!=y) || (from.x!=x && from.y==y) || (from.x!=x && from.y!=y))) {
			view.clearCase(startButton);
			this.selected=false;
			
			if ( model.at(from).isLegalMove(from,coordinates) && controller.checkMoves(from,coordinates)) {
						
				coordinates.x=x;
				coordinates.y=y;
				mover.moveAt(from,coordinates,e.getSource());
		
				selected=false;
				
				frame.getPiecesArea().setText(frame.setJumpedPieces(whitejumped,blackjumped)[0]);
				frame.getPiecesArea().setForeground(Color.white); 
				
				frame.getPiecesArea2().setText(frame.setJumpedPieces(whitejumped,blackjumped)[1]);
				
				Piece enemy=controller.isCheck(model.getChessBoard().getMyKingCoord());
				if(enemy!=null){
					view.colorOnCheck(model.getChessBoard().getMyKingCoord());
					if(mover.therIsAWinner(enemy)!=null)
						view.showFinalDialog(mover.therIsAWinner(enemy),hiFrame);
				}
				else if(controller.isCheck(model.getChessBoard().getMyKingCoord())==null &&
						frame.getRoundLabel().getText()=="Check!!")
						frame.getRoundLabel().setText(frame.setLabel(frame.getHiFrame().getWhite().getText(), frame.getHiFrame().getBlack().getText()));
					
			}
			else  view.illegalMove(e.getSource()); //implement view
			
			if (model.at(coordinates) instanceof Pawn && 
					   ((y==0 && model.at(coordinates).getColor()==Color.white) || (y==7 && model.at(coordinates).getColor()==Color.black))) {
						view.showPromotionDialog(coordinates); 
					} 
		}
		else view.illegalMove(e.getSource());
	
	}
	
	public Position getFrom(){
		return from;
	}
	
	public ArrayList<Piece> getwhitePieces(){
		return whitejumped;
	}
	
	public ArrayList<Piece> getblackPieces(){
		return blackjumped;
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
	
	@Override
	public void promotion(Position pos,Piece piece,PromotionDialog prom){	
		model.pieceSwap(pos,piece);
		prom.dispose();
		
		//rimuovi pezzo rinato da lista pezzi mangiati
		if (piece.getColor().equals(Color.white)) {
			for(int i=0;i< whitejumped.size();i++)
				if (whitejumped.get(i).equals(piece))
					whitejumped.remove(whitejumped.get(i));
		}
		else 
			for (Piece p2 : blackjumped)
				if (p2.equals(piece))
					blackjumped.remove(p2);
		
	}
	
	

}
