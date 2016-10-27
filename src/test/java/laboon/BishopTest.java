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
	// Add a bishop to E5
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
	
	// Test the bishop moving to F6
	@Test
	public void testBishopMoveUpAndRight() {
		assertTrue(bishop.move(board,5,5));
	}
	
	// Test the bishop moving to D6
	@Test
	public void testBishopMoveUpAndLeft(){
		assertTrue(bishop.move(board,5,3));
	}
	
	// Test the bishop moving to F4
	@Test
	public void testBishopMoveDownAndRight(){
		assertTrue(bishop.move(board,3,5));
	}
	
	// Test the bishop moving to D4
	@Test
	public void testBishopMoveDownAndLeft(){
		assertTrue(bishop.move(board,3,3));
	}
	
	// Test bishop move to B2, should be false since ally pawn is there
	@Test
	public void testBishopMoveToAlly() {
		assertFalse(bishop.move(board,1,1));
	}
	
	// Test bishop move to H8, should be false since enemy pawn is blocking the path
	@Test
	public void testBishopMovePastEnemy() {
		assertFalse(bishop.move(board,7,7));
	}
	
	// Test bishop move to G7, should be true because enemy pawn is capturable
	public void testBishopCaptureEnemy() {
		assertTrue(bishop.move(board,6,6));
	}
}
