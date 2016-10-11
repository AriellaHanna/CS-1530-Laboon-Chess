public class King extends Piece {
	
	//Constructor
	public King(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		//Make sure King is only trying to move one space
		if (abs(h,getX()) <= 1 && abs(v,getY()) <=1)
			return true;
		else
			return false;
	}
	
	//Find absolute value of a-b
	private int abs(int a, int b) {
		if (a - b > 0)
			return a - b;
		else
			return (a-b)*-1;
	}
}