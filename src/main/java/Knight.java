public class Knight extends Piece {
	
	//Constructor
	public Knight(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	//Knight movement
	public boolean move(int h, int v) {
		if (abs(h,getX()) == 2 && abs(v,getY()) == 1)
			return true;
		else if (abs(h,getX()) == 1 && abs(v,getY()) == 2)
			return true;
		else return false;
	}
	
	//Find absolute value of a-b
	private int abs(int a, int b) {
		if (a - b > 0)
			return a - b;
		else
			return (a-b)*-1;
	}
}