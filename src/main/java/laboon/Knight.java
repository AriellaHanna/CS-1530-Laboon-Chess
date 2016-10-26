package laboon;
public class Knight extends Piece {

	//Constructor
	public Knight(boolean color, int hori, int vert) {
		super(color, hori, vert);
		symbol = "Kn";
	}
	//Knight movement
	public boolean move(Board board, int row, int column) {
		// Knight can move horizontal two and vertical one or vice verse and there is no ally piece on the destination
		if ((Math.abs(column-getCol())==2 && Math.abs(row-getRow())==1)^(Math.abs(column-getCol())==1 && Math.abs(row-getRow())==2)
			&& (capture(board, row, column) || board.spaceIsEmpty(row,column)))
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
	
	// True if there is an enemy piece on destination
	private boolean capture(Board board, int row, int column){
		return (!board.spaceIsEmpty(row,column) && board.getSpaceColor(row,column) != isWhite());
	}
}