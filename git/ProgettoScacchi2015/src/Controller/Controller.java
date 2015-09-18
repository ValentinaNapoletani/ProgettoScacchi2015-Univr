package Controller;

import java.awt.event.*;
import Model.Position;
import Model.Piece;

public interface Controller {
	
	//User do something
	void onClick(int x, int y, ActionEvent e);
	void setupNewGame();
	void quitGame();
	void start();
	void promotion(Position pos,Piece piece);
	
} 
