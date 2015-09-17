package Model;

public interface Configuration {
	
	public Piece at(Position coordinates);
	public ChessBoard moveAt(Position initialCoord, Position finalCoord);
	public void initializePieces();
}
