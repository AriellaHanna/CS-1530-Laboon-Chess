package laboon;
public class Rook extends Piece {

	//Constructor
	public Rook(boolean color, int hori, int vert) {
		super(color, hori, vert);
        symbol = "R";
	}

	public boolean move(int row, int column) {
		//Moving horizontally or vertically
		return (getCol() == column ^ getRow() == row);
	}
}