public class Rook extends Piece {

	//Constructor
	public Rook(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		//Moving horizontally or vertically
		return (getX() == h ^ getY() == v);
	}
}