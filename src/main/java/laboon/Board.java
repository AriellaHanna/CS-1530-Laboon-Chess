package laboon;
public class Board {
	private Space[][] spaces = new Space[8][8]; //Array of all spaces on the board
	private Piece[] takenBlack = new Piece[16]; //Array of captured black pieces
	private Piece[] takenWhite = new Piece[16];	//Array of captured white pieces
	private int capturedBlack = 0;
	private int capturedWhite = 0;
	
	//Creates the board at the start of the game
	public Board() {
		// initialize spaces on board
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j ++){
				spaces[i][j] = new Space(i, j);
			}
		}
		//initialize capture pieces as empty
		for (int i = 0; i < 16; i++)
		{
			takenBlack[i] = null;
			takenWhite[i] = null;
		}
		addWhites();
		addBlacks();
	}
	
	//Adds all white pieces
	private void addWhites() {
		spaces[0][0].add(new Rook(true,0,0));
		spaces[0][7].add(new Rook(true,0,7));
		spaces[0][1].add(new Knight(true,0,1));
		spaces[0][6].add(new Knight(true,0,6));
		spaces[0][2].add(new Bishop(true,0,2));
		spaces[0][5].add(new Bishop(true,0,5));
		spaces[0][3].add(new Queen(true,0,3));
		spaces[0][4].add(new King(true,0,4));
		for (int i = 0; i < 8; i++)
			spaces[1][i].add(new Pawn(true,1,i));
	}
	
	//Adds all black pieces
	private void addBlacks() {
		spaces[7][0].add(new Rook(false,7,0));
		spaces[7][7].add(new Rook(false,7,7));
		spaces[7][1].add(new Knight(false,7,1));
		spaces[7][6].add(new Knight(false,7,6));
		spaces[7][2].add(new Bishop(false,7,2));
		spaces[7][5].add(new Bishop(false,7,5));
		spaces[7][3].add(new Queen(false,7,3));
		spaces[7][4].add(new King(false,7,4));
		for (int i = 0; i < 8; i++)
			spaces[6][i].add(new Pawn(false,6,i));
	}
	
	// Getter for array of captured black pieces
	public Piece[] getCapturedBlack()
	{
		return takenBlack;
	}
	
	// Getter for array of captured white pieces
	public Piece[] getCapturedWhite()
	{
		return takenWhite;
	}
	
	// Getter for number of captured black pieces
	public int getNumCapturedBlack()
	{
		return capturedBlack;
	}
	
	// Getter for number of captured white pieces
	public int getNumCapturedWhite()
	{
		return capturedWhite;
	}
	
	// Setter for number of captured black pieces
	public void setCapturedBlack(int n)
	{
		capturedBlack = n;
	}
	
	// Setter for number of captured white pieces
	public void setCapturedWhite(int n)
	{
		capturedBlack = n;
	}
	
	// Takes a captured piece and puts it in proper captured array
	public void capture(Piece piece)
	{
		if (piece.isWhite() && getNumCapturedWhite() < 16)
		{
			takenWhite[getNumCapturedWhite()] = piece;
			setCapturedWhite(getNumCapturedWhite()+1);
		}
		else if (!piece.isWhite() && getNumCapturedBlack() < 16)
		{
			takenBlack[getNumCapturedBlack()] = piece;
			setCapturedBlack(getNumCapturedBlack()+1);
		}		
	}
	
	//Return a space
	public Space getSpace(int row, int column) {
		return spaces[row][column];
	}
	
	// Get the piece on a space
	public Piece getSpacePiece(int row, int column) {
		return getSpace(row,column).getPiece();
	}
	
	// Get the color of a piece on a space
	public boolean getSpaceColor(int row, int column){
		return getSpacePiece(row,column).isWhite();
	}
	
	// Find if space is empty
	public boolean spaceIsEmpty(int row, int column){
		return getSpace(row, column).isEmpty();
	}
	
	// Put a piece on a space
	public void addToSpace(int row, int column, Piece piece){
		getSpace(row,column).add(piece);
	}
	
	// Remove a piece from a space
	public Piece removeFromSpace(int row, int column, boolean capture) {
		if (spaceIsEmpty(row, column))
			return null;
		else
		{
			Piece piece = getSpace(row,column).remove();
			if (capture)
				capture(piece);
			return piece;	
		}	
	}
	
}