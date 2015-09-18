package View;


import java.awt.*;
import javax.swing.*;

public class FinalDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Color color;
	
	public FinalDialog(ChessFrame frame,HiFrame hiFrame,Color color){
		super(frame, "End!", true);
		
		this.color=color;
		if(color==Color.white)
			this.name=hiFrame.getWhite().getText();
		else this.name=hiFrame.getBlack().getText();
		
		createLayout();
	}
	
	
	public void createLayout(){
				
		JPanel panel=new JPanel(new GridLayout(2,1));	
		this.add(panel);
				
		JPanel panel1=new JPanel();
		panel.add(panel1);
		JLabel label=new JLabel();
		panel1.add(label);
		panel1.setBackground(new Color(255, 253, 208));
		
		label.setText("<html>The winner is <br>  " + name + "!!!</h2></html>");		
		label.setFont(new Font("Purisa",Font.ITALIC,30));
		label.setForeground(new Color(255, 117, 24));

		JPanel panel2=new JPanel();
		panel.add(panel2);
		JLabel label2=new JLabel();
		panel2.add(label2);
		panel2.setBackground(new Color(255, 253, 208));
		
		label2.setText("\u265A");
		label2.setFont(new Font("Tahoma",Font.BOLD,100));
		label2.setForeground(color);
			
		this.setSize(400,300);
		
	}
}
