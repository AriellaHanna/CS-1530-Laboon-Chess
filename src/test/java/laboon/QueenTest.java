package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import static org.junit.Assert.*;	


public class QueenTest{
	private static Queen queen;
	private static Board board;
	
	@BeforeClass
	public static void createBoard(){
		board = new Board();
	}
	// Add a Queen to E5
	@Before
	public void addQueen(){
		queen = new Queen(true,4,4);
		board.addToSpace(4,4, queen);
	}
	
	// Remove the queen from the board
	@After
	public void removeQueen(){
		board.removeFromSpace(queen.getRow(),queen.getCol(),false);
	}
	
	// Test queen to E6
	@Test
	public void testQueenMoveUp() {
		assertTrue(queen.move(board,5,4));
	}
	
	// Test queen to E4
	@Test
	public void testQueenMoveDown() {
		assertTrue(queen.move(board,3,4));
	}
	
	// Test queen to F5
	@Test
	public void testQueenMoveRight() {
		assertTrue(queen.move(board,4,5));
	}
	
	// Test queen to D5
	@Test
	public void testQueenMoveLeft() {
		assertTrue(queen.move(board,4,3));
	}
	
	// Test queen to F6
	@Test
	public void testQueenMoveUpAndRight() {
		assertTrue(queen.move(board, 5,5));
	}	
	
	// Test queen to D6
	@Test
	public void testQueenMoveUpAndLeft() {
		assertTrue(queen.move(board,5,3));
	}		
	
	// Test queen to F4
	@Test
	public void testQueenMoveDownAndRight() {
		assertTrue(queen.move(board,3,5));
	}	
	
	// Test queen to D4
	@Test
	public void testQueenMoveDownAndLeft() {
		assertTrue(queen.move(board,3,3));
	}
	
	// Test queen to E7, capture pawn
	@Test
	public void testQueenCapture(){
		assertTrue(queen.move(board,6,4));
	}
	// Test queen to E8, fail because enemy pawn in the way
	@Test
	public void testQueenEnemyCollision(){
		assertFalse(queen.move(board,7,7));
	}
	
	// Test queen to E1, fail because ally in the way
	@Test
	public void testQueenAllyCollision(){
		assertFalse(queen.move(board,0,4));
	}
	
}