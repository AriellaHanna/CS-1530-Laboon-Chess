package laboon;
public class Pawn extends Piece {
	private boolean hasMoved; //Whether or not pawn has moved yet
	//Constructor
	public Pawn(boolean color, int hori, int vert) {
		super(color, hori, vert);
		hasMoved = false;
		symbol = "P";
	}

	//Move the pawn
	public boolean move(int h, int v) {
		if (h != getX())
			return capture(h,v);
		else if (isWhite())
			return whiteMove(h,v);
		else
			return blackMove(h,v);
	}

	//Capture a piece/move diagonally
	private boolean capture(int h, int v){
		if (isWhite())
			return (h-getX() == 1 && v-getY() == 1);
		else
			return (getX()-h == 1 && getY()-v == 1);
	}

	//Movement of white Pawn
	private boolean whiteMove(int h, int v){
		//First move, can move two spaces
		if (!getMoved()){
			setMoved(true);
			return (v-getY() <= 2 && v > getY());
		}
		//Not first move, can only move one
		else
			return v-getY() == 1;
	}

	//Movement of black pawn
	private boolean blackMove(int h, int v){
		//First move, can move one or two
		if (!getMoved()){
			setMoved(true);
			return (getY()-v <= 2 && v < getY());
		}
		//Not first move, can only move one
		else
			return getY()-v == 1;
	}

	public void setMoved(boolean status){
		hasMoved = status;
	}

	public boolean getMoved(){
		return hasMoved;
	}
}