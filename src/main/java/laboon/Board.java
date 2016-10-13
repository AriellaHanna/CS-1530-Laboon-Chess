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
		spaces[0][7].add(new Rook(true,0,7));
		spaces[0][1].add(new Knight(true,0,1));
		spaces[0][6].add(new Knight(true,0,6));
		spaces[0][2].add(new Bishop(true,0,2));
		spaces[0][5].add(new Bishop(true,0,5));
		spaces[0][3].add(new Queen(true,0,3));
		spaces[0][4].add(new King(true,0,4));
		for (int i = 0; i < 8; i++)
			spaces[1][i].add(new Pawn(true,1,i));
	}
	
	//Adds all black pieces
	private void addBlacks() {
		spaces[7][0].add(new Rook(false,7,0));
		spaces[7][7].add(new Rook(false,7,7));
		spaces[7][1].add(new Knight(false,7,1));
		spaces[7][6].add(new Knight(false,7,6));
		spaces[7][2].add(new Bishop(false,7,2));
		spaces[7][5].add(new Bishop(false,7,5));
		spaces[7][3].add(new Queen(false,7,3));
		spaces[7][4].add(new King(false,7,4));
		for (int i = 0; i < 8; i++)
			spaces[6][i].add(new Pawn(false,6,i));
	}
	
	//Return a space
	public Space getSpace(int x, int y) {
		return spaces[x][y];
	}
	
}