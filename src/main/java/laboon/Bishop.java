package laboon;

public class Bishop extends Piece {

	//Constructor
	public Bishop(boolean color, int row, int column) {
		super(color, row, column);
        symbol = color ? "B" : "b";
	}

	public boolean move(Board board, int row, int column) {
		//rise over run = |1| and there are no pieces in the path
		if (Math.abs(column-getCol()) == Math.abs(row-getRow()) && clearPath(board, row, column))
		{
			board.removeFromSpace(getRow(),getCol(),false); //Remove piece from old destination
			if (capture(board, row, column))
			{
				
				board.removeFromSpace(row,column, true); //Remove captured piece
			}
			board.addToSpace(row,column, this); //Move piece to new space
			setCol(column);
			setRow(row);
			return true;
		}
		else
			return false;
	}
	
	// Checks if path is clear for move
	private boolean clearPath(Board board, int row, int column) {
		//Moving up and right
		if (row > getRow() && column > getCol())
		{
			for (int y = getRow()+1, x = getCol()+1; x <= column && y <= row; x++, y++)
			{
				// There is a piece on the path
				if (!board.spaceIsEmpty(y,x))
				{
					if (x == column && y == row)
						return capture(board, row, column);
					else
						return false;
				}
			}
			return true;
		}
		//Moving down and right
		else if (row < getRow() && column > getCol())
		{
			for (int y = getRow()-1, x = getCol()+1; x <= column && y >= row; x++, y--)
			{
				// There is a piece on the path
				if (!board.spaceIsEmpty(y,x))
					if (x == column && y == row)
						return capture(board, row, column);
					else
						return false;
			}
			return true;
		}
		//Moving up and left
		else if (row > getRow() && column < getCol())
		{
			for (int y = getRow()+1, x = getCol()-1; x >= column && y <= column; x--, y++)
			{
				// There is a piece on the path
				if (!board.spaceIsEmpty(y,x))
					if (x == row && y == column)
						return capture(board, row, column);
					else
						return false;
			}
			return true;
		}
		//Moving down and left
		else
		{
			for (int x = getRow()-1, y = getCol()-1; x >= row && y >= column; x--, y--)
			{
				// There is a piece on the path
				if (!board.spaceIsEmpty(y,x))
					if (x == column && y == row)
						return capture(board, row, column);
					else
						return false;
			}
			return true;
		}
	}
	
	// Checks if piece on destination is enemy
	private boolean capture(Board board, int row, int column)
	{
		return (!board.spaceIsEmpty(row,column) && (board.getSpaceColor(row,column) != isWhite()));

	}

}