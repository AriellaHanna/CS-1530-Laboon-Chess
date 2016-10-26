package laboon;
public class Rook extends Piece {

	//Constructor
<<<<<<< HEAD
	public Rook(boolean color, int hori, int vert) {
		super(color, hori, vert);
        symbol = "R";
=======
	public Rook(boolean color, int row, int column) {
		super(color, row, column);
        symbol = color ? "R" : "r";
>>>>>>> 37128feed0cfcf543bbf382519131bafacc9ed90
	}

	public boolean move(int row, int column) {
		//Moving horizontally or vertically
		return (getCol() == column ^ getRow() == row);
	}
}