public class King extends Piece {
	
	//Constructor
	public King(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		//Make sure King is only trying to move one space
		if (xor((abs(h-x)==1 && y == v), (abs(v-y)==1) && x == h))
			return true;
		else
			return false;
	}
	
	private boolean xor(boolean a, boolean b) {
		return (a && !b) || (b && !a);
	}
	
	//Find absolute value
	private int abs(int a, int b) {
		if (a - b > 0)
			return a - b;
		else
			return (a-b)*-1;
	}
}