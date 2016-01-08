package Controller;

import View.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


/** Classe che gestisce l'interazione vista-modello, implementando il Controller.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public class ChessBoardController implements Controller {
	
	private final View view;
	private final Mover mover;
	private final Model model;
	private HiFrame hiFrame;
	private final ChessFrame frame;
	private Position from = new Position();
	private boolean selected=false;
	private ArrayList<Piece> blackjumped = new ArrayList<>();
	private ArrayList<Piece> whitejumped = new ArrayList<>();
	private CheckMateController controller; 
	private JButton startButton;
	
	/**
	 * Costruttore del controller.
	 * 
	 * @param view La vista.
	 * @param model Il modello.
	 * @param hiFrame La finestra dove si inseriscono i nomi dei giocatori.
	 * @param frame La finestra principale di gioco.
	 */
	public ChessBoardController(View view, Model model, HiFrame hiFrame , ChessFrame frame){
		this.view = view;
		this.model=model;
		this.hiFrame=hiFrame;
		this.frame=frame;
		this.controller=new CheckMateController(model);
		this.mover = new Mover(view.getModel(),view,this, controller);
		view.setController(this);
	}
	
	/**
	 * Gestisce il click dei bottoni della scacchiera.
	 * 
	 * @param x La coordinata x del bottone su cui l'utente ha cliccato.
	 * @param y La coordinata y del bottone su cui l'utente ha cliccato.
	 * @param e L'evento generato dal click del bottone.
	 */
	@Override
	public void onClick(int x,int y, ActionEvent e) {
		Position coordinates= new Position(x,y);
		
		//non è già selezionata la partenza e seleziono una mia pedina
		if(model.at(coordinates)!=null && model.at(coordinates).getColor() == model.getChessBoard().getTurn() && !selected){
			this.from.x=x;
			this.from.y=y;
			this.selected=true;
			startButton=(JButton)e.getSource();
			view.selectCase(startButton); //implement view
			
		}
		//deseleziono il pezzo per fare una altra mossa
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
				
				//verifica scacco
				Piece enemy=controller.isCheck(model.getChessBoard().getMyKingCoord());
				if(enemy!=null){
					frame.getRoundLabel().setText("Check!!");
					if(mover.therIsAWinner(enemy)!=null)
						view.showFinalDialog(mover.therIsAWinner(enemy),hiFrame);
				}
				else if(controller.isCheck(model.getChessBoard().getMyKingCoord())==null &&
						frame.getRoundLabel().getText()=="Check!!")
						frame.getRoundLabel().setText(frame.setLabel(frame.getHiFrame().getWhite().getText(), frame.getHiFrame().getBlack().getText()));
					
			}
			else  view.illegalMove(e.getSource()); 
			
			if (model.at(coordinates) instanceof Pawn && 
					   ((y==0 && model.at(coordinates).getColor()==Color.white) || (y==7 && model.at(coordinates).getColor()==Color.black))) {
						view.showPromotionDialog(coordinates); 
					} 
		}
		else view.illegalMove(e.getSource());
	
	}
	
	/**
	 * Ritorna la posizione di partenza della pedina.
	 * 
	 * @return la posizione di partenza della pedina.
	 */
	public Position getFrom(){
		return from;
	}
	
	/**
	 * Ritorna la lista dei pezzi bianchi che sono stati mangiati.
	 * 
	 * @return la lista dei pezzi bianchi che sono stati mangiati.
	 */
	public ArrayList<Piece> getwhitePieces(){
		return whitejumped;
	}
	
	/**
	 * Ritorna la lista dei pezzi neri che sono stati mangiati.
	 * 
	 * @return la lista dei pezzi neri che sono stati mangiati.
	 */
	public ArrayList<Piece> getblackPieces(){
		return blackjumped;
	}
	
	/**
	 * Gestisce l'evento generato al click del bottone di nuovo gioco da parte dell'utente
	 * e genera una finestra dove poter inserire il nome dei giocatori.
	 */
	@Override
	public void setupNewGame() {
	
		model.setConfiguration(new ChessBoard());
		hiFrame.getWhite().setText("");
		hiFrame.getBlack().setText("");
		hiFrame.setVisible(true);
		
		this.blackjumped.clear();
		this.whitejumped.clear();
		frame.getPiecesArea().setText("");
		frame.getPiecesArea2().setText("");
		frame.getRoundLabel().setText("");
		
        
	}
	
	/**
	 * Gestisce l'evento generato al click del bottone di start da parte dell'utente.
	 */
	@Override
	public void start() {
		String whiteGamer = hiFrame.getWhite().getText();
		String blackGamer = hiFrame.getBlack().getText();
		view.getFrame().setLabel(whiteGamer,blackGamer);
		
		model.setConfiguration(new ChessBoard(1));
		hiFrame.dispose();

	}	

	/**
	 * Gestisce l'evento generato al click del bottone Quit, chiudendo la finestra di gioco.
	 */
	@Override
	public void quitGame() {
		System.exit(0);
	}
	
	/**
	 *  Gestisce l'evento di promozione del pedone.
	 *  
	 *  @param pos La posizione in cui scambiare il pedone con un altro pezzo per promuoverlo.
	 *  @param piece Il pezzo da far tornare in gioco.
	 *  @param prom La finestra di promozone del pedone.
	 */
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
