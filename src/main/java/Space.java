public class Space {
	private int x;
	private int y;
	private Piece piece;
	
	//Blank space
	public Space(int h, int v) {
		x = h;
		y = v;
		piece = null;
	}
	
	//Create space with piece on it
	public Space (int h, int v, Piece token) {
		x = h;
		y = h;
		piece = token;
	}

	//Put piece on a space
	public void add(Piece token) {
		piece = token;
	}
	
	// Returns true if there is not a piece on the space
	public boolean isEmpty() {
		return piece == null;
	}
	
	// Returns color of the piece on the space
	public boolean getColor() {
		return piece.isWhite();
	}
	
	// Takes piece off of space and returns the piece
	public Piece remove() {
		Piece toRemove = piece;
		piece = null;
		return toRemove;
	}
}