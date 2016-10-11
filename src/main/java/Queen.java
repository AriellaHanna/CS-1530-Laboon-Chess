public class Queen extends Piece {
	
	//Constructor
	public Queen(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		//Moving horizontally or vertically
		if (xor(x == h, y == v))
			return true;
		//Moving diagonally
		else if (h-x == v - y)
		{
			return true;
		}
		else
			return false;
	}
	
	//Logical XOR
	private boolean xor(boolean a, boolean b) {
		return (a && !b) || (b && !a);
	}
}