package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

// Tests the Bishop piece	
public class BishopTest{
	private static Bishop bishop;
	private static Board board;
	@BeforeClass
	public static void createBoard(){
		board = new Board();
	}
	// Add a bishop to E4
	@Before
	public void addBishop(){
		bishop = new Bishop(true,4,4);
		board.addToSpace(4,4, bishop);
	}
	
	// Remove the piece we added
	@After
	public void removeBishop(){
		board.removeFromSpace(bishop.getRow(),bishop.getCol(),false);
	}
	
	// Test the bishop moving to F5
	@Test
	public void testBishopMoveUpAndRight() {
		assertTrue(bishop.move(board,3,5));
	}
	
	// Test the bishop moving to D5
	@Test
	public void testBishopMoveUpAndLeft(){
		assertTrue(bishop.move(board,3,3));
	}
	
	// Test the bishop moving to F3
	@Test
	public void testBishopMoveDownAndRight(){
		assertTrue(bishop.move(board,5,5));
	}
	
	// Test the bishop moving to D3
	@Test
	public void testBishopMoveDownAndLeft(){
		assertTrue(bishop.move(board,5,3));
	}
	
	// Test bishop move to C2, should be false since ally pawn is there
	@Test
	public void testBishopMoveToAlly() {
		assertFalse(bishop.move(board,6,2));
	}
	
	// Test bishop move to A8, should be false since enemy pawn is blocking the path
	@Test
	public void testBishopMovePastEnemy() {
		assertFalse(bishop.move(board,0,0));
	}
	
	// Test bishop move to H7, should be true because enemy pawn is capturable
	public void testBishopCaptureEnemy() {
		assertTrue(bishop.move(board,1,7));
	}
}
