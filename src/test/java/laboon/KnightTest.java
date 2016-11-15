package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import static org.junit.Assert.*;	


public class KnightTest{
	private static Knight knight;
	private static Board board;
	
	@BeforeClass
	public static void createBoard(){
		board = new Board();
	}
	// Add a Knight to E4
	@Before
	public void addKnight(){
		knight = new Knight(true,3,4);
		board.addToSpace(3,4, knight);
	}
	
	@After
	public void removeKnight(){
		board.removeFromSpace(knight.getRow(),knight.getCol(),false);
	}
	
	// Test knight to F6
	@Test
	public void testKnightMoveUpAndRight() {
		assertTrue(knight.move(board,1,5));
	}
	
	// Test knight to D6
	@Test
	public void testKnightMoveUpAndLeft() {
		assertTrue(knight.move(board,1,3));
	}	
	
	// Test knight from B6 to A4
	@Test
	public void testKnightMoveDownAndLeft() {
		assertTrue(knight.move(board,5,3));
	}
	
	// Test knight from B6 to C4
	@Test
	public void testKnightMoveDownAndRight() {
		assertTrue(knight.move(board,5,5));
	}
	
	// Test knight to C5
	@Test
	public void testKnightMoveLeftAndUp() {
		assertTrue(knight.move(board,2,2));
	}	
	
	// Test knight to C3
	@Test
	public void testKnightMoveLeftAndDown() {
		assertTrue(knight.move(board,4,2));
	}	
	
	// Test knight to G5
	@Test
	public void testKnightMoveRightAndUp() {
		assertTrue(knight.move(board,2,6));
	}
	
	// Test knight to G3
	@Test
	public void testKnightMoveRightAndDown() {
		assertTrue(knight.move(board,4,6));
	}
	
	// Test knight to D2, fails because ally pawn is there
	@Test
	public void testKnightCollision() {
		knight = new Knight(true,5,0);
		board.addToSpace(5,0,knight);
		assertFalse(knight.move(board,7,1));
	}
}