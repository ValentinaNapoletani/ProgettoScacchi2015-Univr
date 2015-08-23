package Model;

import View.*;
//import Controller.*;

public interface Model {
	
// State information
	public Piece at(int[] coordinates);
	public ChessBoard getChessBoard();
	
//change status
	public void setConfiguration(ChessBoard chessBoard);
	public void setView(View view);
}
