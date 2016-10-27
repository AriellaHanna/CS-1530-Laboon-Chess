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
		if (clearPath(board, row, column)&&(getCol() == column ^ getRow() == row))
		{
			board.removeFromSpace(getRow(),getCol(),false); //Remove piece from old destination
			if (capture(board, row, column))
			{
				
				board.removeFromSpace(row,column,true); //Remove captured piece
			}
			board.addToSpace(row,column, this); //Move piece to new space
			setCol(column);
			setRow(row);
			return true;
		}
		else
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