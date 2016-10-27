package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
	
public class RookTest{
	private static Rook rook;
	private static Board board;
	
	// Create a board for all of the tests
	@BeforeClass
	public static void createBoard(){
		board = new Board();
	}
	// Add a rook to E5
	@Before
	public void addRook(){
		rook = new Rook(true,4,4);
		board.addToSpace(4,4, rook);
	}
	
	// Remove the piece we added
	@After
	public void removeRook(){
		board.removeFromSpace(rook.getRow(),rook.getCol(),false);
	}
	
	// Test rook to E6
	@Test
	public void testRookMoveUp(){
		assertTrue(rook.move(board,5,4));
	}
	
	// Test rook to E4
	@Test
	public void testRookMoveDown(){
		assertTrue(rook.move(board,3,4));
	}
	
	// Test rook to F5
	@Test
	public void testRookMoveRight(){
		assertTrue(rook.move(board,4,5));
	}

	// Test rook to D5
	@Test
	public void testRookMoveLeft(){
		assertTrue(rook.move(board,4,3));
	}
	
	// Test rook to E7, capturing pawn
	@Test
	public void testRookCapture(){
		assertTrue(rook.move(board,6,4));
	}
	
	// Test rook to E8, failing because pawn in the way
	@Test
	public void testRookEnemyCollision(){
		assertFalse(rook.move(board,7,4));
	}
	
	// Test rook to E2, failing because ally pawn in the way
	@Test
	public void testRookAllyCollision(){
		assertFalse(rook.move(board,1,4));
	}
}