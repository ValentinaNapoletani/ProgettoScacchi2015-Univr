package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.*;
import Model.*;

/**
 * Classe che implementa la vista, il pannello della scacchiera.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public class ChessBoardPanel extends JPanel implements View {
	
	private static final long serialVersionUID = 1L;
	private ChessBoardController controller;
	private final Model model;
	private final ChessFrame frame;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private JPanel chessBoard=new JPanel(new GridLayout(9, 9));
	
	
	private final Color[] colors = { new Color(240, 230, 140), new Color(101, 67, 33)};
	Position coordinates=new Position();
	
	/**
	 * Costruttore della classe che crea la scacchiera,una matrice di JButton.
	 * 
	 * @param model Il modello.
	 * @param frame La finestra principale di gioco.
	 */
	public ChessBoardPanel(Model model,ChessFrame frame) {
    	
    	this.model=model; 
    	this.frame=frame;

    	createChessBoardButtons();
        
        model.setView(this);
    }
	
	/**
	 * Ritorna il modello.
	 * @return il modello.
	 */
	@Override
	public Model getModel() {	
	    return model;
	}
	
	/**
	 * Ritorna la finestra di gioco.
	 * @return la finestra di gioco.
	 */
	public ChessFrame getFrame(){
		return frame;
	}

	/**
	 * Imposta il controller.
	 */
	public void setController(ChessBoardController controller) {
	    this.controller=controller;
	}
	
	/**
	 * Gestisce i cambiamenti di configurazione del modello e modifica adeguatamente la vista.
	 * Modifica inoltre l'etichetta del turno.
	 */
	public void onConfigurationChange() {
		for(int y=0;y<8; y++) {
			coordinates.y=y;
			for (int x=0; x<8; x++) {
				coordinates.x=x;
				chessBoardSquares[x][y].setText(model.at(coordinates)==null ? "" : (model.at(coordinates)).getUnicode());
				setButton(chessBoardSquares[x][y]);				
				
			}
		}
		
		frame.getRoundLabel().setText(frame.setLabel(frame.getHiFrame().getWhite().getText(), frame.getHiFrame().getBlack().getText()));
	}
	
	private void createChessBoardButtons(){
		
		final String COLS = "ABCDEFGH";
		add(chessBoard);
		chessBoard.setBackground(new Color(255, 229, 180));
		
		JLabel label=new JLabel("");
		chessBoard.add(label);
	       
		for (int x= 0; x < 8; x++) 	
            chessBoard.add( new JLabel("        "+COLS.substring(x, x + 1) + "      "));

		
		for(int y=0;y<8; y++) {
			coordinates.y=y;
			chessBoard.add(new JLabel("    " + (9-(y + 1))));
    	    for (int x=0; x<8; x++) {
    	    	coordinates.x=x;
    	    	chessBoard.add(chessBoardSquares[x][y]=mkButton(x,y,model.at(coordinates),colors[(x+y)%2]));
    	    }
		}
	}
	
	
	private JButton mkButton(int x, int y, Piece at, Color color){
		
		coordinates.x=x;
		coordinates.y=y;
		
		JButton button=new JButton( model.at(coordinates)==null ? "" : model.at(coordinates).getUnicode());
		
		button.setBackground(color);
		button.addActionListener(event -> frame.getController().onClick(x,y,event));
		button.setPreferredSize(new Dimension(59,59));
		button.setFocusPainted(false);
		
	    return button;
	    
	}
	
	private void setButton(JButton button){
		
		button.setFont(new Font("Tahoma",Font.BOLD,35));
		button.setPreferredSize(new Dimension(59,59));
		
		if( model.at(coordinates) != null ) {
			if (model.at(coordinates).getColor() == Color.black) 
				button.setForeground(Color.black);
			else if (model.at(coordinates).getColor() == Color.white)
				button.setForeground(Color.white); 
		}
			
	} 
	
	/** 
	 * Mostra la finestra finale, che mosta il vincitore.
	 * 
	 * @param color Il colore del vincitore
	 * @param hiframe La finestra iniziale con il nome dei giocatori.
	 */
	public void showFinalDialog(Color color,HiFrame hiFrame) {
		frame.getRoundLabel().setText("Check Mate");
		model.getChessBoard().setTurn(null);
		new FinalDialog(frame,hiFrame,color).setVisible(true);
		
	}
	
	/**
	 * Mostra la finestra di promozione del pedone.
	 * 
	 * @param p La posizione del pedone da promuovere.
	 */
	public void showPromotionDialog(Position p){
		new PromotionDialog(frame,controller.getwhitePieces(),controller.getblackPieces(),model.getChessBoard().getTurn(),controller,p).setVisible(true);;
	}
	
	/**
	 * Seleziona la casella cliccata dal giocatore, colorandone il bordo di blu.
	 * @param o Il bottone cliccato dall'utente.
	 */
	public void selectCase(Object o){
		JButton button=(JButton) o;
		
		if(button.getBorder()!=BorderFactory.createMatteBorder(2,2,2,2,Color.blue)){
			button.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));
			repaint();	
		}
		
	}
	
	/** Ripristina il colore della casella di partenza dopo che la mossa è stata effettuata oppure dopo un ripensamento del giocatore sulla pedina da spostare.
	 * @param o Il bottone il cui colore del bordo è da ripristinare.
	 */
	public void clearCase(Object o){
		JButton button=(JButton) o;
		button.setBorder(null);
	}
	
	/**Colora il bordo del bottone di arrivo di rosso se la mossa non è possibile.
	 * @param o Il bottone il cui colore del bordo è da ripristinare.
	 */
	public void illegalMove(Object o){
		JButton button=(JButton) o;
		button.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));
		repaint();
		
		int delay = 800; //millisecondi
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          clearCase(o);
		      }
		  };
		 new Timer(delay, taskPerformer).start();
	}
	
}
