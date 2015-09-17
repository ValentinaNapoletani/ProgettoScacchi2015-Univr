package Model;

import View.View;

public class ChessBoardModel implements Model {
	
	
	private ChessBoard chessBoard;
	private View view;
	
	
	public ChessBoardModel( ChessBoard chessBoard ){
		this.chessBoard=chessBoard;
	}

	@Override
	public Piece at(Position coordinates) {
		return chessBoard.at(coordinates);
	}

	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	@Override
	public void setConfiguration(ChessBoard chessBoard) {
		if(!(this.getChessBoard().equals(chessBoard))) {
			this.chessBoard = chessBoard;
			if( view != null)
				view.onConfigurationChange();
		}
	}

	@Override
	public void setView(View view) {
		this.view=view;
	}

}
