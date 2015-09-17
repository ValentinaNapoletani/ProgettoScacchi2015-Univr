package Model;

public class Position {
	
	public int x;
	public int y;
	
	public Position(int x,int y){
		this.x=x;
		this.y=y;	
	}
	
	public Position(){	
		this.x=0;
		this.y=0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object obj){

		if(obj instanceof Position) 
			if(this.x==((Position)obj).x && this.y==((Position)obj).y)
				return true;
		return false;
	}
	
	public String toString(){
		String res= "x:" + this.x + "  y:"+ this.y;
		return res;
	}
}
