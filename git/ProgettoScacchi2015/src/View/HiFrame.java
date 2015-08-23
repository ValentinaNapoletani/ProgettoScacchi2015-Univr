package View;


import javax.swing.*;
import java.awt.*;


public class HiFrame extends JFrame {

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
    	JLabel namesLabel = new JLabel("Insert names");
    	northPanel.add(wellcomeLabel);
    	northPanel.add(namesLabel);
    	//wellcomeLabel.setFont();
    	//namesLabel.setFont();
    	
    	JPanel centerPanel = new JPanel(new GridLayout(2,2));
    	layout.add(centerPanel,BorderLayout.CENTER);
    	
    	JLabel whiteKing = new JLabel("\u265A", JLabel.RIGHT);
    	JLabel blackKing = new JLabel("\u265A", JLabel.RIGHT );
    	
    	JTextField white = new JTextField();
    	JTextField black = new JTextField();
    	
    	centerPanel.add(whiteKing);
    	centerPanel.add(black);
    	centerPanel.add(white);
    	centerPanel.add(blackKing);
	}
}
