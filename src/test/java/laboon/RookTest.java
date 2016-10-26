package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
	

public class RookTest{
	@Test
	public void testRookMoveUp(){
		assertTrue(Chess.rook.move(5,4));
	}
	
	@Test
	public void testRookMoveDown(){
		assertTrue(Chess.rook.move(3,4));
	}
	
	@Test
	public void testRookMoveRight(){
		assertTrue(Chess.rook.move(4,5));
	}

	@Test
	public void testRookMoveLeft(){
		assertTrue(Chess.rook.move(4,3));
	}
	
}