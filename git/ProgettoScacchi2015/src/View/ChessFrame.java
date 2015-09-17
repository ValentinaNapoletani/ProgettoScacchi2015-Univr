package View;

import java.awt.*;
import javax.swing.*;

import Controller.*;
import Model.*;
import java.util.ArrayList;

public class ChessFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel roundLabel;
	private TextArea piecesArea;
	private TextArea piecesArea2;
	private final HiFrame hiFrame;
	private final ChessBoardModel model = new ChessBoardModel(new ChessBoard());
	private final Controller controller;

    public ChessFrame(HiFrame hiFrame) {
    	
    	this.hiFrame=hiFrame;
       	
        ChessBoardPanel view=addChessBoard();
    	setTitle("Chess");
    	createLayout();   
        controller=new ChessBoardController(view,model,hiFrame,this);
        this.setSize(2000, 2000);
        
	}
    
    private void createLayout() {
    	
    	JPanel layout = new JPanel(new BorderLayout());
    	add(layout);
    	
    	//JScrollPane scrollbar=new JScrollPane(layout);
    	//add(scrollbar);
    	
    	 //chessboard
        //layout.add(addChessBoard(),BorderLayout.CENTER);
	
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
    	
    	JPanel centerPanel = new JPanel(new FlowLayout());
        layout.add(centerPanel, BorderLayout.CENTER);
        
        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.Y_AXIS));
    	centerPanel.add(Panel2);
        
        centerPanel.add(addChessBoard());
    	
        JPanel southPanel = new JPanel( new GridLayout(1,3));
    	layout.add(southPanel, BorderLayout.SOUTH);
    		
    	JLabel jl=new JLabel();
    	southPanel.add(jl);
    	
    	//round
    	this.roundLabel = new JLabel();
    	southPanel.add(roundLabel);
    	roundLabel.setFont(new Font("Purisa",Font.BOLD,16));
    	roundLabel.setForeground(Color.blue);
    	
    	JLabel jl2=new JLabel();
    	southPanel.add(jl2);
    	
    	JPanel Panel = new JPanel();
    	Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    	centerPanel.add(Panel);
    	
    	//white jumped pieces
    	JLabel whitejumped = new JLabel(" White jumped pieces ");
    	whitejumped.setAlignmentX(LEFT_ALIGNMENT);;
    	Panel2.add(whitejumped);
    	whitejumped.setFont(new Font("Purisa",Font.BOLD,15));
    	whitejumped.setForeground(Color.blue);
    	
    	this.piecesArea=new TextArea("",10,1,TextArea.SCROLLBARS_VERTICAL_ONLY);
    	this.piecesArea.setEditable(false);
    	this.piecesArea.setFont(new Font("Tahoma",Font.BOLD,40));
    
    	Panel2.add(piecesArea);
    	
        //black jumped pieces
    	JLabel blackjumped = new JLabel(" Black jumped pieces ");
        Panel.add(blackjumped);
        blackjumped.setFont(new Font("Purisa",Font.BOLD,15));
        blackjumped.setForeground(Color.blue);
    	
    	this.piecesArea2=new TextArea("",10,1,TextArea.SCROLLBARS_VERTICAL_ONLY);
    	this.piecesArea2.setEditable(false);
    	this.piecesArea2.setFont(new Font("Tahoma",Font.BOLD,40));
    	
    	Panel.add(piecesArea2);
    
   
  
    } 
	
    public String setLabel (String white, String black){
    	String result="";
    	if(model.getChessBoard().getTurn()==Color.white)	
    		result= "            " + white + ", it's your turn!";
    	else if (model.getChessBoard().getTurn()==Color.black)
    		result= "            " + black + ", it's your turn!";
    	return result;
    	
    }
    
    public String[] setJumpedPieces(ArrayList<Piece> white, ArrayList<Piece> black){
    	String result[]= new String[2];
    	result[0]="";
    	result[1]="";
    	for (Piece p: white)
    		result[0] += p.getUnicode();
    	for (Piece p: black)
    		result[1] += p.getUnicode();
    	return result;
    }
    
    public TextArea getPiecesArea(){
    	return piecesArea;
    }
    
    public TextArea getPiecesArea2(){
    	return piecesArea2;
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
                frame.setVisible(true);
                hiFrame.setSize(400,300);
                hiFrame.setAlwaysOnTop(true);
                hiFrame.setVisible(true);      
               // new finalDialog(frame,hiFrame,Color.white).setVisible(true);
                 
            }
        });
        
    }
}
