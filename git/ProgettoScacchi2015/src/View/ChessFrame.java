package View;

import java.awt.*;
import javax.swing.*;

import Controller.*;
import Model.*;

public class ChessFrame extends JFrame {
	
	//private static final long serialVersionUID = 1L;
	private JLabel roundLabel;
	private final HiFrame hiFrame;
	private final ChessBoardModel model = new ChessBoardModel(new ChessBoard());
	private final Controller controller;

    public ChessFrame(HiFrame hiFrame) {
    	
    	this.hiFrame=hiFrame;
       	
        ChessBoardPanel view=addChessBoard();
    	setTitle("Chess");
    	createLayout();   
        controller=new ChessBoardController(view,model,hiFrame);
        this.setSize(2000, 2000);
        
	}
    
    private void createLayout() {
    	
    	JPanel layout = new JPanel(new BorderLayout());
    	add(layout);
    	
    	//JScrollPane scrollbar=new JScrollPane(layout);
    	//add(scrollbar);
    	
    	 //chessboard
        //layout.add(addChessBoard(),BorderLayout.CENTER);
        
    	JPanel centerPanel = new JPanel( new FlowLayout() );
        layout.add(centerPanel, BorderLayout.CENTER);
        
        centerPanel.add(addChessBoard());
	
    	JPanel northPanel = new JPanel( new GridLayout(1, 2) );
    	layout.add(northPanel,BorderLayout.NORTH);
    	
    	//new game
    	JButton newGame=new JButton("New Game");
    	northPanel.add(newGame);
    	newGame.addActionListener(event -> controller.setupNewGame());
    	newGame.setFont(new Font("Purisa",Font.BOLD,15));
    	newGame.setBackground(new Color(230, 230, 250));
    	newGame.setForeground(Color.blue);
    	
    	//quit
    	JButton quit=new JButton("Quit");
    	northPanel.add(quit);
    	quit.addActionListener(event -> controller.quitGame());
    	quit.setFont(new Font("Purisa",Font.BOLD,15));
    	quit.setBackground(new Color(230,230,250));
    	quit.setForeground(Color.blue);
    	
    	JPanel eastPanel = new JPanel( new GridLayout(2,2));
    	layout.add(eastPanel, BorderLayout.EAST);
    	
    	//round
    	this.roundLabel = new JLabel();
    	eastPanel.add(roundLabel);
    	roundLabel.setLocation(600, 100);
    	roundLabel.setFont(new Font("Purisa",Font.BOLD,20));
    	roundLabel.setForeground(Color.blue);
    	
    	JPanel Panel = new JPanel(new GridLayout(2,2));
    	centerPanel.add(Panel);
    	
    	//History
    	JLabel history = new JLabel(" History ");
    	Panel.add(history);
    	history.setFont(new Font("Purisa",Font.BOLD,15));
    	history.setForeground(Color.blue);
    	
    	TextArea movesArea=new TextArea("",10,10,TextArea.SCROLLBARS_VERTICAL_ONLY);

        //eastPanel.add(movesArea);
    	Panel.add(movesArea);
    	
        
        //jumped pieces
        JLabel jumped = new JLabel(" Jumped pieces ");
        Panel.add(jumped);
        jumped.setFont(new Font("Purisa",Font.BOLD,15));
        jumped.setForeground(Color.blue);
    	
    	TextArea piecesArea=new TextArea("",6,10,TextArea.SCROLLBARS_HORIZONTAL_ONLY);
       
        //eastPanel.add(piecesArea);
    	Panel.add(piecesArea);
   
  
    } 
	

    public String setLabel(String white, String black){
    	String result="";
    	if(model.getChessBoard().getTurn()==Color.white)
    		result= white + ", it's your turn!	  ";
    	else if (model.getChessBoard().getTurn()==Color.black)
    		result=black + ", it's your turn!	  ";
    	return result;
    	
    }
    
    private ChessBoardPanel addChessBoard() {
    	
    	ChessBoardPanel chessBoard=new ChessBoardPanel(model,this); 
		return chessBoard;
	} 
    
    public Controller getController(){
		return controller;
	}
    
    public HiFrame getHiFrame() {
    	return hiFrame;
    };
    
    public JLabel getRoundLabel(){
    	return this.roundLabel;
    }
        	
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
            	HiFrame hiFrame = new HiFrame();
            	ChessFrame frame = new ChessFrame(hiFrame);
        
            	hiFrame.createLayout(frame.getController());
            	
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               // frame.pack();
                frame.setVisible(true);
                hiFrame.pack();
                hiFrame.setVisible(true);
             
      
            }
        });
        
    }
}
