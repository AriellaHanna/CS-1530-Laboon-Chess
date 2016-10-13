package laboon;

public class Bishop extends Piece {
	
	//Constructor
	public Bishop(boolean color, int hori, int vert) {
		super(color, hori, vert);
	}
	
	public boolean move(int h, int v) {
		return (Math.abs(h-getX()) == Math.abs(v-getY()));
	}

}