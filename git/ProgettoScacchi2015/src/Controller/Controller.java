package Controller;

import java.awt.event.*;
import Model.*;

public interface Controller {
	
	//User do something
	void onClick(int x, int y, ActionEvent e);
	void setupNewGame();
	void quitGame();
	void start();
	Position getFrom();
	
} 
