package View;

import java.awt.*;
import javax.swing.*;

import Controller.*;
import Model.*;

public class ChessFrame extends JFrame {
	
	//private static final long serialVersionUID = 1L;
	private final ChessBoardModel model = new ChessBoardModel(new ChessBoard());
	private final Controller controller;

    public ChessFrame() {
       	
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
    	//JLabel roundLabel = new JLabel("Round:" + setRound());
    	JLabel roundLabel = new JLabel("Round:");
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
        
      //chessboard
        //JPanel chessBoard=new ChessBoardPanel(model,this); 
        //layout.add(chessBoard,BorderLayout.CENTER);
        
       // if (addChessBoard() != null)
        layout.add(addChessBoard(),BorderLayout.CENTER);
                   	
    }
    
    private ChessBoardPanel addChessBoard() {
    	
    	ChessBoardPanel chessBoard=new ChessBoardPanel(model,this); 
		//add(chessBoard, BorderLayout.CENTER);
		return chessBoard;
	} 
        	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
            	JFrame frame = new ChessFrame();
            	JFrame hiFrame = new HiFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                hiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                hiFrame.pack();
                hiFrame.setVisible(true);
      
            }
        });
    }
}
