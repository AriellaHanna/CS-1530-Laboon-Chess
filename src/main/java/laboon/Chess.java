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
    private Color blacksColor = Color.BLACK;
	private String blacksColorName = "Black";
    private Color whitesColor = Color.WHITE;
	private String whitesColorName = "White";
	private boolean playerTurn = true;
	private int turnNumber = 0;
	private String move;
	private String[] player1Moves = new String[20];
	private String[] player2Moves = new String[20];;

    

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
        display.setText("It is " + whitesColorName + "'s turn first");  //tells the user who is up first
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
        boolean pieceSelected = false;
        int r1, r2, c1, c2;

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
                whitesColor = Color.WHITE;
                blacksColor = Color.BLACK;
                board = new Board();

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
                    whitesColor = playerOneColors[chooseColorWindow];
					
					//sets the whitesColorName and blacksColorName String for displaying whose turn and alert
					if(whitesColor == Color.WHITE) whitesColorName = "White";
					else if(whitesColor == Color.BLUE) whitesColorName = "Blue";
					else if(whitesColor == Color.RED) whitesColorName = "Red";
					else whitesColorName = "Green";
					blacksColorName = "Black";
                } else if (newWindow == 1) {  // player 2 is on the top
                    blacksColor = playerTwoColors[chooseColorWindow];
					
					//sets the whitesColorName and blacksColorName String for displaying whose turn and alert
					if(blacksColor == Color.WHITE) blacksColorName = "Black";
					else if(blacksColor == Color.BLUE) blacksColorName = "Blue";
					else if(blacksColor == Color.RED) blacksColorName = "Red";
					else blacksColorName = "Green";
					whitesColorName = "White";
                }

                redrawBoard();
				playerTurn = true;
				display.setText("It is " + whitesColorName + "'s Turn");  //tells the user who is up first
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

            else {
                for (int i = 0; i < 8; i++)
                {
                    for (int j = 0; j < 8; j++)
                    {
                        if (e.getSource() == theButtons[i][j])
                        {
                            System.out.printf("(%d, %d)%n", i, j);
                            if (!pieceSelected)
                            {
                                r1 = i;
                                c1 = j;
                                pieceSelected = true;
                            }
                            else {
                                r2 = i;
                                c2 = j;
                                System.out.println(makeMove(r1, c1, r2, c2));
                                redrawBoard();
                                pieceSelected = false;
                            }
                        }
                    }
                }
            }
        }

        // Reset the game buttons to empty strings and allow them to be clicked again
        public void redrawBoard() {
            for(int i=0; i<game; i++)
            {
                for(int j=0; j<game; j++)
                {
                    Piece p = board.getSpacePiece(i, j);
                    theButtons[i][j].setText(p == null ? " " : p.getSymbol());
                    theButtons[i][j].setForeground(p != null && p.isWhite() ? whitesColor : blacksColor);
                    theButtons[i][j].setEnabled(true);
                }
            }
			//displays whose turn it is
			if(playerTurn) display.setText("It is " + whitesColorName + "'s Turn"); 
			else display.setText("It is " + blacksColorName + "'s Turn");
        }


        // Attempts to move the piece at (r1, c1) to (r2, c2).
        // Returns true if successful, otherwise returns false.
        public boolean makeMove(int r1, int c1, int r2, int c2) {
            // TODO: make sure the piece belongs to the player
            Piece p = board.getSpacePiece(r1, c1);
			if (p == null) return false;  // if a piece was clicked on
			//checks whose turn it is
			if((playerTurn && p.isWhite()) || (!playerTurn && !p.isWhite())){
				if (p.move(board, r2, c2)) {   // if the move is valid
					addMoveToList(p, p.getCol(), p.getRow());
					playerTurn = !playerTurn;					
					return true;
				}
			}
			else{
				//if not the user's turn. Alert user as such
				printTurnAlert();
			}
            return false;
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
        // Writes a PGN file
        public void savePGN(File file){
            try{
                PrintWriter writer = new PrintWriter(file);
                String event = "[Event: \"Local Game\"]";
                String site = "[Site: \"local\"]";
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                String d = "[Date \""+dateFormat.format(date)+"\"]";
                String white = "[" + whitesColorName + " \"Player\"]";
                String black = "[" + blacksColorName + " \"Computer\"]";
				String result = "[Result]";

                //writes the header of the PGN file
                writer.println(event+"\n"+site+"\n"+d+"\n"+white+"\n"+black+"\n"+result+"\n\n");
				for(int i = 0; i < turnNumber; i++){
					if(player2Moves[i] != null){
						writer.print((i+1)+"."+player1Moves[i]+" "+player2Moves[i]+" ");
					}
					else{
						writer.print((i+1)+"."+player1Moves[i]+" ");						
					}
				}
                writer.close();
            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file");
            }

        }		
		// prints out alert message if it is not the player's turn	
		public void printTurnAlert(){
			String alertMessage;
			if(playerTurn){
				alertMessage = "It is " + whitesColorName + "'s turn.";
			}
			else{
				alertMessage = "It is " + blacksColorName + "'s turn.";
			}
			JOptionPane.showMessageDialog(frame, alertMessage, "Warning", JOptionPane.WARNING_MESSAGE);
		}		
		
		/*takes the piece being moved and the destination of move. Then composes
		the a string of the move based on PGN notation
		params:
			Piece p:	piece being moved
			int c2:		destination column
			int r2:		destination row*/
		public void addMoveToList(Piece p, int c2, int r2){
			//Composes move based on PGN notation
			if(p.getSymbol().equals("Kn")) move = "N";
			else if(p.getSymbol().equals("P")) move = "";
			else move = p.getSymbol();
			move = move + letters[c2].getText() + numbers[r2].getText();
			if(playerTurn){
				turnNumber++;
				player1Moves[turnNumber-1] = move;
			}
			else{
				player2Moves[turnNumber-1] = move;
			}
		}
    }
}