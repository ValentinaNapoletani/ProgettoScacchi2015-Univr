package Controller;

import Model.*;
import java.util.ArrayList;

public class CheckMateController {
	
	private Model model;
	private Piece enemy;
	 
	public CheckMateController(Model model){
		this.model=model;
		
	}
	
	//gestione delle mosse (scacco/scacco matto)
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
		
	//lista di pezzi che possono salvare il re se è in scacco
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

	//ritorna se un pezzo sta salvando il re e da quale nemico
	public Piece savingKing(ArrayList<Piece> pieces,Position kingCoord, Piece goodPiece){
		
		for (Piece p: pieces)
			if( p.getValidPosition(p.getCoordinates()).contains(kingCoord))
				if(getCoordFromEnemyToKing(p,kingCoord)!=null && getCoordFromEnemyToKing(p,kingCoord).contains(goodPiece.getCoordinates()))
					return p;
		
		return null;
	}
	
	//calcolo percorso dei pezzi nemici verso il re
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
	
	/*public boolean isCheck(Position kingCoord,Position from){
		
		boolean b=false;
		
		for (Piece p: model.getChessBoard().getTurn()==model.at(from).getColor() ? model.getChessBoard().getOpponentsPieces() : model.getChessBoard().getMyPieces())
				if(p.getValidPosition(p.getCoordinates()).contains(kingCoord)) 
					if (p instanceof Knight) {
						b=true;
						return b;	
					}
					else if (p instanceof Pawn && p.getCoordinates().x==kingCoord.x) {
						b=false;
						break;
					}
					else {
						if(getCoordFromEnemyToKing(p,kingCoord).isEmpty()) {
							b=true;
							return b;
						}
						for (Position position : getCoordFromEnemyToKing(p,kingCoord)) {	
							if(model.at(position)!=null) {
								b=false;
								break;
							}
							else 
								b=true;			
						}	
						if (b==true)
							return b;
					}
		return b;
	}*/
	
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
	
	
	public boolean isCheckMate(Piece enemy){
	
		if( getSafeCoordinates(enemy).isEmpty() && getGoodPieces(enemy).isEmpty()){
	
			return true;
			}
		
		return false;
					
	}
}
