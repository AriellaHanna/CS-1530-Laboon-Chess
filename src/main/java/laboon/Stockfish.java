package laboon;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.SystemUtils;

public class Stockfish {

    private Process stockfish;
    private BufferedReader reader;
    private OutputStreamWriter writer;

    public Stockfish() throws IOException {
        ProcessBuilder processBuilder= new ProcessBuilder();
        String pwd = System.getProperty("user.dir");

        // Determine appropriate binary for the user's OS
        // Supports Windows, Mac OS X, and Linux
        String binary = "";
        if (SystemUtils.IS_OS_WINDOWS) {
            binary = "stockfish-win-64.exe";
        } else if (SystemUtils.IS_OS_MAC) {
            binary = "stockfish-osx-64";
        } else if (SystemUtils.IS_OS_LINUX) {
            binary = "stockfish-linux-64";
        } else {
            throw new RuntimeException("No compatabible binaries found in " + pwd);
        }


        Path path = Paths.get(pwd, "bin", binary);
        processBuilder.command(path.toString());

        // starts Stockfish and sets references to IO streams
        stockfish = processBuilder.start();
        reader = new BufferedReader(new InputStreamReader(stockfish.getInputStream()));
        writer = new OutputStreamWriter(stockfish.getOutputStream());
    }

    // Send a command directly to Stockfish
    public void write(String command) throws IOException {
        writer.write(command + "\n");  // \n appended as per the UCI specification
        writer.flush();
    }

    // Retrieves lines from Stockfish
    public String read() throws IOException {
        String line = "";
        StringBuilder sb = new StringBuilder();

        // As per UCI, the engine indicates it is ready to receive new commands
        // when it responds to "isready" with "readyok".
        write("isready");
        while (!line.equals("readyok\n")) {
            sb.append(line);
            line = readLine();
        }

        return sb.toString();
    }

    // Retrieves a single line from Stockfish
    public String readLine() throws IOException {
        return reader.readLine() + "\n";
    }

}