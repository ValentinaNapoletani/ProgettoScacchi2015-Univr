package View;

import javax.swing.*;
import java.awt.*;

import Controller.*;
import Model.*;

public class ChessBoardPanel extends JPanel implements View {
	
	private Controller controller;
	private final Model model;
	private final JFrame frame;
	private JButton[][] chessBoardSquares = new JButton[8][8];
	
	private final Color[] colors = { new Color(240, 230, 140), new Color(101, 67, 33)};
	
	int[] coordinates=new int[2];
	
	public ChessBoardPanel(Model model,JFrame frame) {
    	
    	this.model=model; 
    	this.frame=frame;

    	createChessBoardButtons();
        
        model.setView(this);
    }
	

	@Override
	public Model getModel() {	
	    return model;
	}

	public void setController(Controller controller) {
	    this.controller=controller;
	}
	        
	public void onConfigurationChange() {
		for(int y=0;y<8; y++)
			for (int x=0; x<8; x++) {
				coordinates[0]=x;
	    		coordinates[1]=y;
				chessBoardSquares[x][y].setText(model.at(coordinates)==null ? "" : (model.at(coordinates)).getUnicode());
				setButton(chessBoardSquares[x][y]);
			}	
		//turno label
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
    	    for (int x=0; x<8; x++) {
    	    	coordinates[0]=x;
    	    	coordinates[1]=y;
    		    chessBoard.add(chessBoardSquares[x][y]=mkButton(x,y,model.at(coordinates),colors[(x+y)%2]));
    	    }
		}
	}
	
	public JButton mkButton(int x, int y, Piece at, Color color){
		
		coordinates[0]=x;
    	coordinates[1]=y;
    	

		JButton button=new JButton( model.at(coordinates)==null ? "" : model.at(coordinates).getUnicode());
		
		//button.setFont(new Font("Tahoma",Font.BOLD,35));
		button.setBackground(color);
		button.addActionListener(event -> controller.onClick(coordinates[0],coordinates[1]));
	   
		/*if( model.at(coordinates) != null ) {
			if (model.at(coordinates).getColor() == Color.black) 
				button.setForeground(Color.black);
			else if (model.at(coordinates).getColor() == Color.white)
				button.setForeground(Color.white); 	
		}*/
			
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

}
