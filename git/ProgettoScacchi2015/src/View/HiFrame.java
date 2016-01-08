package View;


import javax.swing.*;
import java.awt.*;
import Controller.*;

/**
 * La classe implementa la finestra iniziale dove i giocatori inseriscono il loro nome, accanto al colore corrispondente.
 * 
 * @author Napoletani Valentina VR377688
 */

public class HiFrame extends JFrame {
	
	private JTextField white;
	private JTextField black;
	private static final long serialVersionUID=1L;

	/**
	 * Costruttore della classe.
	 */
	public HiFrame() {
		setTitle("Wellcome");
		
	}
	
	/**
	 * Il metodo configura il layout della finestra.
	 * 
	 * @param controller Il controller.
	 */
	public void createLayout(Controller controller) {
		
		JPanel layout = new JPanel(new BorderLayout());
    	add(layout);
    	
    	JPanel northPanel = new JPanel(new GridLayout(2,1));
    	layout.add(northPanel,BorderLayout.NORTH);
    	northPanel.setBackground(new Color(255, 229, 180));
    	
    	JLabel wellcomeLabel = new JLabel("    Wellcome");
    	JLabel namesLabel = new JLabel("Insert names:");
    	northPanel.add(    wellcomeLabel);
    	northPanel.add(    namesLabel);
    	
    	wellcomeLabel.setFont(new Font("Purisa",Font.BOLD,40));
    	namesLabel.setFont(new Font("Purisa",Font.BOLD,20));
    	wellcomeLabel.setForeground(new Color(255, 117, 24));
    	namesLabel.setForeground(new Color(255, 117, 24));
    	
    	JPanel centerPanel = new JPanel(new GridLayout(2,2));
    	layout.add(centerPanel,BorderLayout.CENTER);
    	centerPanel.setBackground(new Color(255, 229, 180));
    	
    	JLabel whiteKing = new JLabel("\u265A", JLabel.RIGHT);
    	JLabel blackKing = new JLabel("\u265A", JLabel.RIGHT );
    	
    	whiteKing.setFont(new Font("Tahoma",Font.BOLD,35));
    	blackKing.setFont(new Font("Tahoma",Font.BOLD,35));
    	
    	whiteKing.setForeground(Color.white);
    	
    	this.white = new JTextField();
    	this.black = new JTextField();
    	
    	white.setFont(new Font("Purisa",Font.BOLD,18));
    	black.setFont(new Font("Purisa",Font.BOLD,18));
    	white.setForeground(Color.ORANGE);
    	black.setForeground(Color.ORANGE);
    	
    	centerPanel.add(whiteKing);
    	centerPanel.add(white);	
    	centerPanel.add(blackKing);
    	centerPanel.add(black);
    	
    	JPanel southPanel = new JPanel();
    	layout.add(southPanel,BorderLayout.SOUTH);
    	southPanel.setBackground(new Color(255, 229, 180));
    	
    	JButton start=new JButton("Start");
    	southPanel.add(start);
    	
    	start.addActionListener(event -> controller.start());
    	start.setBackground(new Color(255, 253, 208));
    	start.setForeground(new Color(255, 117, 24));
    	start.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(255, 117, 24)));
    	start.setPreferredSize(new Dimension(60,30));
    	start.setFont(new Font("Purisa",Font.BOLD,15));
    	
	}
	
	public JTextField getWhite(){
		return white;
	}
	
	public JTextField getBlack(){
		return black;
	}
}
