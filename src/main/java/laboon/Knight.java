package laboon;
public class Knight extends Piece {

	//Constructor
	public Knight(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Kn";
	}

	//Knight movement
	public boolean move(int h, int v) {
		// Knight can move horizontal two and vertical one or vice verse
		return ((Math.abs(h-getX())==2 && Math.abs(v-getY())==1)^(Math.abs(h-getX())==1 && Math.abs(v-getY())==2));
	}
}