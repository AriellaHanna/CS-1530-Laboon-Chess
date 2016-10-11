public class Pawn extends Piece {
	private boolean hasMoved; //Whether or not pawn has moved yet
	//Constructor
	public Pawn(boolean color, int hori, int vert) {
		super(color, hori, vert);
		hasMoved = false;
	}
	
	public boolean move(int h, int v) {
		//Move diagonally
		if (abs(h,x) == 1 && abs(v,y) == 1)
			return true;
		//Pawn can move two spaces
		else if (!getMoved()){
			if (h == x && (abs(v,y) == 1 || abs(v,y) == 2))
			{
				setMoved(true);
				return true;
			}
		}
		//Pawn can only move one space forward
		else if (h == x && (abs(v,y) == 1))
			return true;
		else
			return false;
	}
	
	public void setMoved(boolean status){
		hasMoved = status;
	}
	
	public boolean getMoved(){
		return hasMoved;
	}
	
	//Logical XOR
	private boolean xor(boolean a, boolean b) {
		return (a && !b) || (b && !a);
	}
	
	//Find absolute value of a-b
	private int abs(int a, int b) {
		if (a - b > 0)
			return a - b;
		else
			return (a-b)*-1;
	}
}