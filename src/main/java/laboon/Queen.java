package laboon;
public class Queen extends Piece {

	//Constructor
	public Queen(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Q";
	}

	//Move queen
	public boolean move(int h, int v) {
		//Return true if piece can move diagonal or straight
		return moveDiagonal(h,v) || moveStraight(h,v);
	}

	//Check if diagonal movement
	private boolean moveDiagonal(int h, int v) {
		return (Math.abs(h-getX()) == Math.abs(v-getY()));
	}

	//Check if straight movement
	private boolean moveStraight(int h, int v){
		return (getX() == h ^ getY() == v);
	}

}