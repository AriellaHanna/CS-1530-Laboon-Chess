package laboon;
public class Knight extends Piece {

	//Constructor
	public Knight(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Kn";
	}
	//Knight movement
	public boolean move(int row, int column) {
		// Knight can move horizontal two and vertical one or vice verse
		return ((Math.abs(column-getCol())==2 && Math.abs(row-getRow())==1)^(Math.abs(column-getCol())==1 && Math.abs(row-getRow())==2));
	}
}