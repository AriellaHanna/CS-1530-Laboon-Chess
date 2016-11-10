package laboon;
public class Pawn extends Piece {
    private boolean hasMoved; //Whether or not pawn has moved yet
    //Constructor
    public Pawn(boolean color, int row, int column) {
        super(color, row, column);
        hasMoved = false;
        symbol = "P";
    }

    //Move the pawn
    public boolean move(Board board, int row, int column) {
        if ((column != getCol() && capture(board, row, column)) ^ (isWhite() && whiteMove(board,row,column))
            ^(!isWhite() && blackMove(board,row,column)))
        {
            board.removeFromSpace(getRow(),getCol(),false); //Remove piece from old destination
            int oldRow = getRow();
            int oldCol = getCol();
            Piece savedPiece = null;
            // Remove captured piece
            if (capture(board, row, column))
            {
                if (enPassant(board,row,column))
                {
                    if (isWhite())
                        savedPiece = board.removeFromSpace(row+1,column,true);
                    else
                    savedPiece = board.removeFromSpace(row-1,column,true);
                }
                else
                   savedPiece = board.removeFromSpace(row,column,true);
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
			else
            {	setMoved(true);
            	return true;
            }
        }
        else
            return false;
    }

    //Capture a piece/move diagonally
    private boolean capture(Board board, int row, int column){
        if (isWhite())
            return (Math.abs(column-getCol()) == 1 && getRow()-row == 1 && ((!board.spaceIsEmpty(row,column)
                    && !board.getSpaceColor(row,column)) || enPassant(board,row,column)));
        else
            return (Math.abs(getCol()-column) == 1 && row-getRow() == 1 && ((!board.spaceIsEmpty(row,column)
                    && board.getSpaceColor(row,column)) || enPassant(board,row,column)));
    }

    //En passant move (Capture a pawn that moved two spaces as if it had only moved one)
    private boolean enPassant(Board board, int row, int column){
        if (isWhite())
            // True if we're trying to move to row 6 and the pawn that initialized in row 7
            // is in row 5
            return (row == 2 && board.spaceIsEmpty(row,column) && !board.getSpaceColor(row+1,column)
                    && board.getSpacePiece(row+1,column).getSymbol().equals("P") &&
                    board.getSpacePiece(row+1,column).getOriginC() == column);
        else
            // True if we're trying to move to row 3 and the pawn that initialized in row 2
            // is in row 4
            return (row == 5 && board.spaceIsEmpty(row,column) && board.getSpaceColor(row-1,column)
                    && board.getSpacePiece(row-1,column).getSymbol().equals("P") &&
                    board.getSpacePiece(row-1,column).getOriginC() == column);
    }

    //Movement of black Pawn
    private boolean blackMove(Board board, int row, int column){
        //First move, can move two spaces
        if (!getMoved()){
            if (row-getRow() == 2 && getCol() == column)
                return board.spaceIsEmpty(row,column) && board.spaceIsEmpty(row-1,column);
            else if (row - getRow() == 1 && getCol() == column)
                return board.spaceIsEmpty(row,column);
            else
                return false;
        }
        //Not first move, can only move one
        else
            return (row-getRow() == 1) && board.spaceIsEmpty(row,column) && getCol() == column;
    }

    //Movement of white pawn
    private boolean whiteMove(Board board, int row, int column){
        //First move, can move one or two
        if (!getMoved()){
            if (getRow() - row == 2 && getCol() == column)
                return board.spaceIsEmpty(row,column) && board.spaceIsEmpty(row+1,column);
            else if (getRow() - row == 1 && getCol() == column)
                return board.spaceIsEmpty(row,column);
            else
                return false;
        }
        //Not first move, can only move one
        else
            return (getRow()-row == 1) && board.spaceIsEmpty(row,column) && getCol() == column;
    }

    // Setter for hasMoved
    public void setMoved(boolean status){
        hasMoved = status;
    }

    // Getter for hasMoved
    public boolean getMoved(){
        return hasMoved;
    }
}