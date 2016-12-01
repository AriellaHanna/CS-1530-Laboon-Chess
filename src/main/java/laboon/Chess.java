package laboon;

import java.util.*;
import java.util.Timer;
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
    private JLabel display, title, kibitzer, space;
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
    private boolean aiEnabled = true;
    private boolean whiteOnBottom;
    private Color blacksColor = Color.BLACK;
	private String blacksColorName = "Black";
    private Color whitesColor = Color.WHITE;
	private String whitesColorName = "White";
	private boolean playerTurn = true;
	private int turnNumber = 0;
	private String move;
	private String[] player1Moves = new String[30];
	private String[] player2Moves = new String[30];
	private String[] numberText = new String[8];
	private String[] letterText = new String[9];
    private String moves = "";
    private Stockfish stockfish;


    public static void main(String[] args) {
        new Chess(8);
    }

    public Chess(int size)
    {
        try {
            stockfish = new Stockfish();
        } catch (IOException ioe) {
            System.err.println("Failed to start Stockfish!");
            aiEnabled = false;
            ioe.printStackTrace();
        }

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
		for(int i = 0; i < 8; i++){
			numberText[i] = Integer.toString(8-i);
		}
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
		letterText[0] = "";
		letterText[1] = "a";
		letterText[2] = "b";
		letterText[3] = "c";
		letterText[4] = "d";
		letterText[5] = "e";
		letterText[6] = "f";
		letterText[7] = "g";
		letterText[8] = "h";
        theButtons = new JButton[game][game];   //creates the game buttons
        newGame = new JButton("New Game");  //creates a new game button
        loadGame = new JButton("Load Game"); //loads an existing game
        saveGame = new JButton("Save Game"); //saves current game
        flipBoard = new JButton("Flip Board"); //Flips the chess board
        title = new JLabel("", JLabel.CENTER);
        display = new JLabel(); //creates a JLabel that will be altered throughout the game telling the user who is up
        kibitzer = new JLabel(); //creates a JLabel that will handle commentary
        space = new JLabel();
        space.setText("               ");
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

        board = new Board(numberText, letterText);

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
        lowerPanel.add(space);
        lowerPanel.add(kibitzer);
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
                board = new Board(numberText, letterText);

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
                kibitzer.setText("");
                
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
                                boolean validMove = makeMove(r1, c1, r2, c2);

                                if (validMove) {
                                    String algMove = toAlgebraicNotation(r1, c1, r2, c2);
                                    System.out.println(algMove);
                                    moves += " " + algMove;

                                    if (aiEnabled) {
                                        algMove = aiMove(stockfish, moves);
                                        moves += " " + algMove;
                                        int[] rcMove = toRowColNotation(algMove);
                                        makeMove(rcMove[0], rcMove[1], rcMove[2], rcMove[3]);
                                    }
                                }
								System.out.println(moves);
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
            boolean isSpaceEmpty = board.spaceIsEmpty(r2,c2);
            boolean spaceColor = false;
            if (!isSpaceEmpty)
            	spaceColor = board.getSpaceColor(r2,c2);

            String[] comments = new String[5];
            comments[0] = "Go white!";
            comments[1] = "This chess game has really good value!";
            comments[2] = "Go black!";
            comments[3] = "This game is taking forever...";
            comments[4] = "Who will be in check first?";

            Timer timer = new Timer();
            Random rand = new Random();
            Random rand2 = new Random();
            Thread t = new Thread(() ->
            {     
                int randTime = rand2.nextInt(6 - 1) + 1;       
                timer.schedule(new TimerTask() {
                    public void run() {
                        int randInt = rand.nextInt(6 - 1) + 1;
                        kibitzer.setText(comments[randInt-1]);
                    }
                }, randTime*1000, randTime*1000);
            });

            t.start();

            t.run();
            // TODO: make sure the piece belongs to the player
            Piece p = board.getSpacePiece(r1, c1);
			if (p == null) return false;  // if a piece was clicked on
			//checks whose turn it is
			if((playerTurn && p.isWhite()) || (!playerTurn && !p.isWhite())){
				if (p.move(board, r2, c2)) {   // if the move is valid
					boolean capture = !isSpaceEmpty && spaceColor != p.isWhite();
					System.out.println(capture);
					addMoveToList(p, c2, r2, capture, r1, c1);
					playerTurn = !playerTurn;
					return true;
				}
				else{
					printInvalidMoveMessage();
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
				aiEnabled = false;
				board = new Board(numberText, letterText);
				moves = "";
				turnNumber = 1;
				player1Moves = new String[30];
				player2Moves = new String[30];

                Scanner inFile = new Scanner(file);

				String event = inFile.nextLine(); // event name
				String site = inFile.nextLine(); // site
				String date = inFile.nextLine(); // date
				String round = inFile.nextLine(); // round
				String player1 = inFile.nextLine();	// player 1
				String player2 = inFile.nextLine(); // player 2
				loadPlayerColors(player1, player2);
				String result = inFile.nextLine(); // result

                //pattern used to find chess piece movements in PGN file
                String pattern  = "((O-O-O)|(O-O)|[abcdefghNxBQORK]+[12345678])+[\\+]?";
                Pattern p = Pattern.compile(pattern);
				ArrayList<String> moveList = new ArrayList<String>();

                //Searches the file for pattern
                while(inFile.hasNext()){
                    String line = inFile.nextLine();
                    Matcher m = p.matcher(line);
                    while(m.find()){
						moveList.add(m.group());
						System.out.println(m.group());
                    }
                }

				//goes through all moves found and loads the corresponding pieces
				for(int i = 0; i < moveList.size(); i++){
					if((i%2) == 0){
						loadsPieces(moveList, i, true);
					}
					else{
						loadsPieces(moveList, i, false);
					}
				}
                inFile.close();
				redrawBoard();
				System.out.println(moves);
				aiEnabled = true;
            }
            catch(FileNotFoundException ex) {
                System.out.println("Unable to open file");
            }
        }

		// loads the piece colors which were in the saved pgn file
		// param:
		// String player1 : a string containing the player 1's color
		// String player 2 : a string containing the player 2's color
		public void loadPlayerColors(String player1, String player2){
			String colorPattern = "((White)|(Blue)|(Red)|(Green))"; // regex for parsing the color
			Pattern pc = Pattern.compile(colorPattern);
			Matcher mc = pc.matcher(player1);
			//sets the color
			if(mc.find()){
				if(mc.group().equals("White")){
					whitesColor = Color.WHITE;
					whitesColorName = "White";
				}
				else if(mc.group().equals("Blue")){
					whitesColor = Color.BLUE;
					whitesColorName = "Blue";
				}
				else if(mc.group().equals("Red")){
					whitesColor = Color.RED;
					whitesColorName = "Red";
				}
				else if(mc.group().equals("Green")){
					whitesColor = Color.GREEN;
					whitesColorName = "Green";
				}
			}

			mc = pc.matcher(player2);
			//sets the color
			if(mc.find()){
				if(mc.group().equals("Black")){
					blacksColor = Color.BLACK;
					blacksColorName = "Black";
				}
				else if(mc.group().equals("Blue")){
					blacksColor = Color.BLUE;
					blacksColorName = "Blue";
				}
				else if(mc.group().equals("Red")){
					blacksColor = Color.RED;
					blacksColorName = "Red";
				}
				else if(mc.group().equals("Green")){
					blacksColor = Color.GREEN;
					blacksColorName = "Green";
				}
			}
		}

		/*
		* based on the first character of the move determine which piece is to be loaded
		* 	params:
		*	ArrayList<String> moveList: the list of moveList parsed from load file
		* 	int i: index of which move is being loaded
		* 	boolean white: which color is being loaded
		*/
		public void loadsPieces(ArrayList<String> moveList, int i, boolean white){
			if(moveList.get(i).charAt(0) == 'a' || moveList.get(i).charAt(0) == 'b' || moveList.get(i).charAt(0) == 'c' ||
				moveList.get(i).charAt(0) == 'd' || moveList.get(i).charAt(0) == 'e' || moveList.get(i).charAt(0) == 'f' ||
				moveList.get(i).charAt(0) == 'g' || moveList.get(i).charAt(0) == 'h'){
				loadPawn(moveList.get(i),white);
			}
			else if(moveList.get(i).charAt(0) == 'N'){
				loadKnight(moveList.get(i), white);
			}
			else if(moveList.get(i).charAt(0) == 'B'){
				loadBishop(moveList.get(i),white);
			}
			else if(moveList.get(i).charAt(0) == 'Q'){
				loadQueen(moveList.get(i),white);
			}
			else if(moveList.get(i).charAt(0) == 'R'){
				loadRook(moveList.get(i),white);
			}
			else if(moveList.get(i).equals("O-O")){
				loadKingsideCastling(white);
			}
			else if(moveList.get(i).equals("O-O-O")){
				loadQueensideCastling(white);
			}
			else if(moveList.get(i).charAt(0) == 'K'){
				loadKing(moveList.get(i),white);
			}

			if(playerTurn){
				player1Moves[turnNumber-1] = moveList.get(i);
			}
			else{
				player2Moves[turnNumber-1] = moveList.get(i);
				turnNumber++;
			}
			playerTurn = !playerTurn;

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
                String round = "[Round : 1]";
				String white = "[" + whitesColorName + " \"Player\"]";
                String black = "[" + blacksColorName + " \"Computer\"]";
				String result = "[Result]";

                //writes the header of the PGN file
                writer.println(event+"\n"+site+"\n"+d+"\n"+round+"\n"+white+"\n"+black+"\n"+result+"\n\n");
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

		//	prints out message to alert the user of an invalid move.
		public void printInvalidMoveMessage(){
			String invalidMoveMessage = "The move you tried to make is not valid.";
			JOptionPane.showMessageDialog(frame, invalidMoveMessage, "Warning", JOptionPane.WARNING_MESSAGE);
		}

		/*takes the piece being moved and the destination of move. Then composes
		the a string of the move based on PGN notation
		params:
			Piece p:			piece being moved
			int c2:				destination column
			int r2:				destination row
			boolean capture: 	if a piece was captured.
		*/
		public void addMoveToList(Piece p, int c2, int r2, boolean capture, int r1, int c1){
			//Composes move based on PGN notation
			if(p.getSymbol().equals("Kn")) move = "N";
			else if(p.getSymbol().equals("P")) move = "";
			else move = p.getSymbol();
			//if a piece is captured during move
			if(capture){
				move = move + "x" + letters[c2+1].getText().toLowerCase() + numbers[r2].getText();
			}
			else if(move.equals("R")){
				if(r1 == r2){
					move = move + letters[c1+1].getText().toLowerCase() + letters[c2+1].getText().toLowerCase() + numbers[r2].getText();
				}
				else if(c1 == c2){
					move = move + numbers[r1].getText() + letters[c2+1].getText().toLowerCase() + numbers[r2].getText();
				}
				else{
					move = move + letters[c2+1].getText().toLowerCase() + numbers[r2].getText();
				}
			}
			else{
				move = move + letters[c2+1].getText().toLowerCase() + numbers[r2].getText();
			}
			if(p.isWhite()){
				//if the opponents king is put into check during move
				if(board.blackCheck()){
					move += "+";
				}
			}
			else{
				//if the opponents king is put into check during move
				if(board.whiteCheck()){
					move += "+";
				}
			}
			if(playerTurn){
				turnNumber++;
				player1Moves[turnNumber-1] = move;
			}
			else{
				player2Moves[turnNumber-1] = move;
			}
		}

		/*
		* loads a pawn to the corresponding space in the move. Parses the row and column and loads to space
		* 	params:
		*	String move: the move parsed from load file
		* 	boolean white: which color is being loaded
		*/
		public void loadPawn(String move, boolean white){
			int y = 0;
			for(int j = 0; j < 9; j++){
				if(Character.toString(move.charAt(0)).equals(letterText[j]))
					y = j-1;
			}
			if(move.charAt(1) != 'x'){
				int x = Character.getNumericValue(move.charAt(1));
				if(white){
					loadWhitePawn(x,y);
				}
				else{
					loadBlackPawn(x,y);
				}
			}
			else{
				loadPawnCapture(white,move,y);
			}
		}

		/*
		* adds the white pawn to the board and then removes the same pawn from its previous location
		* 	params:
		*	int x: the parsed row
		*	int y: the parsed column
		*/
		public void loadWhitePawn(int x, int y){
			if(board.getSpacePiece(9-x,y) == null){
				if(board.getSpacePiece(10-x,y) != null && board.getSpacePiece(10-x,y).isWhite()){
					if(board.getSpacePiece(10-x,y).getSymbol().equals("P")){
						board.removeFromSpace(10-x,y,false);
						moves += " " + letterText[y+1] + numberText[10-x];
					}
				}
			}
			else{
				if(board.getSpacePiece(9-x,y).isWhite()){
					if(board.getSpacePiece(9-x,y).getSymbol().equals("P")){
						board.removeFromSpace(9-x,y,false);
						moves += " " + letterText[y+1] + numberText[9-x];
					}
				}
			}
			board.addToSpace(8-x,y,new Pawn(true,8-x,y));
			moves += letterText[y+1] + numberText[8-x];
		}
			

		/*
		* adds the black pawn to the board and then removes the same pawn from its previous location
		* 	params:
		*	int x: the parsed row
		*	int y: the parsed column
		*/
		public void loadBlackPawn(int x, int y){
			if(board.getSpacePiece(7-x,y) == null){
				if(board.getSpacePiece(6-x,y) != null && !board.getSpacePiece(6-x,y).isWhite()){
					if(board.getSpacePiece(6-x,y).getSymbol().equals("P")){
						board.removeFromSpace(6-x,y,false);
						moves += " " + letterText[y+1] + numberText[6-x];
					}
				}
			}
			else{
				if(!board.getSpacePiece(7-x,y).isWhite()){
					if(board.getSpacePiece(7-x,y).getSymbol().equals("P")){
						board.removeFromSpace(7-x,y,false);
						moves += " " + letterText[y+1] + numberText[7-x];
					}
				}
			}
			board.addToSpace(8-x,y,new Pawn(false,8-x,y));
			moves += letterText[y+1] + numberText[8-x];
		}

		/*
		* adds the pawn to the board and then removes the same pawn from its previous location. Takes into account the pawn
		* captures another piece.
		* 	params:
		*	int x: the parsed row
		*	int y: the parsed column
		*/
		public void loadPawnCapture(boolean white, String move, int y){
			int y2 = 0;
			for(int j = 0; j < 9; j++){
				if(Character.toString(move.charAt(2)).equals(letterText[j]))
					y2 = j-1;
			}
			int x = Character.getNumericValue(move.charAt(3));
			board.removeFromSpace(8-x,y2,true);
			if(white){
				board.removeFromSpace(9-x,y,false);
				board.addToSpace(8-x,y2,new Pawn(true,8-x,y2));
				moves += " " + letterText[y+1] + numberText[9-x]+letterText[y2+1]+numberText[8-x];
			}
			else{
				board.removeFromSpace(7-x,y,false);
				board.addToSpace(8-x,y2,new Pawn(false,8-x,y2));
				moves += " " + letterText[y+1] + numberText[9-x]+letterText[y2+1]+numberText[8-x];
			}
		}

		/*
		* loads knight by parsing the row and column of new location. adding knight to new location
		* and removing the knight from the previous location.
		* 	params:
		*	String move: the move parsed from file
		*	boolean white: which color the piece is
		*/
		public void loadKnight(String move, boolean white){
			if(move.length() == 3 || (move.length() == 4 && move.charAt(3) == '+')){
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(1)).equals(letterText[j]))
						y = j-1;
				}

				int x = Character.getNumericValue(move.charAt(2));
				findAndRemoveKnight(white, x, y);
				board.addToSpace(8-x,y,new Knight(white,8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
			else{
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(2)).equals(letterText[j]))
						y = j-1;
				}

				int x = Character.getNumericValue(move.charAt(3));
				findAndRemoveKnight(white, x, y);
				board.addToSpace(8-x,y,new Knight(white,8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
		}

		/*
		* Searches all possible locations of the previous knight and removes it from the board
		* 	params:
		*	boolean white: which color the piece is
		*	int x: the row of the knight
		*	int y: the column of the knight
		*/
		public void findAndRemoveKnight(boolean white, int x, int y){
			if(board.getSpacePiece((8-x)+1,y-2) != null){
				if(board.getSpacePiece((8-x)+1,y-2).getSymbol().equals("Kn") && board.getSpacePiece((8-x)+1,y-2).isWhite() == white){
					board.removeFromSpace((8-x)+1,y-2,false);
					moves += " " + letterText[y-2+1] + numberText[8-x+1];
				}						
			}
			if(board.getSpacePiece((8-x)-1,y-2) != null){
				if(board.getSpacePiece((8-x)-1,y-2).getSymbol().equals("Kn") && board.getSpacePiece((8-x)-1,y-2).isWhite() == white){
					board.removeFromSpace((8-x)-1,y-2,false);
					moves += " " + letterText[y-2+1] + numberText[8-x-1];
				}
			}
			if(board.getSpacePiece((8-x)-1,y+2) != null){
				if(board.getSpacePiece((8-x)-1,y+2).getSymbol().equals("Kn") && board.getSpacePiece((8-x)-1,y+2).isWhite() == white){
					board.removeFromSpace((8-x)-1,y+2,false);
					moves += " " + letterText[y+2+1] + numberText[8-x-1];
				}
			}
			if(board.getSpacePiece((8-x)+1,y+2) != null){
				if(board.getSpacePiece((8-x)+1,y+2).getSymbol().equals("Kn") && board.getSpacePiece((8-x)+1,y+2).isWhite() == white){
					board.removeFromSpace((8-x)+1,y+2,false);
					moves += " " + letterText[y+2+1] + numberText[8-x+1];
				}
			}
			if(board.getSpacePiece((8-x)+2,y+1) != null){
				if(board.getSpacePiece((8-x)+2,y+1).getSymbol().equals("Kn") && board.getSpacePiece((8-x)+2,y+1).isWhite() == white){
					board.removeFromSpace((8-x)+2,y+1,false);
					moves += " " + letterText[y+1+1] + numberText[8-x+2];
				}
			}
			if(board.getSpacePiece((8-x)+2,y-1) != null){
				if(board.getSpacePiece((8-x)+2,y-1).getSymbol().equals("Kn") && board.getSpacePiece((8-x)+2,y-1).isWhite() == white){
					board.removeFromSpace((8-x)+2,y-1,false);
					moves += " " + letterText[y-1+1] + numberText[8-x+2];
				}
			}
			if(board.getSpacePiece((8-x)-2,y+1) != null){
				if(board.getSpacePiece((8-x)-2,y+1).getSymbol().equals("Kn") && board.getSpacePiece((8-x)-2,y+1).isWhite() == white){
					board.removeFromSpace((8-x)-2,y+1,false);
					moves += " " + letterText[y+1+1] + numberText[8-x-2];
				}
			}
			if(board.getSpacePiece((8-x)-2,y-1) != null){
				if(board.getSpacePiece((8-x)-2,y-1).getSymbol().equals("Kn") && board.getSpacePiece((8-x)-2,y-1).isWhite() == white){
					board.removeFromSpace((8-x)-2,y-1,false);
					moves += " " + letterText[y-1+1] + numberText[8-x-2];
				}
			}
		}

		/*
		* loads bishop by parsing the row and column of new location. adding bishop to new location
		* and removing the bishop from the previous location.
		* 	params:
		*	String move: the move parsed from file
		*	boolean white: which color the piece is
		*/
		public void loadBishop(String move, boolean white){
			if(move.length() == 3 || (move.length() == 4 && move.charAt(3) == '+')){
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(1)).equals(letterText[j]))
						y = j-1;
				}
				int x = Character.getNumericValue(move.charAt(2));
				findAndRemoveBishop(white,x,y);
				board.addToSpace(8-x,y,new Bishop(white,8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
			else{
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(2)).equals(letterText[j]))
						y = j-1;
				}
				int x = Character.getNumericValue(move.charAt(3));
				findAndRemoveBishop(white, x, y);
				board.addToSpace(8-x,y,new Bishop(white,8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
		}

		/*
		* Searches all possible locations of the previous bishop and removes it from the board
		* 	params:
		*	boolean white: which color the piece is
		*	int x: the row of the bishop
		*	int y: the column of the bishop
		*/
		public void findAndRemoveBishop(boolean white, int x, int y){
			for(int i = 1; i < 8; i++){
				if(board.getSpacePiece((8-x)+i,y+i) != null){
					if(board.getSpacePiece((8-x)+i,y+i).getSymbol().equals("B") && board.getSpacePiece((8-x)+i,y+i).isWhite() == white){
						board.removeFromSpace((8-x)+i,y+i,false);
						moves += " " + letterText[y+i+1] + numberText[8-x+i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)-i,y+i) != null){
					if(board.getSpacePiece((8-x)-i,y+i).getSymbol().equals("B") && board.getSpacePiece((8-x)-i,y+i).isWhite() == white){
						board.removeFromSpace((8-x)-i,y+i,false);
						moves += " " + letterText[y+i+1] + numberText[8-x-i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)+i,y-i) != null){
					if(board.getSpacePiece((8-x)+i,y-i).getSymbol().equals("B") && board.getSpacePiece((8-x)+i,y-i).isWhite() == white){
						board.removeFromSpace((8-x)+i,y-i,false);
						moves += " " + letterText[y-i+1] + numberText[8-x+i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)-i,y-i) != null){
					if(board.getSpacePiece((8-x)-i,y-i).getSymbol().equals("B") && board.getSpacePiece((8-x)-i,y-i).isWhite() == white){
						board.removeFromSpace((8-x)-i,y-i,false);
						moves += " " + letterText[y-i+1] + numberText[8-x-i];
						break;
					}
				}
			}
		}

		/*
		* loads queen by parsing the row and column of new location. adding queen to new location
		* and removing the queen from the previous location.
		* 	params:
		*	String move: the move parsed from file
		*	boolean white: which color the piece is
		*/
		public void loadQueen(String move, boolean white){
			if(move.length() == 3 || (move.length() == 4 && move.charAt(3) == '+')){
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(1)).equals(letterText[j]))
						y = j-1;
				}
				int x = Character.getNumericValue(move.charAt(2));
				findAndRemoveQueen(white, x, y);
				board.addToSpace(8-x,y,new Queen(white, 8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
			else{
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(2)).equals(letterText[j]))
						y = j-1;
				}
				int x = Character.getNumericValue(move.charAt(3));
				findAndRemoveQueen(white, x, y);
				board.addToSpace(8-x,y,new Queen(white, 8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
		}

		/*
		* Searches all possible locations of the previous queen and removes it from the board
		* 	params:
		*	boolean white: which color the piece is
		*	int x: the row of the queen
		*	int y: the column of the queen
		*/
		public void findAndRemoveQueen(boolean white, int x, int y){
			for(int i = 1; i < 8; i++){
				if(board.getSpacePiece((8-x)+i,y+i) != null){
					if(board.getSpacePiece((8-x)+i,y+i).getSymbol().equals("Q") && board.getSpacePiece((8-x)+i,y+i).isWhite() == white){
						board.removeFromSpace((8-x)+i,y+i,false);
						moves += " " + letterText[y+i+1] + numberText[8-x+i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)-i,y+i) != null){
					if(board.getSpacePiece((8-x)-i,y+i).getSymbol().equals("Q") && board.getSpacePiece((8-x)-i,y+i).isWhite() == white){
						board.removeFromSpace((8-x)-i,y+i,false);
						moves += " " + letterText[y+i+1] + numberText[8-x-i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)+i,y-i) != null){
					if(board.getSpacePiece((8-x)+i,y-i).getSymbol().equals("Q") && board.getSpacePiece((8-x)+i,y-i).isWhite() == white){
						board.removeFromSpace((8-x)+i,y-i,false);
						moves += " " + letterText[y-i+1] + numberText[8-x+i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)-i,y-i) != null){
					if(board.getSpacePiece((8-x)-i,y-i).getSymbol().equals("Q") && board.getSpacePiece((8-x)-i,y-i).isWhite() == white){
						board.removeFromSpace((8-x)-i,y-i,false);
						moves += " " + letterText[y-i+1] + numberText[8-x-i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)-i,y) != null){
					if(board.getSpacePiece((8-x)-i,y).getSymbol().equals("Q") && board.getSpacePiece((8-x)-i,y).isWhite() == white){
						board.removeFromSpace((8-x)-i,y,false);
						moves += " " + letterText[y+1] + numberText[8-x-i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)+i,y) != null){
					if(board.getSpacePiece((8-x)+i,y).getSymbol().equals("Q") && board.getSpacePiece((8-x)+i,y).isWhite() == white){
						board.removeFromSpace((8-x)+i,y,false);
						moves += " " + letterText[y+1] + numberText[8-x+i];
						break;
					}
				}
				if(board.getSpacePiece((8-x),y+i) != null){
					if(board.getSpacePiece((8-x),y+i).getSymbol().equals("Q") && board.getSpacePiece((8-x),y+i).isWhite() == white){
						board.removeFromSpace((8-x),y+i,false);
						moves += " " + letterText[y+i+1] + numberText[8-x];
						break;
					}
				}
				if(board.getSpacePiece((8-x),y-i) != null){
					if(board.getSpacePiece((8-x),y-i).getSymbol().equals("Q") && board.getSpacePiece((8-x),y-i).isWhite() == white){
						board.removeFromSpace((8-x),y-i,false);
						moves += " " + letterText[y-i+1] + numberText[8-x];
						break;
					}
				}
			}
		}

		/*
		* loads rook by parsing the row, column of new location and either the row or column of the previous location.
		* adding rook to new location and removing the rook from the previous location.
		* 	params:
		*	String move: the move parsed from file
		*	boolean white: which color the piece is
		*/
		public void loadRook(String move, boolean white){
			if(move.length() == 3 || (move.length() == 4 && move.charAt(3) == '+')){
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(1)).equals(letterText[j]))
						y = j-1; //new column
				}
				int x = Character.getNumericValue(move.charAt(2)); //new row
				findAndRemoveRook(white, x, y);
				board.addToSpace(8-x,y,new Rook(white,8-x,y));
				moves += letterText[y+1] + numberText[8-x];
			}
			else{
				//if previous row is also given
				if(move.charAt(1) == '1' || move.charAt(1) == '2' || move.charAt(1) == '3' || move.charAt(1) == '4' || move.charAt(1) == '5' ||
						move.charAt(1) == '6' || move.charAt(1) == '7' || move.charAt(1) == '8'){
					removeAndLoadRookIfGivenRow(move, white);
				}
				//if previous column is also given
				else if(move.charAt(1) == 'a' || move.charAt(1) == 'b' || move.charAt(1) == 'c' || move.charAt(1) == 'd' || move.charAt(1) == 'e' ||
							move.charAt(1) == 'f' || move.charAt(1) == 'g' || move.charAt(1) == 'h'){
					removeAndLoadRookIfGivenCol(move, white);
				}
				else if(move.charAt(1) == 'x'){
					int y = 0;
					for(int j = 0; j < 9; j++){
						if(Character.toString(move.charAt(2)).equals(letterText[j]))
							y = j-1;
					}
					int x = Character.getNumericValue(move.charAt(3));
					findAndRemoveRook(white, x, y);
					board.addToSpace(8-x,y,new Rook(white,8-x,y));
					moves += letterText[y+1] + numberText[8-x];
				}
			}
		}

		//searches the pevious column for the rook and removes it from the board
		// params
		// String move: the move parsed from load file
		// boolean white: which color the piece is
		public void removeAndLoadRookIfGivenCol(String move, boolean white){
			int y1 = 0;
			int y2 = 0;
			for(int j = 0; j < 9; j++){
				if(Character.toString(move.charAt(1)).equals(letterText[j]))
					y1 = j-1; //old column
				else if(Character.toString(move.charAt(2)).equals(letterText[j]))
					y2 = j-1; //new column
			}
			int x = Character.getNumericValue(move.charAt(3)); //new row
			if(board.getSpacePiece(8-x,y1).getSymbol().equals("R")){
				board.removeFromSpace(8-x,y1,false);
				moves += " "+letterText[y1+1] + numberText[8-x];
			}
			board.addToSpace(8-x,y2,new Rook(white,8-x,y2));
			moves += letterText[y2+1] + numberText[8-x];
		}

		//searches the previous ow for the rook and removes it from the board
		// params
		// String move: the move parsed from load file
		// boolean white: which color the piece is
		public void removeAndLoadRookIfGivenRow(String move, boolean white){
			int y = 0;
			for(int j = 0; j < 9; j++){
				if(Character.toString(move.charAt(2)).equals(letterText[j]))
					y = j-1;
			}
			int x1 = Character.getNumericValue(move.charAt(1));
			int x2 = Character.getNumericValue(move.charAt(3));
			if(white){
				//System.out.println("white");
				board.addToSpace(8-x2,y,new Rook(true,8-x2,y));
			}
			else{
				//System.out.println("black");
				board.addToSpace(8-x2,y,new Rook(false,8-x2,y));
			}
			if(board.getSpacePiece(8-x1,y).getSymbol().equals("R")){
				board.removeFromSpace(8-x1,y,false);
				moves += " "+letterText[y+1] + numberText[8-x1];
			}
			moves += letterText[y+1] + numberText[8-x2];
		}

		/*
		* Searches all possible locations of the previous rook and removes it from the board
		* 	params:
		*	boolean white: which color the piece is
		*	int x: the row of the rook
		*	int y: the column of the rook
		*/
		public void findAndRemoveRook(boolean white, int x, int y){
			for(int i = 1; i < 8; i++){
				if(board.getSpacePiece((8-x)-i,y) != null){
					if(board.getSpacePiece((8-x)-i,y).getSymbol().equals("R") && board.getSpacePiece((8-x)-i,y).isWhite() == white){
						board.removeFromSpace((8-x)-i,y,false);
						moves += " "+letterText[y+1] + numberText[8-x-i];
						break;
					}
				}
				if(board.getSpacePiece((8-x)+i,y) != null){
					if(board.getSpacePiece((8-x)+i,y).getSymbol().equals("R") && board.getSpacePiece((8-x)+i,y).isWhite() == white){
						board.removeFromSpace((8-x)+i,y,false);
						moves += " "+letterText[y+1] + numberText[8-x+i];
						break;
					}
				}
				if(board.getSpacePiece((8-x),y+i) != null){
					if(board.getSpacePiece((8-x),y+i).getSymbol().equals("R") && board.getSpacePiece((8-x),y+i).isWhite() == white){
						board.removeFromSpace((8-x),y+i,false);
						moves += " "+letterText[y+i+1] + numberText[8-x];
						break;
					}
				}
				if(board.getSpacePiece((8-x),y-i) != null){
					if(board.getSpacePiece((8-x),y-i).getSymbol().equals("R") && board.getSpacePiece((8-x),y-i).isWhite() == white){
						board.removeFromSpace((8-x),y-i,false);
						moves += " "+letterText[y-i+1] + numberText[8-x];
						break;
					}
				}
			}
		}

		//loads a kingside castling move based on which color the king is.
		// params:
		// boolean white: which color the king is
		public void loadKingsideCastling(boolean white){
			if(white){
				King k = board.getWhiteKing();
				int row = k.getRow();
				int col = k.getCol();
				board.removeFromSpace(7,7,false); //removes the rook
				board.removeFromSpace(row,col,false); //removes the king
				board.addToSpace(row,col+2,k); //adds king
				board.addToSpace(row,col+1,new Rook(true,row,col+1)); //add rook
				k.setRow(row);
				k.setCol(col+2);
			}
			else{
				King k = board.getBlackKing();
				int row = k.getRow();
				int col = k.getCol();
				board.removeFromSpace(0,7,false); //removes rook
				board.removeFromSpace(row,col,false); //removes king
				board.addToSpace(row,col+2,k); //adds king
				board.addToSpace(row,col+1,new Rook(false,row,col+1)); //adds rook
				k.setRow(row);
				k.setCol(col+2);
			}
		}

		//loads a queenside castling move based on which color the king is.
		// params:
		// boolean white: which color the king is
		public void loadQueensideCastling(boolean white){
			if(white){
				King k = board.getWhiteKing();
				int row = k.getRow();
				int col = k.getCol();
				board.removeFromSpace(7,0,false); //removes the queenside rook
				board.removeFromSpace(row,col,false); //removes the king
				board.addToSpace(row,col-2,k); //adds the king to new location
				board.addToSpace(row,col-1,new Rook(true,row,col-1)); //adds the rook to new location
				k.setRow(row);
				k.setCol(col-2);
			}
			else{
				King k = board.getWhiteKing();
				int row = k.getRow();
				int col = k.getCol();
				board.removeFromSpace(0,0,false); //removes queenside rook
				board.removeFromSpace(row,col,false); //removes king
				board.addToSpace(row,col-2,k); //adds king
				board.addToSpace(row,col-1,new Rook(false,row,col-1)); //adds rook
				k.setRow(row);
				k.setCol(col-2);
			}
		}

		/*
		* loads king by parsing the row, and column of new location
		* adding king to new location and removing the king from the previous location.
		* 	params:
		*	String move: the move parsed from file
		*	boolean white: which color the piece is
		*/
		public void loadKing(String move, boolean white){
			if(white){
				King k = board.getWhiteKing();
				int row = k.getRow();
				int col = k.getCol();
				board.removeFromSpace(row,col,false);
				moves += " "+letterText[col+1] + numberText[row];
				addKing(move, white, k);
			}
			else{
				King k = board.getBlackKing();
				int row = k.getRow();
				int col = k.getCol();
				board.removeFromSpace(row,col,false);
				moves += " "+letterText[col+1] + numberText[row];
				addKing(move, white, k);
			}
		}

		//parses the move and adds king to the new location
		//params:
		//String move: the move parsed from the load file
		//boolean white: what color the king is
		//King k: the king piece
		public void addKing(String move, boolean white, King k){
			if(move.length() == 3 || (move.length() == 4 && move.charAt(3) == '+')){
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(1)).equals(letterText[j]))
						y = j-1; //new column
				}
				int x = Character.getNumericValue(move.charAt(2)); //new row
				board.addToSpace(8-x,y,k);
				moves += letterText[y+1] + numberText[8-x];
				k.setRow(8-x);
				k.setCol(y);
			}
			else{
				int y = 0;
				for(int j = 0; j < 9; j++){
					if(Character.toString(move.charAt(2)).equals(letterText[j]))
						y = j-1; //new column
				}
				int x = Character.getNumericValue(move.charAt(3)); // new row
				board.addToSpace(8-x,y,k);
				moves += letterText[y+1] + numberText[8-x];
				k.setRow(8-x);
				k.setCol(y);
			}
		}


        // Converts move from (row, col) notation to algebraic notation.
        public String toAlgebraicNotation(int r1, int c1, int r2, int c2) {
            return letterText[c1+1] + numberText[r1] + letterText[c2+1] + numberText[r2];
        }

        // Converts move from algebraic notation to (row, col) notation.
        // Inverse of toAlgebraicNotation.
        // Example: e2e4 -> ((6, 4), (4, 4))
        public int[] toRowColNotation(String alg) {
            int r1, c1, r2, c2;
            String a = "" + alg.charAt(0);
            String b = "" + alg.charAt(1);
            String c = "" + alg.charAt(2);
            String d = "" + alg.charAt(3);
            r1 = Arrays.asList(numberText).indexOf(b);
            c1 = Arrays.asList(letterText).indexOf(a) - 1;
            r2 = Arrays.asList(numberText).indexOf(d);
            c2 = Arrays.asList(letterText).indexOf(c) - 1;
            int[] move = {r1, c1, r2, c2};
            return move;
        }

        // Gets the best next move from Stockfish given the previous moves.
        // Returns move in algebraic notation.
        public String aiMove(Stockfish sf, String moves) {
            String move = "";
            try {
                sf.write("position startpos moves " + moves);
                sf.write("go movetime 1000");
                while (!move.contains("bestmove")) {
                    move = sf.read();
                }
                int index = move.indexOf("bestmove") + "bestmove ".length();
                move = move.substring(index, index + 4);
            } catch (Exception ex) {
                System.err.println("AI failed to produce move.");
                ex.printStackTrace();
            }
            return move;
        }
    }
}