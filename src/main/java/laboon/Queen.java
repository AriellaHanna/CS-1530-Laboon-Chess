package laboon;
public class Queen extends Piece {

	//Constructor
<<<<<<< HEAD
	public Queen(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Q";
=======
	public Queen(boolean color, int row, int column) {
		super(color, row, column);
		symbol = color ? "Q" : "q";
>>>>>>> 37128feed0cfcf543bbf382519131bafacc9ed90
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