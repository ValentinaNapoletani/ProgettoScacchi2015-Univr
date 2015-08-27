package View;

import javax.swing.*;
import java.awt.*;

import Controller.*;
import Model.*;

public class ChessBoardPanel extends JPanel implements View {
	
	private Controller controller;
	private final Model model;
	private final ChessFrame frame;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	
	private final Color[] colors = { new Color(240, 230, 140), new Color(101, 67, 33)};
	
	int[] coordinates=new int[2];
	
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

	public void setController(Controller controller) {
	    this.controller=controller;
	}
	
	
	public void onConfigurationChange() {
		for(int y=0;y<8; y++) {
			coordinates[1]=y;
			for (int x=0; x<8; x++) {
				coordinates[0]=x;
				chessBoardSquares[x][y].setText(model.at(coordinates)==null ? "" : (model.at(coordinates)).getUnicode());
				setButton(chessBoardSquares[x][y]);
			}
		}
		
		frame.getRoundLabel().setText(frame.setLabel(frame.getHiFrame().getWhite().getText(), frame.getHiFrame().getBlack().getText()));
	}
	
	public void createChessBoardButtons(){

		final String COLS = "ABCDEFGH";
		JPanel chessBoard=new JPanel(new GridLayout(9, 9));
		add(chessBoard);
		
		chessBoard.add(new JLabel(""));
	       
		for (int x= 0; x < 8; x++) 
            chessBoard.add( new JLabel("        "+COLS.substring(x, x + 1) + "      "));
		
		for(int y=0;y<8; y++) {
			chessBoard.add(new JLabel("" + (9-(y + 1))));
			coordinates[1]=y;
    	    for (int x=0; x<8; x++) {
    	    	coordinates[0]=x;
    		    chessBoard.add(chessBoardSquares[x][y]=mkButton(x,y,model.at(coordinates),colors[(x+y)%2]));
    	    }
		}
	}
	
	public JButton mkButton(int x, int y, Piece at, Color color){
		
		coordinates[0]=x;
    	coordinates[1]=y;
    	

		JButton button=new JButton( model.at(coordinates)==null ? "" : model.at(coordinates).getUnicode());
		
		button.setBackground(color);
		button.addActionListener(event -> frame.getController().onClick(x,y));
		button.setPreferredSize(new Dimension(65,65));
		
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
	
	public void showFinalDialog() {
	//	new finalDialog(frame,controller).setVisible(true); //da passare il vincitore?!
	}
	
	public void showPromotionDialog(){
	//	new promotionDialog(frame,controller);
	}
	
	public void selectCase(int [] coordinates){
		chessBoardSquares[coordinates[0]][coordinates[1]].setBackground(Color.blue);
		/*JButton button=chessBoardSquares[coordinates[0]][coordinates[1]];
		button.setText(model.at(coordinates)==null ? "" : (model.at(coordinates)).getUnicode());
		setButton(button); 
		button.setBackground(Color.blue);
		button.addActionListener(event -> frame.getController().onClick(coordinates[0],coordinates[1]));
		button.setPreferredSize(new Dimension(65,65)); */
	}
	
	public void clearCase(){
		
	}
	
	public void illegalMove(int [] coordinates){
		chessBoardSquares[coordinates[0]][coordinates[1]].setBackground(Color.red);
	}


}
