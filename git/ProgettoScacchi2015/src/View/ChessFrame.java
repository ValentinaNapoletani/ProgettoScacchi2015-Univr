package View;

import java.awt.*;
import javax.swing.*;

import Controller.*;
import Model.*;
import java.util.ArrayList;

/**Classe che implementa la finestra principale di gioco,estende JFrame.
 * 
 * @author Napoletani Valentina VR377688
 *
 */
public class ChessFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel roundLabel;
	private TextArea piecesArea;
	private TextArea piecesArea2;
	private HiFrame hiFrame;
	private final ChessBoardModel model = new ChessBoardModel(new ChessBoard());
	private final Controller controller;

	/**
	 *  Costruttore della classe.
	 * @param hiFrame La finestra dove si inseriscono i nomi dei giocatori.
	 */
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
    	
    	JPanel northPanel = new JPanel( new GridLayout(1, 2) );
    	layout.add(northPanel,BorderLayout.NORTH);
    	northPanel.setBackground(new Color(255, 229, 180));
    	
    	//new game
    	JButton newGame=new JButton("New Game");
    	northPanel.add(newGame);
    	newGame.addActionListener(event -> controller.setupNewGame());
    	newGame.setFont(new Font("Purisa",Font.BOLD,15));
    	newGame.setBackground(new Color(255, 253, 208));
    	newGame.setForeground(new Color(255, 117, 24));
    	newGame.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(255, 117, 24)));
    	
    	//quit
    	JButton quit=new JButton("Quit");
    	northPanel.add(quit);
    	quit.addActionListener(event -> controller.quitGame());
    	quit.setFont(new Font("Purisa",Font.BOLD,15));
    	quit.setBackground(new Color(255, 253, 208));
    	quit.setForeground(new Color(255, 117, 24));
    	quit.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(255, 117, 24)));
    	
    	JPanel centerPanel = new JPanel(new FlowLayout());
        layout.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBackground(new Color(255, 229, 180));
        
        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.Y_AXIS));
        Panel2.setBackground(new Color(255, 229, 180));
        centerPanel.add(Panel2);
        
        centerPanel.add(addChessBoard());
    	
        JPanel southPanel = new JPanel( new GridLayout(1,3));
    	layout.add(southPanel, BorderLayout.SOUTH);
    	southPanel.setBackground(new Color(255, 229, 180));
    		
    	JLabel jl=new JLabel();
    	southPanel.add(jl);
    	
    	//round
    	this.roundLabel = new JLabel();
    	southPanel.add(roundLabel);
    	roundLabel.setFont(new Font("Purisa",Font.BOLD,16));
    	roundLabel.setForeground(new Color(255, 117, 24));
    	
    	JLabel jl2=new JLabel();
    	southPanel.add(jl2);
    	
    	JPanel Panel = new JPanel();
    	Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
    	Panel.setBackground(new Color(255, 229, 180));
    	centerPanel.add(Panel);
    	
    	//white jumped pieces
    	JLabel whitejumped = new JLabel(" White jumped pieces ");
    	whitejumped.setAlignmentX(LEFT_ALIGNMENT);;
    	Panel2.add(whitejumped);
    	whitejumped.setFont(new Font("Purisa",Font.BOLD,15));
    	whitejumped.setForeground(new Color(255, 117, 24));
    	
    	this.piecesArea=new TextArea("",10,1,TextArea.SCROLLBARS_NONE);
    	this.piecesArea.setEditable(false);
    	this.piecesArea.setFont(new Font("Tahoma",Font.BOLD,40));
    	this.piecesArea.setBackground(new Color(255, 229, 180));

    
    	Panel2.add(piecesArea);
    	
        //black jumped pieces
    	JLabel blackjumped = new JLabel(" Black jumped pieces ");
        Panel.add(blackjumped);
        blackjumped.setFont(new Font("Purisa",Font.BOLD,15));
        blackjumped.setForeground(new Color(255, 117, 24));
    	
    	this.piecesArea2=new TextArea("",10,1,TextArea.SCROLLBARS_NONE);
    	this.piecesArea2.setEditable(false);
    	this.piecesArea2.setFont(new Font("Tahoma",Font.BOLD,40));
    	this.piecesArea2.setBackground(new Color(255, 229, 180));
    	
    	Panel.add(piecesArea2);
    
   
    } 
	
    /**
     * Ritorna una stringa con il turno, prendendo il nome del giocatore impostato nella hiFrame.
     * @param white Il nome del giocatore con il colore bianco.
     * @param black Il nome del giocatore con il colore nero.
     * @return Una stringa relativa al turno di gioco.
     */
    public String setLabel (String white, String black){
    	String result="";
    	if(model.getChessBoard().getTurn()==Color.white)	
    		result= "            " + white + ", it's your turn!";
    	else if (model.getChessBoard().getTurn()==Color.black)
    		result= "            " + black + ", it's your turn!";
    	return result;
    	
    }
    
    /**Ritorna la lista di degli unicode dei pezzi bianchi e neri mangiati, da inserire nell'area di testo apposita.
     * 
     * @param white La lista dei pezzi bianchi mangiati.
     * @param black La lista dei pezzi neri mangiati.
     * @return Array delle due stringhe indicanti lo unicode dei pezzi neri e dei pezzi bianchi mangiati.
     */
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
    
    /**Ritorna l'area di testo dove inseire i pezzi bianchi mangiati.
     * 
     * @return l'area di testo dove inseire i pezzi bianchi mangiati.
     */
    public TextArea getPiecesArea(){
    	return piecesArea;
    }
    
    /**Ritorna l'area di testo dove inserire i pezzi neri mangiati.
     * 
     * @return l'area di testo dove inserire i pezzi neri mangiati.
     */
    public TextArea getPiecesArea2(){
    	return piecesArea2;
    }
    
    /**Ritorna il pannello contenente la scacchira.
     * 
     * @return Il pannello contenente la scacchira.
     */
    public ChessBoardPanel addChessBoard() {
    	
    	ChessBoardPanel chessBoard=new ChessBoardPanel(model,this); 
    	chessBoard.setBackground(new Color(255, 229, 180));
		return chessBoard;
	} 
    
    /**Ritorna il controller.
     * 
     * @return Il controller.
     */
    public Controller getController(){
		return controller;
	}
    
    /**Ritorna la finestra dove si inseriscono i nomi dei giocatori.
     * 
     * @return hiFrame,la finestra dove si inseriscono i nomi dei giocatori.
     */
    public HiFrame getHiFrame() {
    	return hiFrame;
    };
    
    /**Ritorna l'etichetta relativa al turno.
     * 
     * @return L'etichetta relativa al turno.
     */
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
             
                             
            }
        });
        
    }
}
