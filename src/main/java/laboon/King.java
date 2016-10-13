package laboon;
public class King extends Piece {

	//Constructor
	public King(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = color ? "K" : "k";
	}

	public boolean move(int h, int v) {
		//Make sure King is moving exactly one space
		return ((Math.abs(h-getX()) <= 1) && (Math.abs(v-getY()) <=1) && !(h == getX() && v == getY()));
	}
}