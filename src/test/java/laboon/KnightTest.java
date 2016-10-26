package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;	

public class KnightTest{
	@Test
	public void testKnightMoveUpAndRight() {
		assertTrue(Chess.knight.move(6,5));
	}
	
	@Test
	public void testKnightMoveUpAndLeft() {
		assertTrue(Chess.knight.move(6,3));
	}	
	
	@Test
	public void testKnightMoveDownAndLeft() {
		assertTrue(Chess.knight.move(2,3));
	}
	
	@Test
	public void testKnightMoveDownAndRight() {
		assertTrue(Chess.knight.move(2,5));
	}
	
	@Test
	public void testKnightMoveLeftAndUp() {
		assertTrue(Chess.knight.move(5,2));
	}	
	
	@Test
	public void testKnightMoveLeftAndDown() {
		assertTrue(Chess.knight.move(3,2));
	}	
	
	@Test
	public void testKnightMoveRightAndUp() {
		assertTrue(Chess.knight.move(5,6));
	}
	
	@Test
	public void testKnightMoveRightAndDown() {
		assertTrue(Chess.knight.move(3,6));
	}
}