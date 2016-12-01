package laboon;
public class Space {
	private int x;
	private int y;
	private String tag;
	private Piece piece;

	//Blank space
	public Space(int h, int v, String[] numberText, String[] letterText) {
		x = h;
		y = v;
		tag = letterText[y] + numberText[x];
		piece = null;
	}
	
	//New space for testing purposes
	public Space(int h, int v){
		x = h;
		y = v;
		piece = null;
		tag = null;
	}

	//Create space with piece on it for testing
	public Space (int h, int v, Piece token) {
		x = h;
		y = h;
		tag = null;
		piece = token;
	}
	//Create space with piece on it
	public Space (int h, int v, Piece token, String[] numberText, String[] letterText) {
		x = h;
		y = h;
		tag = letterText[y] + numberText[x];
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

	// Returns the piece on the space
	public Piece getPiece() {
		return piece;
	}
	
	public String getTag(){
		return tag;
	}
}