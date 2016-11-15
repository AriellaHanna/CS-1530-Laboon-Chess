package laboon;
public abstract class Piece {
	private boolean taken; //True if piece has been captured, false otherwise
	private boolean white; //True if white, false if black
	private int r; //Row piece is in
	private int c; //Column piece is in
	private final int originC; //Orignal column position
	private final int originR; //Orignal row position
	protected String symbol; // Letter representing the piece on the board

	//Constructor
	public Piece(boolean color, int row, int column) {
		white = color;
		r = row;
		c = column;
		originC = column;
		originR = row;
	}

	// Move the piece, return true if completed, false if illegal
	public abstract boolean move(Board board, int row, int column);

	// Undos a move, currently only happens when a move causes check
	public void undo(Board board, int r, int c, Piece piece){
		board.removeFromSpace(getRow(),getCol(),false);
		board.addToSpace(r,c,this);
		setRow(r);
		setRow(c);
		if (piece != null)
			board.addToSpace(piece.getRow(), piece.getCol(), piece);
	}
	// Getter for the row
	public int getRow() {
		return r;
	}

	//Setter for the row
	public void setRow(int row) {
		r = row;
	}

	// Getter for the column
	public int getCol() {
		return c;
	}

	// Setter for the column
	public void setCol(int col) {
		c = col;
	}

	// Getter for the color
	public boolean isWhite() {
		return white;
	}

	// Getter for the symbol
	public String getSymbol() {
		return symbol;
	}
	
	//Getter for origin column
	public int getOriginC(){
		return originC;
	}
	
	//Getter for origin row
	public int getOriginR(){
		return originR;
	}
}