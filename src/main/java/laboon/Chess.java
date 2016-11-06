package laboon;

import java.util.*;
import java.text.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.undo.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Chess {
    private JFrame frame;   //global variables
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JLabel display, title;
    private JButton [][] theButtons;
    private JLabel [] numbers;
    private JLabel [] letters;
    private JButton newGame;
    private JButton loadGame;
    private JButton saveGame;
    private JButton flipBoard;
    private Control ButtonListener;
    private int game;
    private int t;
    private boolean win = false;
    private boolean test;
    private Board board;
    private boolean whiteOnBottom;

    protected static Bishop bishop = new Bishop(true,4,4);
    protected static King king = new King(true,4,4);
    protected static Queen queen = new Queen(true,4,4);
    protected static Knight knight = new Knight(true,4,4);
    protected static Pawn pawn = new Pawn(true,4,4);
    protected static Rook rook = new Rook(true,4,4);

    public static void main(String[] args) {
        new Chess(8);
    }

    public Chess(int size)
    {
        game = size;
        frame = new JFrame("Laboon Chess"); //creates the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        ButtonListener = new Control();

        //creates all positions for the board
        numbers = new JLabel[8];
        numbers[0] = new JLabel("8", JLabel.CENTER);
        numbers[1] = new JLabel("7", JLabel.CENTER);
        numbers[2] = new JLabel("6", JLabel.CENTER);
        numbers[3] = new JLabel("5", JLabel.CENTER);
        numbers[4] = new JLabel("4", JLabel.CENTER);
        numbers[5] = new JLabel("3", JLabel.CENTER);
        numbers[6] = new JLabel("2", JLabel.CENTER);
        numbers[7] = new JLabel("1", JLabel.CENTER);
        letters = new JLabel[9];
        letters[0] = new JLabel("", JLabel.CENTER);
        letters[1] = new JLabel("A", JLabel.CENTER);
        letters[2] = new JLabel("B", JLabel.CENTER);
        letters[3] = new JLabel("C", JLabel.CENTER);
        letters[4] = new JLabel("D", JLabel.CENTER);
        letters[5] = new JLabel("E", JLabel.CENTER);
        letters[6] = new JLabel("F", JLabel.CENTER);
        letters[7] = new JLabel("G", JLabel.CENTER);
        letters[8] = new JLabel("H", JLabel.CENTER);
        theButtons = new JButton[game][game];   //creates the game buttons
        newGame = new JButton("New Game");  //creates a new game button
        loadGame = new JButton("Load Game"); //loads an existing game
        saveGame = new JButton("Save Game"); //saves current game
        flipBoard = new JButton("Flip Board"); //Flips the chess board
        title = new JLabel("", JLabel.CENTER);
        display = new JLabel(); //creates a JLabel that will be altered throughout the game telling the user who is up
        title.setText("Laboon Chess");
        display.setText("White is up first!");
        upperPanel = new JPanel();
        middlePanel = new JPanel(); //creates a JPanel
        lowerPanel = new JPanel();  //creates another JPanel

        upperPanel.setLayout(new GridLayout());
        upperPanel.add(title);

        middlePanel.setLayout(new GridLayout(0, 9));    //Sets the way the components that will be added will be displayed
        lowerPanel.setLayout(new FlowLayout(game)); //See comment in the line above
        middlePanel.add(numbers[0]);

        board = new Board();

        whiteOnBottom = true;

        int i = 0;
        int a = 1;

        while(a < 9)
        {
            for (int j = 0; j < theButtons[i].length; j++)
            {
                Piece p = board.getSpace(i,j).getPiece();
                theButtons[i][j] = new JButton(p == null ? " " : p.getSymbol());
                theButtons[i][j].addActionListener(ButtonListener); //this line calls the ActionLister so that the buttons will function when clicked

                        if (i == 0 || i == 1)
                        {
                            theButtons[i][j].setForeground(Color.BLACK);
                        }

                        else if (i == 6 || i == 7)
                        {
                            theButtons[i][j].setForeground(Color.WHITE);
                        }

                if (i%2 == 0 && j%2 != 0)
                {
                    theButtons[i][j].setBackground(Color.GRAY);
                }

                else if (i%2 != 0 && j%2 == 0)
                {
                    theButtons[i][j].setBackground(Color.GRAY);
                }

                else if (i%2 == 0 && j%2 == 0)
                {
                    theButtons[i][j].setBackground(Color.ORANGE);
                }

                else if(i%2 == 1 && j%2 == 1)
                {
                    theButtons[i][j].setBackground(Color.ORANGE);
                }

                middlePanel.add(theButtons[i][j]);
            }

            if(a != 8)
            {
                middlePanel.add(numbers[a]);
            }

            a++;
            i++;
        }

        middlePanel.add(letters[0]);
        middlePanel.add(letters[1]);
        middlePanel.add(letters[2]);
        middlePanel.add(letters[3]);
        middlePanel.add(letters[4]);
        middlePanel.add(letters[5]);
        middlePanel.add(letters[6]);
        middlePanel.add(letters[7]);
        middlePanel.add(letters[8]);

        newGame.addActionListener(ButtonListener);  //this line calls the ActionLister so that the buttons will function when clicked
        loadGame.addActionListener(ButtonListener); //this line calls the ActionLister so that the buttons will function when clicked
        saveGame.addActionListener(ButtonListener); //this line calls the ActionLister so that the buttons will function when clicked
        flipBoard.addActionListener(ButtonListener); //this line calls the ActionLister so that the buttons will function when clicked
        lowerPanel.add(newGame);    //adds new game button to the panel
        lowerPanel.add(loadGame); //adds load game button to the panel
        lowerPanel.add(saveGame); //adds save game button to the panel
        lowerPanel.add(flipBoard); //adds flip board button to the panel
        lowerPanel.add(display);    //adds the JLabel to the panel
        frame.add(upperPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);    //adds the one JLabel to the JFrame and puts it in the center of the GUI
        frame.add(lowerPanel, BorderLayout.SOUTH);  //adds the other JLabel to the JFrame and puts it in the southern part of the GUI

        frame.setVisible(true);
    }

    private class Control implements ActionListener
    {
        public void actionPerformed(ActionEvent e)  //this creates the different actions that will take place when certain buttons are clicked
        {
            JFileChooser fc = new JFileChooser();

            if(e.getSource() == newGame)    //when new game is clicked
            {
                Object[] playerOptions = {"Player 1", "Player 2"};
                Object[] playerOneColorOptions = {"White", "Blue", "Red", "Green"};
                Object[] playerTwoColorOptions = {"Black", "Blue", "Red", "Green"};
                Color[] playerOneColors = {Color.WHITE, Color.BLUE, Color.RED, Color.GREEN};
                Color[] playerTwoColors = {Color.BLACK, Color.BLUE, Color.RED, Color.GREEN};
                Object[] colorOptions = {};
                Color bottomColor = Color.WHITE;
                Color topColor = Color.BLACK;

                int newWindow = JOptionPane.showOptionDialog(frame,
                    "Choose your player:",
                    "Select a player",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    playerOptions,
                    playerOptions[1]);

                if (newWindow == 0) {  // selected player 1
                    colorOptions = playerOneColorOptions;
                } else if (newWindow == 1) {  // selected player 2
                    colorOptions = playerTwoColorOptions;
                }

                int chooseColorWindow = JOptionPane.showOptionDialog(frame,
                    "Choose your color:",
                    "Select a color",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    colorOptions,
                    colorOptions[3]);

                if (newWindow == 0) {  // player 1 is on the bottom
                    bottomColor = playerOneColors[chooseColorWindow];
                } else if (newWindow == 1) {  // player 2 is on the top
                    topColor = playerTwoColors[chooseColorWindow];
                }

                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 8; j++) {
                        theButtons[i][j].setForeground(topColor);
                        theButtons[i+6][j].setForeground(bottomColor);
                    }
                }

                for(int i=0; i<game; i++)   //these for loops reset the game buttons to empty strings and allow them to be clicked again
                {
                    for(int j=0; j<game; j++)
                    {
                        Piece p = board.getSpace(i,j).getPiece();
                        theButtons[i][j].setText(p == null ? " " : p.getSymbol());
                        theButtons[i][j].setEnabled(true);
                    }
                }

                display.setText("White is up first!");  //tells the user who is up first
            }

            else if(e.getSource() == loadGame) //when load game is clicked
            {
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    loadPGN(file);
                }
            }

            else if(e.getSource() == saveGame) //when save game is clicked
            {
                if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    File file = fc.getSelectedFile();
                    savePGN(file);
                }
            }

            else if(e.getSource() == flipBoard) //when flip board is clicked
            {

                // remove buttons, letters, and numbers
                for (int i = 0; i < 8; i++)
                {
                    for (int j = 0; j < 8; j++)
                    {
                        middlePanel.remove(theButtons[i][j]);
                        middlePanel.revalidate();
                        middlePanel.repaint();
                    }

                    middlePanel.remove(letters[i]);
                    middlePanel.remove(numbers[i]);
                }

                middlePanel.remove(letters[8]);  // don't forget corner piece

                if (whiteOnBottom == true)
                {
                    for (int i = 8; i >= 0; i--)
                    {
                        middlePanel.add(letters[i]);
                    }

                    for(int i = 7; i >= 0; i--)
                    {
                        for(int j = 7; j >= 0; j--)
                        {
                            middlePanel.add(theButtons[i][j]);
                        }

                        middlePanel.add(numbers[i]);
                    }
                }

                else
                {


                    for(int i = 0; i < 8; i++)
                    {
                        middlePanel.add(numbers[i]);

                        for(int j = 0; j < 8; j++)
                        {
                            middlePanel.add(theButtons[i][j]);
                        }
                    }

                    for (int i = 0; i <= 8; i++)
                    {
                       middlePanel.add(letters[i]);
                    }
                }

                whiteOnBottom = !whiteOnBottom;
            }
        }
        // loads and parses the PGN file
        public void loadPGN(File file){
            try{
                Scanner inFile = new Scanner(file);

                //pattern used to find chess piece movements in PGN file
                String pattern  = "((O-O)|(O-O-O)|[abcdefghNxBQORK]+[12345678])+[\\+]?(\\s)([abcdefghNxBQORK]+[12345678])*(O-O)?(O-O-O)?[\\+]?";
                Pattern p = Pattern.compile(pattern);

                //Searches the file for pattern
                while(inFile.hasNext()){
                    String line = inFile.nextLine();
                    Matcher m = p.matcher(line);
                    while(m.find()){
                        System.out.println("Found value: " + m.group());
                    }
                }
                inFile.close();

            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file");
            }
        }
        //Writes a PGN file
        public void savePGN(File file){
            try{
                PrintWriter writer = new PrintWriter(file);
                String event = "[Event: \"Local Game\"]";
                String site = "[Site: \"local\"]";
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String d = "[Date \""+dateFormat.format(date)+"\"]";
                String white = "[White \"Player\"]";
                String black = "[Black \"Computer\"]";

                //writes the header of the PGN file
                writer.println(event+"\n"+site+"\n"+d+"\n"+white+"\n"+black);
                writer.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file");
            }

        }

    }
}