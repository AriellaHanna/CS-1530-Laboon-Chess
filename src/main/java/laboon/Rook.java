package laboon;
public class Rook extends Piece {

	//Constructor
	public Rook(boolean color, int row, int column) {
		super(color, row, column);
        symbol = color ? "R" : "r";
	}

	public boolean move(int row, int column) {
		//Moving horizontally or vertically
		return (getCol() == column ^ getRow() == row);
	}
}