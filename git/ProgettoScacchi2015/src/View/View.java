package View;

import Model.*;
import java.awt.Color;
import Controller.*;

public interface View {
	
	Model getModel();
	ChessFrame getFrame();
	
	void setController(ChessBoardController controller);
	
	//change display
	void showFinalDialog(Color color,HiFrame hiFrame);
	void showPromotionDialog(Position p);
	void selectCase(Object o);
	void clearCase(Object o);
	void illegalMove(Object o);
	void colorOnCheck(Position p);
	
	//I've changed
	void onConfigurationChange();

}
