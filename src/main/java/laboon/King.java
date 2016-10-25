package laboon;
public class King extends Piece {

	//Constructor
	public King(boolean color, int row, int column) {
		super(color, row, column);
		symbol = color ? "K" : "k";
	}

	public boolean move(int row, int column) {
		//Make sure King is moving exactly one space
		return ((Math.abs(column-getCol()) <= 1) && (Math.abs(row-getRow()) <=1) && !(column == getCol() && row == getRow()));
	}
}