package laboon;
public class King extends Piece {
	//Constructor
	public King(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "K";
	}
	
	// Returns if move is legal
	public boolean move(Board board, int row, int column) {
		//Make sure King is moving exactly one space and there are no allies on space
		if ((Math.abs(column-getCol()) <= 1) && (Math.abs(row-getRow()) <=1) &&
			 !(column == getCol() && row == getRow()) && clearPath(board, row, column))
		{
			board.removeFromSpace(getRow(),getCol(),false); //Remove piece from old destination
			int oldRow = getRow();
			int oldCol = getCol();
			Piece savedPiece = null;
			if (capture(board, row, column))
			{
				
				savedPiece = board.removeFromSpace(row,column, true); //Remove captured piece
			}
			board.addToSpace(row,column, this); //Move piece to new space
			setCol(column);
			setRow(row);
			if (isWhite() && board.whiteCheck()){
				undo(board, oldRow,oldCol,savedPiece);
				return false;
			}
			else if (!isWhite() && board.blackCheck()){
				undo(board, oldRow,oldCol,savedPiece);
				return false;
			}
			else{
				hasMoved();
				return true;
			}
		}
		else
			return false;
	}
	

	// Returns true if there are no ally pieces in the King's path, false otherwise
	private boolean clearPath(Board board, int row, int column)
	{
		return (board.spaceIsEmpty(row,column) || capture(board,row,column));
	}	
	
	// Returns true if the piece on a space is the enemy
	private boolean capture(Board board, int row, int column)
	{
		return (!board.spaceIsEmpty(row,column) && board.getSpaceColor(row,column) != isWhite());
	}
	
	// This will test if the king is currently in check, always false for now
	public boolean inCheck(Board board){
		return (!diagonalSafe(board) || !straightSafe(board) || !knightSafe(board));
	}
	
	//Check if king is threatened diagonally
	private boolean diagonalSafe(Board board){
		// Up and right
		for (int y = getRow()-1, x = getCol()+1; y >= 0 && x <= 7; y--, x++)
		{
			//Ally piece is protecting diagonal path
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) == isWhite())
				break;
			// Queen or Bishop can capture King
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) != isWhite() &&
			(board.getSpacePiece(y,x).getSymbol().equals("B") || board.getSpacePiece(y,x).getSymbol().equals("Q")))
				return false;
			// Enemy pawn can capture King
			if (isWhite() && y == getRow()-1 && !board.spaceIsEmpty(y,x) && !board.getSpaceColor(y,x)
			&& board.getSpacePiece(y,x).getSymbol().equals("P"))
				return false;

		}
		// Up and left
		for (int y = getRow()-1, x = getCol()-1; y >= 0 && x >= 0; y--, x--)
		{
			//Ally piece is protecting diagonal path
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) == isWhite())
				break;
			// Queen or Bishop can capture King
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) != isWhite() &&
			(board.getSpacePiece(y,x).getSymbol().equals("B") || board.getSpacePiece(y,x).getSymbol().equals("Q")))
				return false;
			// Enemy pawn can capture King
			if (isWhite() && y == getRow()-1 && !board.spaceIsEmpty(y,x) && !board.getSpaceColor(y,x)
			&& board.getSpacePiece(y,x).getSymbol().equals("P"))
				return false;
		}
		// Down and left
		for (int y = getRow()+1, x = getCol()-1; y <= 7 && x >=0; y++, x--)
		{
			//Ally piece is protecting diagonal path
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) == isWhite())
				break;
			// Queen or Bishop can capture King
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) != isWhite() &&
			(board.getSpacePiece(y,x).getSymbol().equals("B") || board.getSpacePiece(y,x).getSymbol().equals("Q")))
				return false;
			// Enemy pawn can capture King
			if (!isWhite() && y == getRow()+1 && !board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x)
			&& board.getSpacePiece(y,x).getSymbol().equals("P"))
				return false;
		}
		// Down and right
		for (int y = getRow()+1, x = getCol()+1; y <= 7 && x <= 7; y++, x++)
		{
			//Ally piece is protecting diagonal path
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) == isWhite())
				break;
			// Queen or Bishop can capture King
			if (!board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x) != isWhite() &&
			(board.getSpacePiece(y,x).getSymbol().equals("B") || board.getSpacePiece(y,x).getSymbol().equals("Q")))
				return false;
			// Enemy pawn can capture King
			if (!isWhite() && y == getRow()+1 && !board.spaceIsEmpty(y,x) && board.getSpaceColor(y,x)
			&& board.getSpacePiece(y,x).getSymbol().equals("P"))
				return false;
		}
		return true;
	}
	
	//Check if king is threatened horizontally or vertically
	private boolean straightSafe(Board board){
		//Up
		for (int y = getRow()-1; y >=0; y--){
			//Ally piece is blocking path
			if (!board.spaceIsEmpty(y,getCol()) && board.getSpaceColor(y,getCol()) == isWhite())
				break;
			//King is threatened by Rook or Queen
			if (!board.spaceIsEmpty(y,getCol()) && board.getSpaceColor(y,getCol()) != isWhite() &&
			(board.getSpacePiece(y,getCol()).getSymbol().equals("R") || board.getSpacePiece(y,getCol()).getSymbol().equals("Q")))
				return false;
		}
		//Down
		for (int y = getRow()+1; y <=7; y++){
			//Ally piece is blocking path
			if (!board.spaceIsEmpty(y,getCol()) && board.getSpaceColor(y,getCol()) == isWhite())
				break;
			//King is threatened by Rook or Queen
			if (!board.spaceIsEmpty(y,getCol()) && board.getSpaceColor(y,getCol()) != isWhite() &&
			(board.getSpacePiece(y,getCol()).getSymbol().equals("R") || board.getSpacePiece(y,getCol()).getSymbol().equals("Q")))
				return false;
		}
		//Right
		for (int x = getCol()+1; x <=7; x++){
			//Ally piece is blocking path
			if (!board.spaceIsEmpty(getRow(),x) && board.getSpaceColor(getRow(),x) == isWhite())
				break;
			//King is threatened by Rook or Queen
			if (!board.spaceIsEmpty(getRow(),x) && board.getSpaceColor(getRow(),x) != isWhite() &&
			(board.getSpacePiece(getRow(),x).getSymbol().equals("R") || board.getSpacePiece(getRow(),x).getSymbol().equals("Q")))
				return false;
		}
		//Left
		for (int x = getCol()-1; x >=0; x--){
			//Ally piece is blocking path
			if (!board.spaceIsEmpty(getRow(),x) && board.getSpaceColor(getRow(),x) == isWhite())
				break;
			//King is threatened by Rook or Queen
			if (!board.spaceIsEmpty(getRow(),x) && board.getSpaceColor(getRow(),x) != isWhite() &&
			(board.getSpacePiece(getRow(),x).getSymbol().equals("R") || 
			board.getSpacePiece(getRow(),x).getSymbol().equals("Q")))
				return false;
		}
		return true;
	}
	
	//Check if king is threatened by a knight
	private boolean knightSafe(Board board){
		// Checks all possible positions where an enemy knight could capture a king
		Piece[] knightSpaces = new Piece[8];
		knightSpaces[0] = board.getSpacePiece(getRow()+2,getCol()+1);
		knightSpaces[1] = board.getSpacePiece(getRow()+2,getCol()-1);
		knightSpaces[2] = board.getSpacePiece(getRow()+1,getCol()+2);
		knightSpaces[3] = board.getSpacePiece(getRow()+1,getCol()-2);
		knightSpaces[4] = board.getSpacePiece(getRow()-2,getCol()+1);
		knightSpaces[5] = board.getSpacePiece(getRow()-2,getCol()-1);
		knightSpaces[6] = board.getSpacePiece(getRow()-1,getCol()+2);
		knightSpaces[7] = board.getSpacePiece(getRow()-1,getCol()-2);
		for (int i = 0; i <8; i++)
		{
			//There is a knight that could take the king from its position
			if (knightSpaces[i] != null && knightSpaces[i].isWhite()!=isWhite()
			 && knightSpaces[i].getSymbol().equals("Kn"))
				return false;
		}
		// There are no knights in position to take the king
		return true;
	}
}