package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import Controller.*;
import Model.*;

public class ChessBoardPanel extends JPanel implements View {
	
	private static final long serialVersionUID = 1L;
	private ChessBoardController controller;
	private final Model model;
	private final ChessFrame frame;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	private JPanel chessBoard=new JPanel(new GridLayout(9, 9));
	
	private final Color[] colors = { new Color(240, 230, 140), new Color(101, 67, 33)};
	Position coordinates=new Position();
	
	public ChessBoardPanel(Model model,ChessFrame frame) {
    	
    	this.model=model; 
    	this.frame=frame;

    	createChessBoardButtons();
        
        model.setView(this);
    }
	

	@Override
	public Model getModel() {	
	    return model;
	}
	
	public ChessFrame getFrame(){
		return frame;
	}

	public void setController(ChessBoardController controller) {
	    this.controller=controller;
	}
	
	
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
	
	public void createChessBoardButtons(){

		final String COLS = "ABCDEFGH";
		//JPanel chessBoard=new JPanel(new GridLayout(9, 9));
		add(chessBoard);
		
		chessBoard.add(new JLabel(""));
	       
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
	
	public JButton mkButton(int x, int y, Piece at, Color color){
		
		coordinates.x=x;
		coordinates.y=y;
		
		JButton button=new JButton( model.at(coordinates)==null ? "" : model.at(coordinates).getUnicode());
		
		button.setBackground(color);
		button.addActionListener(event -> frame.getController().onClick(x,y,event));
		button.setPreferredSize(new Dimension(59,59));
		button.setFocusPainted(false);
		
	    return button;
	    
	}
	
	public void setButton(JButton button){
		
		button.setFont(new Font("Tahoma",Font.BOLD,35));
		
		if( model.at(coordinates) != null ) {
			if (model.at(coordinates).getColor() == Color.black) 
				button.setForeground(Color.black);
			else if (model.at(coordinates).getColor() == Color.white)
				button.setForeground(Color.white); 
		}
			
	} 
	
	public void showFinalDialog(Color color,HiFrame hiFrame) {
		frame.getRoundLabel().setText("scacco matto");
		new finalDialog(frame,hiFrame,color).setVisible(true); 
	}
	
	public void showPromotionDialog(){
	//	new promotionDialog(frame,controller);
	}
	
	public void selectCase(Object o){
		JButton button=(JButton) o;
		
		//button.setBackground(new Color(254,111,94)); per scacco!!!
		//button.setBorder(BorderFactory.createLineBorder(new Color(255,0,0,3/4)));
		button.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));
		
		//chessBoardSquares[coordinates.x][coordinates.y].setBackground(new Color(50,250,48));
		repaint();	
	}
	
	public void colorOnCheck(Object o){
		
		Position position=model.getChessBoard().getEnemyKingCoord();
		JButton button=chessBoardSquares[position.x][position.y];
		button.setBackground(new Color(254,111,94));
		repaint();	
		frame.getRoundLabel().setText("scacco!!");
	}
	
	public void clearCase(Object o){
		JButton button=(JButton) o;
		button.setBorderPainted(false);
	}
	
	public void illegalMove(Object o){
		JButton button=(JButton) o;
		button.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));
		repaint();
		
		int delay = 800; //milliseconds
		ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		          clearCase(o);
		      }
		  };
		 new Timer(delay, taskPerformer).start();
	}


}
