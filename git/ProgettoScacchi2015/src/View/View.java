package View;

import Model.*;
import Controller.*;

public interface View {
	
	Model getModel();
	
	void setController(Controller controller);
	
	//change display
	void showFinalDialog();
	void showPromotionDialog();
	
	//I've changed
	void onConfigurationChange();

}
