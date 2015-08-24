package View;

import java.awt.*;
import javax.swing.*;

import Controller.*;
import Model.*;

public class ChessFrame extends JFrame {
	
	//private static final long serialVersionUID = 1L;
	private final HiFrame hiFrame;
	private final ChessBoardModel model = new ChessBoardModel(new ChessBoard());
	private final Controller controller;

    public ChessFrame(HiFrame hiFrame) {
    	
    	this.hiFrame=hiFrame;
       	
        ChessBoardPanel view=addChessBoard();
    	setTitle("Chess");
    	createLayout();   
        controller=new ChessBoardController(view,model);
        
	}
    
    private void createLayout() {
    	
    	JPanel layout = new JPanel(new BorderLayout());
    	add(layout);
    	
    	JScrollPane scrollbar=new JScrollPane(layout);
    	add(scrollbar);
    	
    	 //chessboard
        layout.add(addChessBoard(),BorderLayout.CENTER);
	
    	JPanel northPanel = new JPanel( new GridLayout(1, 2) );
    	layout.add(northPanel,BorderLayout.NORTH);
    	
    	//new game
    	JButton newGame=new JButton("New Game");
    	northPanel.add(newGame);
    	newGame.addActionListener(event -> controller.setupNewGame());
    	
    	//quit
    	JButton quit=new JButton("Quit");
    	northPanel.add(quit);
    	quit.addActionListener(event -> controller.quitGame());
    	
    	JPanel eastPanel = new JPanel(new GridLayout(5,1));
    	layout.add(eastPanel, BorderLayout.EAST);
    	
    	//round
    	JLabel roundLabel = new JLabel(setLabel());
    	eastPanel.add(roundLabel);
    	
    	//History
    	JLabel history = new JLabel("History");
    	eastPanel.add(history);
    	
    	JTextArea movesArea=new JTextArea(20,8);
        //JScrollPane scrollPane1 = new JScrollPane(movesArea);

        eastPanel.add(movesArea);
        
        //jumped pieces
        JLabel jumped = new JLabel("Jumped pieces");
    	eastPanel.add(jumped);
    	
    	JTextArea piecesArea=new JTextArea(20,8);
      //  JScrollPane scrollpane2=new JScrollPane(piecesArea);
        
        eastPanel.add(piecesArea);
   
                   	
    }
    
    public String setLabel(){
    	String result="";
    	if(model.getChessBoard().getTurn()==Color.white)
    		result=hiFrame.getWhite() + "it's your turn!";
    	else if (model.getChessBoard().getTurn()==Color.black)
    		result=hiFrame.getBlack() + "it's your turn!";
    	return result;
    	
    }
    
    private ChessBoardPanel addChessBoard() {
    	
    	ChessBoardPanel chessBoard=new ChessBoardPanel(model,this); 
		return chessBoard;
	} 
    
    public Controller getController(){
		return controller;
	}
    
        	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
            	HiFrame hiFrame = new HiFrame();
            	JFrame frame = new ChessFrame(hiFrame);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                hiFrame.pack();
                hiFrame.setVisible(true);
              //  frame.setBackground(new Color(255,153,102)); 
      
            }
        });
        
    }
}
