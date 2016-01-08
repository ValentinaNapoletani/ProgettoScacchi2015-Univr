package View;

import Model.*;
import Controller.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**La classe implementa la finestra in cui il giocatore può scegliere con quale pezzo promuovere il pedone.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public class PromotionDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private Color turn;
	private ArrayList<Piece> whitePieces;
	private ArrayList<Piece> blackPieces; 
	private Controller controller;
	private Position p;
	
	/**Il costruttore della classe.
	 * 
	 * @param frame La finestra principale di gioco.
	 * @param whitePieces La lista di pezzi bianchi mangiati.
	 * @param blackPieces La lista di pezzi neri mangiati.
	 * @param turn Il turno corrente del giocatore.
	 * @param controller Il controller.
	 * @param p La posizione in cui si trova il pedone da promuovere.
	 */
	public PromotionDialog(ChessFrame frame, ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces, Color turn,Controller controller,Position p){
		
		super(frame, "Promotion", true);
		this.turn=turn;
		this.whitePieces=whitePieces;
		this.blackPieces=blackPieces;
		this.controller=controller;
		this.p=p;
		createLayout();
		
		this.pack();
	}
	
	private void createLayout(){
		
		JPanel panel=new JPanel(new GridLayout(2,1));
		this.add(panel);
		panel.setBackground(new Color(218,253,218));
		
		ArrayList<Piece> jumpedPieces= turn==Color.white ? (ArrayList<Piece>) blackPieces.clone() : (ArrayList<Piece>) whitePieces.clone();
		int pawn=0;
		
		for(int j=0;j< jumpedPieces.size();j++) {
			Piece piece=jumpedPieces.get(j);
			if(piece instanceof Pawn)
				pawn++;
			for(int i=0;i< jumpedPieces.size();i++)
				if (jumpedPieces.get(i).getUnicode()==piece.getUnicode() && jumpedPieces.get(i).getCoordinates()!=piece.getCoordinates())
					jumpedPieces.remove(piece);
		}
	
		JLabel label=new JLabel(jumpedPieces.isEmpty() || pawn==jumpedPieces.size() ? "There are no jumped pieces for the pawn's promotion " :"	Choose a piece for the pawn's promotion:");
		panel.add(label);
		label.setForeground(new Color(1,50,32));
		label.setFont(new Font("Purisa",Font.BOLD,20));
		
		
		JPanel piecePanel=new JPanel(new FlowLayout() );		
		
		for(Piece piece : jumpedPieces) {
			if(!(piece instanceof Pawn))		
			piecePanel.add(mkButton(piece));
		}
		
		panel.add(piecePanel);
		piecePanel.setBackground(new Color(218,253,218));
		
	}
	
	private JButton mkButton(Piece piece){
		
		JButton button=new JButton(piece.getUnicode());
		button.setForeground( turn==Color.white ? Color.black : Color.white);
		button.setBackground(new Color(152,255,152));
		button.setFont(new Font("Tahoma",Font.BOLD,25));
		
		button.addActionListener(event -> controller.promotion(p,piece,this));
		
		return button;
	}

}
