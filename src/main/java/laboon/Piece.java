package laboon;
public class Piece {
	private boolean taken; //True if piece has been captured, false otherwise
	private boolean white; //True if white, false if black
	private int r; //Row piece is in
	private int c; //Column piece is in
	protected String symbol; // Letter representing the piece on the board

	//Constructor
	public Piece(boolean color, int row, int column) {
		white = color;
		r = row;
		c = column;
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
}