package View;


import javax.swing.*;
import java.awt.*;


public class HiFrame extends JFrame {
	
	private String whiteGamer;
	private String blackGamer;

	public HiFrame() {
		setTitle("Wellcome");
		createLayout();
	}
	
	private void createLayout() {
		
		JPanel layout = new JPanel(new BorderLayout());
    	add(layout);
    	
    	JPanel northPanel = new JPanel(new GridLayout(2,1));
    	layout.add(northPanel,BorderLayout.NORTH);
    	
    	JLabel wellcomeLabel = new JLabel("Wellcome");
    	JLabel namesLabel = new JLabel("Insert names:");
    	northPanel.add(    wellcomeLabel);
    	northPanel.add(    namesLabel);
    	
    	wellcomeLabel.setFont(new Font("Purisa",Font.BOLD,35));
    	namesLabel.setFont(new Font("Tahoma",Font.ITALIC,15));
    	
    	JPanel centerPanel = new JPanel(new GridLayout(2,2));
    	layout.add(centerPanel,BorderLayout.CENTER);
    	
    	JLabel whiteKing = new JLabel("\u265A", JLabel.RIGHT);
    	JLabel blackKing = new JLabel("\u265A", JLabel.RIGHT );
    	
    	whiteKing.setFont(new Font("Tahoma",Font.BOLD,35));
    	blackKing.setFont(new Font("Tahoma",Font.BOLD,35));
    	
    	whiteKing.setForeground(Color.white);
    	
    	JTextField white = new JTextField();
    	JTextField black = new JTextField();
    	
    	centerPanel.add(whiteKing);
    	centerPanel.add(white);	
    	centerPanel.add(blackKing);
    	centerPanel.add(black);
    	
    	this.whiteGamer=white.getText();
    	this.blackGamer=black.getText();
    	
    	JPanel southPanel = new JPanel();
    	layout.add(southPanel,BorderLayout.SOUTH);
    	
    	JButton start=new JButton("Start");
    	southPanel.add(start);
    	//start.addActionListener(event -> controller.start());
  
	}
	
	public String getWhite(){
		return whiteGamer;
	}
	
	public String getBlack(){
		return blackGamer;
	}
}
