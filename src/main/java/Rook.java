public class Rook extends Piece {

	//Constructor
	public Rook(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		//Moving horizontally or vertically
		if (xor(x == h, y == v))
			return true;
		else
			return false;
	}
	
	//Logical XOR
	private boolean xor(boolean a, boolean b) {
		return (a && !b) || (b && !a);
	}
}