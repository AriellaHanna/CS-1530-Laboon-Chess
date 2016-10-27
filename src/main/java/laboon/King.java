package laboon;
public class King extends Piece {
	private boolean check = false; //Whether or not king is in check, set to false until checking for check is implemented
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
	
	// Return if the king is in check
	public boolean inCheck(){
		return check;
	}
	
	// This will test if the king is currently in check, always false for now
	private boolean inCheck(Board board){
		return false;
	}
}