package laboon;

public class Bishop extends Piece {

	//Constructor
	public Bishop(boolean color, int hori, int vert) {
		super(color, hori, vert);
        symbol = color ? "B" : "b";
	}

	public boolean move(int h, int v) {
		return (Math.abs(h-getX()) == Math.abs(v-getY()));
	}

}