package laboon;

import java.io.*;
import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

import laboon.Stockfish;

public class StockfishTest {

    // Stockfish should start without throwing any exceptions
    @Test
    public void testInitialize() throws IOException {
        Stockfish sf = new Stockfish();
    }

    // Stockfish doesn't throw an exception when doing a simple write
    @Test
    public void testWrite() throws IOException {
        Stockfish sf = new Stockfish();
        sf.write("Testing Testing 123");
    }

    // Stockfish successfully reads the startup message
    @Test
    public void testRead() throws IOException {
        Stockfish sf = new Stockfish();
        String observed = sf.read();
        String expected = "Stockfish 7 64 by T. Romstad, M. Costalba, J. Kiiski, G. Linscott\n";
        assertEquals(observed, expected);
    }

    //  Stockfish should respond with the default board when sent the command "d"
    @Test
    public void testDisplayBoard() throws IOException {
        String expected =
        "\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " | r | n | b | q | k | b | n | r |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " | p | p | p | p | p | p | p | p |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " |   |   |   |   |   |   |   |   |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " |   |   |   |   |   |   |   |   |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " |   |   |   |   |   |   |   |   |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " |   |   |   |   |   |   |   |   |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " | P | P | P | P | P | P | P | P |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        " | R | N | B | Q | K | B | N | R |\n" +
        " +---+---+---+---+---+---+---+---+\n" +
        "\n" +
        "Fen: rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1\n" +
        "Key: B4D30CD15A43432D\n" +
        "Checkers: \n";

        Stockfish sf = new Stockfish();
        sf.readLine();  // Discard startup message
        sf.write("position fen rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
        sf.write("d");
        String observed = sf.read();
        assertTrue(expected.equals(observed));
    }
}