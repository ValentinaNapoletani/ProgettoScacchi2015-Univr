package Model;

import View.*;

public interface Model {
	
// State information
	public Piece at(Position coordinates);
	public ChessBoard getChessBoard();
	
//change status
	public void setConfiguration(ChessBoard chessBoard);
	public void setView(View view);
	public void pieceSwap(Position pos,Piece piece);
}
