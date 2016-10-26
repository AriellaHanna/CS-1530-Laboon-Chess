package laboon;
public class King extends Piece {
	private boolean check = false; //Whether or not king is in check, set to false until checking for check is implemented
	//Constructor
<<<<<<< HEAD
	public King(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "K";
=======
	public King(boolean color, int row, int column) {
		super(color, row, column);
		symbol = color ? "K" : "k";
>>>>>>> 37128feed0cfcf543bbf382519131bafacc9ed90
	}

	public boolean move(int row, int column) {
		//Make sure King is moving exactly one space
		return ((Math.abs(column-getCol()) <= 1) && (Math.abs(row-getRow()) <=1) && !(column == getCol() && row == getRow()));
	}
}