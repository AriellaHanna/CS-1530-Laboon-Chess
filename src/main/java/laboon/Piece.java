package laboon;
public class Piece {
	private boolean taken; //True if piece has been captured, false otherwise
	private boolean white; //True if white, false if black
	private int x; //Horizontal position of piece
	private int y; //Vertical position of piece
	
	//Constructor
	public Piece(boolean color, int hori, int vert) {
		taken = false;
		white = color;
		x = hori;
		y = vert;
	}
	
	public boolean isTaken() {
		return taken;
	}
	
	public void setTaken(boolean status) {
		taken = status;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int h) {
		x = h;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int v) {
		y = v;
	}
	
	public boolean isWhite() {
		return white;
	}
}