package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class QueenTest{
	@Test
	public void testQueenMoveUp() {
		assertTrue(Chess.queen.move(5,4));
	}
	
	@Test
	public void testQueenMoveDown() {
		assertTrue(Chess.queen.move(3,4));
	}
	
	@Test
	public void testQueenMoveRight() {
		assertTrue(Chess.queen.move(4,5));
	}
	
	@Test
	public void testQueenMoveLeft() {
		assertTrue(Chess.queen.move(4,3));
	}
	
	@Test
	public void testQueenMoveUpAndRight() {
		assertTrue(Chess.queen.move(5,5));
	}	
	
	@Test
	public void testQueenMoveUpAndLeft() {
		assertTrue(Chess.queen.move(5,3));
	}		
	
	@Test
	public void testQueenMoveDownAndRight() {
		assertTrue(Chess.queen.move(3,5));
	}	
	
	@Test
	public void testQueenMoveDownAndLeft() {
		assertTrue(Chess.queen.move(3,3));
	}
}