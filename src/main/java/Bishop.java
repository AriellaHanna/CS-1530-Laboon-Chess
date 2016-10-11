public class Bishop extends Piece {
	
	//Constructor
	public Bishop(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		if (h-x == v - y)
			return true;
		else
			return false;
	}
	
	//Logical XOR
	private boolean xor(boolean a, boolean b) {
		return (a && !b) || (b && !a);
	}
}