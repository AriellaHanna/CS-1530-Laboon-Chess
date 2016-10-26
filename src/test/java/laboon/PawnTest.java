package laboon;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class PawnTest{
	@Test
	public void testPawnMove() {
		if(Chess.pawn.isWhite())
			assertTrue(Chess.pawn.move(5,4));
		else
			assertTrue(Chess.pawn.move(3,4));
	}
}