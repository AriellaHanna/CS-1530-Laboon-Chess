package laboon;
public class Queen extends Piece {

	//Constructor
	public Queen(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Q";
	}

	//Move queen
	public boolean move(Board board, int row, int column) {
		//Return true if piece can move diagonal or straight
		if (moveDiagonal(board, row,column) || moveStraight(board, row,column))
		{
			board.removeFromSpace(getRow(),getCol(),false);
			if (capture(board,row,column))
			{
				board.removeFromSpace(row,column,true);
			}
			board.addToSpace(row,column,this);
			setCol(column);
			setRow(row);
			return true;
		}
		else
			return false;
	}

	//Check if diagonal movement
	private boolean moveDiagonal(Board board, int row, int column) {
		return (Math.abs(column-getCol()) == Math.abs(row-getRow())&&clearDiagonal(board,row,column));
	}

	//Check if straight movement
	private boolean moveStraight(Board board, int row, int column){
		return ((getCol() == column ^ getRow() == row)&&clearStraight(board,row,column));
	}
	
	// Check if diagonal path is clear
	private boolean clearDiagonal(Board board,int row,int column){
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
	
	//Check if horizontal or vertical path is clear
	private boolean clearStraight(Board board, int row, int column){
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
	
	//Check if there is an enemy piece on destination
	private boolean capture(Board board, int row, int column){
		return (!board.spaceIsEmpty(row,column) && board.getSpaceColor(row,column) != isWhite());
	}

}