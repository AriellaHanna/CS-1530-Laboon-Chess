package laboon;
public class Board {
	private Space[][] spaces = new Space[8][8]; //Array of all spaces on the board
	
	//Creates the board at the start of the game
	public Board() {
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j ++){
				spaces[i][j] = new Space(i, j);
			}
		}
		addWhites();
		addBlacks();
	}
	
	//Adds all white pieces
	private void addWhites() {
		spaces[0][0].add(new Rook(true,0,0));
		spaces[7][0].add(new Rook(true,7,0));
		spaces[1][0].add(new Knight(true,1,0));
		spaces[6][0].add(new Knight(true,6,0));
		spaces[2][0].add(new Bishop(true,2,0));
		spaces[5][0].add(new Bishop(true,5,0));
		spaces[3][0].add(new Queen(true,3,0));
		spaces[4][0].add(new King(true,4,0));
		for (int i = 0; i < 8; i++)
			spaces[i][1].add(new Pawn(true,i,1));
	}
	
	//Adds all black pieces
	private void addBlacks() {
		spaces[0][7].add(new Rook(false,0,0));
		spaces[7][7].add(new Rook(false,7,0));
		spaces[1][7].add(new Knight(false,1,0));
		spaces[6][7].add(new Knight(false,6,0));
		spaces[2][7].add(new Bishop(false,2,0));
		spaces[5][7].add(new Bishop(false,5,0));
		spaces[3][7].add(new Queen(false,3,0));
		spaces[4][7].add(new King(false,4,0));
		for (int i = 0; i < 8; i++)
			spaces[i][6].add(new Pawn(false,i,6));
	}
	
	//Return a space
	public Space getSpace(int x, int y) {
		return spaces[x][y];
	}
	
}