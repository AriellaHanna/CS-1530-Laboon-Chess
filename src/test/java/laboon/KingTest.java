package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;	


public class KingTest{
	
	@Test
	public void testKingMoveUp() {
		assertTrue(Chess.king.move(5,4));
	}
	
	@Test
	public void testKingMoveDown() {
		assertTrue(Chess.king.move(3,4));
	}
	
	@Test
	public void testKingMoveRight() {
		assertTrue(Chess.king.move(4,5));
	}
	
	@Test
	public void testKingMoveLeft() {
		assertTrue(Chess.king.move(4,3));
	}
	
	@Test
	public void testKingMoveUpAndRight() {
		assertTrue(Chess.king.move(5,5));
	}	
	
	@Test
	public void testKingMoveUpAndLeft() {
		assertTrue(Chess.king.move(5,3));
	}		
	
	@Test
	public void testKingMoveDownAndRight() {
		assertTrue(Chess.king.move(3,5));
	}	
	
	@Test
	public void testKingMoveDownAndLeft() {
		assertTrue(Chess.king.move(3,3));
	}	
}