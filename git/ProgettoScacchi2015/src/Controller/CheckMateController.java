package Controller;

import Model.*;
import java.util.ArrayList;

/** 
 * Classe che controlla la gestione dello scacco e dello scacco matto.
 */
public class CheckMateController {
	
	private Model model;
	private Piece enemy;
	 
	/**Costruttore della classe.
	 * 
	 * @param model Il modello.
	 */
	public CheckMateController(Model model){
		this.model=model;
		
	}
	
	/** Il metodo gestisce il controllo delle mosse in funzione dello scacco e dello scacco matto.
	 * 
	 * @param from Posizione di partenza del pezzo.
	 * @param to Posizione di arrivo del pezzo.
	 * @return True se la mossa è possibile,false altrimenti.
	 */
	
	public boolean checkMoves(Position from,Position to) {
		
		boolean b=true;
		Piece enemy=isCheck(model.getChessBoard().getMyKingCoord());
		ArrayList<Position> path;
		
		//se non sono in scacco devo stare al sicuro
		if(enemy==null) {
			//se sono un re non posso mettermi in scacco
			if (model.at(from) instanceof King) 
				return isCheck(to)!=null ? false :true;
						
			//se non sono un re non posso scoprirlo
			if (!(model.at(from) instanceof King)) {
				//se solo questo pezzo salva il re, non può muoversi fuori dalla linea di tiro del nemico
				Piece p= savingKing(model.getChessBoard().getOpponentsPieces(),model.getChessBoard().getMyKingCoord(), model.at(from));
				if(p!=null) {
					for(Position pos: (path=getCoordFromEnemyToKing(p,model.getChessBoard().getMyKingCoord())))
						if ( model.at(pos)!=null && !(model.at(pos).equals(model.at(from))) )
							return true;
					//se muovendomi continuo a salvare il re, ok!
					return path.contains(to) || 
						(to.x==p.getCoordinates().x && to.y==p.getCoordinates().y) ? true : false;	
				}
			}
		}
		//se sono in scacco posso solo togliermi dallo scacco
		else if(enemy!=null) {
			 
			 //se voglio mangiare chi mi mette sotto scacco, mettermi tra re e nemico o spostarmi in posizione salva,ok!
			 if( to.equals(enemy.getCoordinates()) || model.at(from) instanceof King && getSafeCoordinates(enemy).contains(to) || !(model.at(from) instanceof King) && getCoordFromEnemyToKing(enemy,model.getChessBoard().getMyKingCoord()).contains(to))
				return true;
			 else return false; 
		 }
			 
			 
	return b;
	}
		
	/** Ritorna la lista di pezzi che possono salvare il re se è in scacco.
	 * 
	 * @param enemy Il pezzo nemico che sta mettendo sotto scacco il re.
	 * @return la lista di pezzi che possono salvare il re se è in scacco.
	 */
	public ArrayList<Piece> getGoodPieces(Piece enemy){
		
		ArrayList<Piece> goodPieces=new ArrayList<>();
		
		for(Piece p :model.getChessBoard().getMyPieces())
			if(!(p instanceof King))
				if(p instanceof Pawn && p.getCoordinates().x!=enemy.getCoordinates().x)
					for( Position pos: p.getValidPosition(p.getCoordinates()))
						if(getCoordFromEnemyToKing(enemy, model.getChessBoard().getMyKingCoord()).contains(pos))
							goodPieces.add(p);
		
		return goodPieces;
	}

	/**Verifica se un pezzo sta salvando il re e ritorna eventualmente il nemico.
	 * 
	 * @param pieces La lista dei pezzi nemici.
	 * @param kingCoord Le coordinate del re.
	 * @param goodPiece Il pezzo che potrebbe star salvando il re.
	 * @return Il pezzo che mette sotto scacco il re.
	 */
	public Piece savingKing(ArrayList<Piece> pieces,Position kingCoord, Piece goodPiece){
		
		for (Piece p: pieces)
			if( p.getValidPosition(p.getCoordinates()).contains(kingCoord))
				if(getCoordFromEnemyToKing(p,kingCoord)!=null && getCoordFromEnemyToKing(p,kingCoord).contains(goodPiece.getCoordinates()))
					return p;
		
		return null;
	}
	/** Calcola il percorso del pezzo nemico verso il re.
	 * 
	 * @param p Il pezzo nemico.
	 * @param kingCoord Le coordinate del re.
	 * @return La lista delle coordinate del percorso tra il nemico e il re.
	 */
	
	public ArrayList<Position> getCoordFromEnemyToKing(Piece p, Position kingCoord){
		
		ArrayList<Position> path= new ArrayList<>();
		Position kingCoord2=new Position(kingCoord.x,kingCoord.y);
		
		int v=p.getCoordinates().y-kingCoord2.y;
		int o=p.getCoordinates().x-kingCoord2.x;
		
		
		if(!(p instanceof Knight)){
			//spostamento verticale
			if(p.getCoordinates().x==kingCoord2.x)
				while(p.getCoordinates().y != kingCoord2.y)
						path.add( new Position (kingCoord2.x, v>0 ? ++kingCoord2.y : --kingCoord2.y ) );

			//spostamento orizzontale
			else if(p.getCoordinates().y==kingCoord2.y)
				while(p.getCoordinates().x != kingCoord2.x)
					path.add( new Position (o>0 ? ++kingCoord2.x : --kingCoord2.x, kingCoord2.y) );	
			
			//spostamento obliquo
			else 
				while( p.getCoordinates().y != kingCoord2.y && p.getCoordinates().x != kingCoord2.x)
					path.add( new Position (o>0 ? ++kingCoord2.x : --kingCoord2.x, v>0 ? ++kingCoord2.y : --kingCoord2.y) );
				
			if(path.size()>0)
				path.remove(path.size()-1); 
			
		}
			
		return path;
	}
		
	/** Verifica se c'è scacco ed eventualmente ritorna il pezzo che mette il re in scacco.
	 * 
	 * @param kingCoord Le coordiante del re.
	 * @return Il pezzo che mette il re in scacco oppure null.
	 */
	public Piece isCheck(Position kingCoord){
		
		
		for (Piece p: model.getChessBoard().getOpponentsPieces())
				if(p.getValidPosition(p.getCoordinates()).contains(kingCoord)) 
					if (p instanceof Knight) {
						enemy=p;
						return enemy;
						}
					else if (p instanceof Pawn && p.getCoordinates().x==kingCoord.x) {
						enemy=null;;
						break;
					}
					else {	
						if(getCoordFromEnemyToKing(p,kingCoord).isEmpty()) {
							enemy=p;
							return enemy;
						}
						for (Position position : getCoordFromEnemyToKing(p,kingCoord)) {		
							if(model.at(position)!=null) 						
								return enemy=null;
							else  
								enemy=p;
						}
						if(enemy!=null)
							return enemy;
					}
				else enemy=null;
		
		return enemy;
	}
	
	/**
	 * Ritorna le coordinate in cui il re può spostarsi rimanendo in posizione sicura rispetto al pezzo enemy.
	 * @param enemy Il pezzo nemico.	
	 * @return Ritorna la lista delle coordinate sicure per il re.
	 */
	public ArrayList<Position> getSafeCoordinates(Piece enemy){
		
		ArrayList<Position> safePos=new ArrayList<>();
		Position king=model.getChessBoard().getMyKingCoord();
		
		for( Position p: model.at(king).getValidPosition(king)) 
			if(p.x<8 && p.x>=0 && p.y<8 && p.y>=0)
				if ( isCheck(p)==null )
					if(model.at(p)==null || model.at(p).equals(enemy))
						safePos.add(new Position(p.x,p.y));
		
		return safePos;
	}
	
	/**
	 * Verifica la presenza di scacco matto.
	 * 
	 * @param enemy Il pezzo nemico che nella posizione attuale attacca il re.
	 * @return True se c'è scacco matto,false altrimenti.
	 */
	public boolean isCheckMate(Piece enemy){
	
		if( getSafeCoordinates(enemy).isEmpty() && getGoodPieces(enemy).isEmpty()){
	
			return true;
			}
		
		return false;
					
	}
}
