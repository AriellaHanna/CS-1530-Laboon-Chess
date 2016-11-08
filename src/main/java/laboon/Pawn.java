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
            // Remove captured piece
            if (capture(board, row, column))
            {
                if (enPassant(board,row,column))
                {
                    if (isWhite())
                        board.removeFromSpace(row-1,column,true);
                    else
                    board.removeFromSpace(row+1,column,true);
                }
                else
                    board.removeFromSpace(row,column,true);
            }
            board.addToSpace(row,column, this); //Move piece to new space
            setCol(column);
            setRow(row);
            setMoved(true);
            return true;
        }
        else
            return false;
    }

    //Capture a piece/move diagonally
    private boolean capture(Board board, int row, int column){
        if (isWhite())
            return (column-getCol() == 1 && row-getRow() == 1 && ((!board.spaceIsEmpty(row,column)
                    && !board.getSpaceColor(row,column)) || enPassant(board,row,column)));
        else
            return (getCol()-column == 1 && getRow()-row == 1 && ((!board.spaceIsEmpty(row,column)
                    && board.getSpaceColor(row,column)) || enPassant(board,row,column)));
    }

    /*En passant move (Capture a pawn that moved two spaces as if it had only moved one)
      Current bug: Say a black pawn started at B7, captured a piece at A6, and then moved
      to A5, they could still be captured by a white pawn going from B5 to A6
    */
    private boolean enPassant(Board board, int row, int column){
        if (isWhite())
            // True if we're trying to move to row 6 and the pawn that initialized in row 7
            // is in row 5
            return (row == 5 && !board.spaceIsEmpty(row,column) && !board.getSpaceColor(row-1,column)
                    && board.getSpacePiece(row-1,column).getSymbol().equals("P"));
        else
            // True if we're trying to move to row 3 and the pawn that initialized in row 2
            // is in row 4
            return (row == 2 && !board.spaceIsEmpty(row,column) && board.getSpaceColor(row+1,column)
                    && board.getSpacePiece(row+1,column).getSymbol().equals("P"));
    }

    //Movement of black Pawn
    private boolean blackMove(Board board, int row, int column){
        //First move, can move two spaces
        if (!getMoved()){
            if (row-getRow() == 2)
                return board.spaceIsEmpty(row,column) && board.spaceIsEmpty(row-1,column);
            else if (row - getRow() == 1)
                return board.spaceIsEmpty(row,column);
            else
                return false;
        }
        //Not first move, can only move one
        else
            return (row-getRow() == 1) && board.spaceIsEmpty(row,column);
    }

    //Movement of white pawn
    private boolean whiteMove(Board board, int row, int column){
        //First move, can move one or two
        if (!getMoved()){
            if (getRow() - row == 2)
                return board.spaceIsEmpty(row,column) && board.spaceIsEmpty(row+1,column);
            else if (getRow() - row == 1)
                return board.spaceIsEmpty(row,column);
            else
                return false;
        }
        //Not first move, can only move one
        else
            return (getRow()-row == 1) && board.spaceIsEmpty(row,column);
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