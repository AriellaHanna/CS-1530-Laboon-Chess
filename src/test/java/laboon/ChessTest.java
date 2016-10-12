package laboon;

import org.junit.Test;
import static org.junit.Assert.*;

import laboon.Chess;

public class ChessTest {

    @Test
    public void testPoodle() {
        assertEquals(Chess.poodle(), "poodle");
    }
	
	@Test
	public void testBishopMoveUpAndRight() {
		assertTrue(Chess.bishop.move(1,1));
	}
	
	@Test
	public void testBishopMoveUpAndLeft(){
		assertTrue(Chess.bishop.move(-1,1));
	}
	
	@Test
	public void testBishopMoveDownAndRight(){
		assertTrue(Chess.bishop.move(1,-1));
	}
	
	@Test
	public void testBishopMoveDownAndLeft(){
		assertTrue(Chess.bishop.move(-1,-1));
	}
	
	@Test
	public void testKingMoveUp() {
		assertTrue(Chess.king.move(0,1));
	}
	
	@Test
	public void testKingMoveDown() {
		assertTrue(Chess.king.move(0,-1));
	}
	
	@Test
	public void testKingMoveRight() {
		assertTrue(Chess.king.move(1,0));
	}
	
	@Test
	public void testKingMoveLeft() {
		assertTrue(Chess.king.move(-1,0));
	}
	
	@Test
	public void testKingMoveUpAndRight() {
		assertTrue(Chess.king.move(1,1));
	}	
	
	@Test
	public void testKingMoveUpAndLeft() {
		assertTrue(Chess.king.move(-1,1));
	}		
	
	@Test
	public void testKingMoveDownAndRight() {
		assertTrue(Chess.king.move(1,-1));
	}	
	
	@Test
	public void testKingMoveDownAndLeft() {
		assertTrue(Chess.king.move(-1,-1));
	}	
	
	@Test
	public void testKnightMoveUpAndRight() {
		assertTrue(Chess.knight.move(1,2));
	}
	
	@Test
	public void testKnightMoveUpAndLeft() {
		assertTrue(Chess.knight.move(-1,2));
	}	
	
	@Test
	public void testKnightMoveDownAndLeft() {
		assertTrue(Chess.knight.move(-1,-2));
	}
	
	@Test
	public void testKnightMoveDownAndRight() {
		assertTrue(Chess.knight.move(1,-2));
	}
	
	@Test
	public void testKnightMoveLeftAndUp() {
		assertTrue(Chess.knight.move(-2,1));
	}	
	
	@Test
	public void testKnightMoveLeftAndDown() {
		assertTrue(Chess.knight.move(-2,-1));
	}	
	
	@Test
	public void testKnightMoveRightAndUp() {
		assertTrue(Chess.knight.move(2,1));
	}
	
	@Test
	public void testKnightMoveRightAndDown() {
		assertTrue(Chess.knight.move(2,-1));
	}
	
	@Test
	public void testQueenMoveUp() {
		assertTrue(Chess.queen.move(0,1));
	}
	
	@Test
	public void testQueenMoveDown() {
		assertTrue(Chess.queen.move(0,-1));
	}
	
	@Test
	public void testQueenMoveRight() {
		assertTrue(Chess.queen.move(1,0));
	}
	
	@Test
	public void testQueenMoveLeft() {
		assertTrue(Chess.queen.move(-1,0));
	}
	
	@Test
	public void testQueenMoveUpAndRight() {
		assertTrue(Chess.queen.move(1,1));
	}	
	
	@Test
	public void testQueenMoveUpAndLeft() {
		assertTrue(Chess.queen.move(-1,1));
	}		
	
	@Test
	public void testQueenMoveDownAndRight() {
		assertTrue(Chess.queen.move(1,-1));
	}	
	
	@Test
	public void testQueenMoveDownAndLeft() {
		assertTrue(Chess.queen.move(-1,-1));
	}

	@Test
	public void testPawnMove() {
		if(Chess.pawn.isWhite())
			assertTrue(Chess.pawn.move(0,1));
		else
			assertTrue(Chess.pawn.move(0,-1));
	}
	
	@Test
	public void testRookMoveUp(){
		assertTrue(Chess.rook.move(0,1));
	}
	
	@Test
	public void testRookMoveDown(){
		assertTrue(Chess.rook.move(0,-1));
	}
	
	@Test
	public void testRookMoveRight(){
		assertTrue(Chess.rook.move(1,0));
	}

	@Test
	public void testRookMoveLeft(){
		assertTrue(Chess.rook.move(0,-1));
	}
	
}
