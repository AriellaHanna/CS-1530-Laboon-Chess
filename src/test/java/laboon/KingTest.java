package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import static org.junit.Assert.*;	


public class KingTest{
	private static King king;
	private static Board board;
	
	@BeforeClass
	public static void createBoard(){
		board = new Board();
	}
	// Add a King to E5
	@Before
	public void addKing(){
		king = new King(true,4,4);
		board.addToSpace(4,4, king);
	}
	
	// Remove the piece we added
	@After
	public void removeKing(){
		board.removeFromSpace(king.getRow(),king.getCol(),false);
	}
	
	// Test king to E6
	@Test
	public void testKingMoveUp() {
		assertTrue(king.move(board, 5, 4));
	}
	
	// Test king to E4
	@Test
	public void testKingMoveDown() {
		assertTrue(king.move(board,3,4));
	}
	
	// Test king to F5
	@Test
	public void testKingMoveRight() {
		assertTrue(king.move(board,4,5));
	}
	
	// Test king to D5
	@Test
	public void testKingMoveLeft() {
		assertTrue(king.move(board,4,3));
	}
	
	// Test king to F6
	@Test
	public void testKingMoveUpAndRight() {
		assertTrue(king.move(board,5,5));
	}	
	
	// Test king to D6
	@Test
	public void testKingMoveUpAndLeft() {
		assertTrue(king.move(board,5,3));
	}		
	
	// Test king to F4
	@Test
	public void testKingMoveDownAndRight() {
		assertTrue(king.move(board,3,5));
	}	
	
	// Test king to D4
	@Test
	public void testKingMoveDownAndLeft() {
		assertTrue(king.move(board,3,3));
	}
	
	// Test king taking pawn at G7
	@Test
	public void testKingCapture(){
		king.move(board,5,5);
		assertTrue(king.move(board,6,6));
	}
	
	// Test king trying to share space with pawn at A2
	@Test
	public void testKingCollision(){
		king = new King(true,2,0);
		board.addToSpace(2,0, king);
		assertFalse(king.move(board,1,0));
	}	
}