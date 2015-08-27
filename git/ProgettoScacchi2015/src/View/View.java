package View;

import Model.*;

import javax.swing.JButton;

import Controller.*;

public interface View {
	
	Model getModel();
	ChessFrame getFrame();
	
	void setController(Controller controller);
	
	//change display
	void showFinalDialog();
	void showPromotionDialog();
	void selectCase(int [] coordinates);
	void clearCase();
	void illegalMove(int [] coordinates);
	 
	//I've changed
	void onConfigurationChange();

}
