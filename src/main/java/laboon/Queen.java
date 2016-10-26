package laboon;
public class Queen extends Piece {

	//Constructor
	public Queen(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Q";
	}

	//Move queen
	public boolean move(int row, int column) {
		//Return true if piece can move diagonal or straight
		return moveDiagonal(row,column) || moveStraight(row,column);
	}

	//Check if diagonal movement
	private boolean moveDiagonal(int row, int column) {
		return (Math.abs(column-getCol()) == Math.abs(row-getRow()));
	}

	//Check if straight movement
	private boolean moveStraight(int row, int column){
		return (getCol() == column ^ getRow() == row);
	}

}