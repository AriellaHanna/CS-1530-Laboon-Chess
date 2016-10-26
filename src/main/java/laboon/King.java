package laboon;
public class King extends Piece {
	private boolean check = false; //Whether or not king is in check, set to false until checking for check is implemented
	//Constructor
	public King(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "K";
	}
	public boolean move(int row, int column) {
		//Make sure King is moving exactly one space
		return ((Math.abs(column-getCol()) <= 1) && (Math.abs(row-getRow()) <=1) && !(column == getCol() && row == getRow()));
	}
}