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
		Board board = new Board();
		Bishop bishop = new Bishop(true,4,4);
		board.addToSpace(4,4, bishop);
		assertTrue(bishop.move(board,5,5));
	}
	
	@Test
	public void testBishopMoveUpAndLeft(){
		Board board = new Board();
		Bishop bishop = new Bishop(true,4,4);
		board.addToSpace(4,4, bishop);
		assertTrue(bishop.move(board,5,3));
	}
	
	@Test
	public void testBishopMoveDownAndRight(){
		Board board = new Board();
		Bishop bishop = new Bishop(true,4,4);
		board.addToSpace(4,4, bishop);
		assertTrue(bishop.move(board,3,5));
	}
	
	@Test
	public void testBishopMoveDownAndLeft(){
		Board board = new Board();
		Bishop bishop = new Bishop(true,4,4);
		board.addToSpace(4,4, bishop);
		assertTrue(bishop.move(board,3,3));
	}
	
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

	@Test
	public void testPawnMove() {
		if(Chess.pawn.isWhite())
			assertTrue(Chess.pawn.move(5,4));
		else
			assertTrue(Chess.pawn.move(3,4));
	}
	
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
