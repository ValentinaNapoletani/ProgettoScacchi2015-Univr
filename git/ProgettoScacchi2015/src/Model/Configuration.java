package Model;

public interface Configuration {
	
	public Piece at(int[] coordinates);
	public ChessBoard moveAt(int[] initialCoord, int[] finalCoord);
	public void initializePieces();
}
