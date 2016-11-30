package laboon;
public class Rook extends Piece {

	//Constructor
	public Rook(boolean color, int hori, int vert) {
		super(color, hori, vert);
        symbol = "R";
	}

	// Move the piece, return true if completed, false if illegal
	public boolean move(Board board, int row, int column) {
		//Moving horizontally or vertically
		if ((clearPath(board, row, column)&&(getCol() == column ^ getRow() == row))^castle(board,row,column))
		{
			board.removeFromSpace(getRow(),getCol(),false); //Remove piece from old destination
			int oldRow = getRow();
			int oldCol = getCol();
			Piece savedPiece = null;
			if (capture(board, row, column))
			{
				
				savedPiece = board.removeFromSpace(row,column, true); //Remove captured piece
			}
			else if (castle(board,row,column)){
				savedPiece = board.removeFromSpace(row,column, false); // Move king
				//Long castle
				if (getCol() == 0){
					board.addToSpace(row, column-2, savedPiece);
					savedPiece.setCol(column-2);
					board.addToSpace(row,3, this);
					setCol(3);
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
			else if (!isWhite() && board.blackCheck()){
				undo(board, oldRow,oldCol,savedPiece);
				return false;
			}
				}
				//Short castle
				else{
					board.addToSpace(row,column+2, savedPiece);
					savedPiece.setCol(column+2);
					board.addToSpace(row,5, this);
					setCol(5);
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
	
	//Checks if castle is legal
	private boolean castle(Board board, int row, int column){
		//White rook
		if (isWhite()){
			//Trying to move to kings space
			if (row == 7 && column == 4){
				//Left rook
				if (getOriginC() == 0 && !hasMoved()){
					//Clear path from rook to king
					if (board.spaceIsEmpty(7,1) && board.spaceIsEmpty(7,2)&& board.spaceIsEmpty(7,3)){
						Piece piece = board.getSpacePiece(7,4);
						//King is white and has not moved
						if (piece != null){
							return (piece.isWhite() && !piece.hasMoved() && piece.getSymbol().equals("K"));
						}
					}
				}
				//Right rook
				else if (getOriginC() == 7 && !hasMoved()){
					//Clear path from rook to king
					if (board.spaceIsEmpty(7,6) && board.spaceIsEmpty(7,5)){
						Piece piece = board.getSpacePiece(7,4);
						//King is white and has not moved
						if (piece != null){
							return (piece.isWhite() && !piece.hasMoved() && piece.getSymbol().equals("K"));
						}
					}
				}
			}
		}
		//Black rook
		else{
			//Trying to move to kings space
			if (row == 0 && column == 4){
				//Left rook
				if (getOriginC() == 0 && !hasMoved()){
					//Clear path from rook to king
					if (board.spaceIsEmpty(0,1) && board.spaceIsEmpty(0,2)&& board.spaceIsEmpty(0,3)){
						Piece piece = board.getSpacePiece(0,4);
						//King is black and has not moved
						if (piece != null){
							return (!piece.isWhite() && !piece.hasMoved() && piece.getSymbol().equals("K"));
						}
					}
				}
				//Right rook
				else if (getOriginC() == 7 && !hasMoved()){
					//Clear path from rook to king
					if (board.spaceIsEmpty(0,6) && board.spaceIsEmpty(0,5)){
						Piece piece = board.getSpacePiece(0,4);
						//King is black and has not moved
						if (piece != null){
							return (!piece.isWhite() && !piece.hasMoved() && piece.getSymbol().equals("K"));
						}
					}
				}
			}
		}
		return false;
	}
	
	// Checks if the path to destination is clear
	private boolean clearPath(Board board, int row, int column) {
		// Moving down
		if (getCol() == column && row < getRow())
		{
			for (int i = getRow()-1; i >= row; i--)
			{
				if (!board.spaceIsEmpty(i,column))
				{
					if (i == row)
						return capture(board, row, column);
					else
						return false;
				}
			}
			return true;
		}
		// Moving up
		else if (getCol() == column && row > getRow())
		{
			for (int i = getRow()+1; i <= row; i++)
			{
				if (!board.spaceIsEmpty(i,column))
				{
					if (i == row)
						return capture(board, row, column);
					else
						return false;
				}
			}
			return true;
		}
		// Moving left
		else if (column < getCol() && row == getRow())
		{
			for (int i = getCol()-1; i >= column; i--)
			{
				if (!board.spaceIsEmpty(row,i))
				{
					if (i == column)
						return capture(board, row, column);
					else
						return false;
				}
			}
			return true;
		}
		// Moving right
		else
		{
			for (int i = getCol()+1; i <= column; i++)
			{
				if (!board.spaceIsEmpty(row,i))
				{
					if (i == column)
						return capture(board, row, column);
					else
						return false;
				}
			}
			return true;
		}
	}
	
	private boolean capture(Board board, int row, int column){
		return !board.spaceIsEmpty(row,column) && board.getSpaceColor(row,column) != isWhite();
	}
}