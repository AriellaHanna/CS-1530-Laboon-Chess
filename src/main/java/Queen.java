public class Queen extends Piece {
	
	//Constructor
	public Queen(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		//Moving horizontallgetY() or verticallgetY()
		if (xor(getX() == h, getY() == v))
			return true;
		//Moving diagonallgetY()
		else if (h-getX() == v - getY())
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