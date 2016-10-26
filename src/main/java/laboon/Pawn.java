package laboon;
public class Pawn extends Piece {
	private boolean hasMoved; //Whether or not pawn has moved yet
	//Constructor
	public Pawn(boolean color, int row, int column) {
		super(color, row, column);
		hasMoved = false;
		symbol = "P";
	}

	//Move the pawn
	public boolean move(int row, int column) {
		if (column != getCol())
			return capture(row,column);
		else if (isWhite())
			return whiteMove(row,column);
		else
			return blackMove(row,column);
	}

	//Capture a piece/move diagonally
	private boolean capture(int row, int column){
		if (isWhite())
			return (column-getCol() == 1 && row-getRow() == 1);
		else
			return (getCol()-column == 1 && getRow()-row == 1);
	}

	//Movement of white Pawn
	private boolean whiteMove(int row, int column){
		//First move, can move two spaces
		if (!getMoved()){
			setMoved(true);
			return (row-getRow() <= 2 && row > getRow());
		}
		//Not first move, can only move one
		else
			return row-getRow() == 1;
	}

	//Movement of black pawn
	private boolean blackMove(int row, int column){
		//First move, can move one or two
		if (!getMoved()){
			setMoved(true);
			return (getRow()-row <= 2 && row < getRow());
		}
		//Not first move, can only move one
		else
			return getRow()-row == 1;
	}

	public void setMoved(boolean status){
		hasMoved = status;
	}

	public boolean getMoved(){
		return hasMoved;
	}
}