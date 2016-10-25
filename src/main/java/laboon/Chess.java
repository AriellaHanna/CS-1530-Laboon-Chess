package laboon;

import java.util.*;
import java.text.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.undo.*;

public class Chess {
	private JFrame frame;	//global variables
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
	private Control ButtonListener;
	private int game;
	private int t;
	private boolean win = false;
	private boolean test;
    private Board board;

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
		frame = new JFrame("Laboon Chess");	//creates the JFrame
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
		theButtons = new JButton[game][game];	//creates the game buttons
		newGame = new JButton("New Game");	//creates a new game button
		loadGame = new JButton("Load Game"); //loads an existing game
		saveGame = new JButton("Save Game"); //saves current game
        title = new JLabel("", JLabel.CENTER);
		display = new JLabel();	//creates a JLabel that will be altered throughout the game telling the user who is up
        title.setText("Laboon Chess");
		display.setText("White is up first!");
        upperPanel = new JPanel();
		middlePanel = new JPanel();	//creates a JPanel
		lowerPanel = new JPanel();	//creates another JPanel

        upperPanel.setLayout(new GridLayout());
        upperPanel.add(title);

		middlePanel.setLayout(new GridLayout(0, 9));	//Sets the way the components that will be added will be displayed
		lowerPanel.setLayout(new FlowLayout(game));	//See comment in the line above
		middlePanel.add(numbers[0]);

        board = new Board();
		
		int i = 0;
		int a = 1;

		while(a < 9) 
		{
			for (int j = 0; j < theButtons[i].length; j++)
			{
                Piece p = board.getSpace(i,j).getPiece();
				theButtons[i][j] = new JButton(p == null ? " " : p.getSymbol());
				theButtons[i][j].addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked

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

		newGame.addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked
		loadGame.addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked
		saveGame.addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked
		lowerPanel.add(newGame);	//adds new game button to the panel
		lowerPanel.add(loadGame); //adds load game button to the panel
		lowerPanel.add(saveGame); //adds save game button to the panel
		lowerPanel.add(display);	//adds the JLabel to the panel
        frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);	//adds the one JLabel to the JFrame and puts it in the center of the GUI
		frame.add(lowerPanel, BorderLayout.SOUTH);	//adds the other JLabel to the JFrame and puts it in the southern part of the GUI

		frame.setVisible(true);
	}

	private class Control implements ActionListener
	{
		public void actionPerformed(ActionEvent e)	//this creates the different actions that will take place when certain buttons are clicked
		{
			JFileChooser fc = new JFileChooser();

			if(e.getSource() == newGame)	//when new game is clicked
			{
				Object[] options = {"White", "Black"};
				
				int newWindow = JOptionPane.showOptionDialog(frame,
					"Choose your color:", 
					"Select a color",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[1]);

				for(int i=0; i<game; i++)	//these for loops reset the game buttons to empty strings and allow them to be clicked again
				{
					for(int j=0; j<game; j++)
					{
                        Piece p = board.getSpace(i,j).getPiece();
						theButtons[i][j].setText(p == null ? " " : p.getSymbol());
						theButtons[i][j].setEnabled(true);
					}
				}

				display.setText("White is up first!");	//tells the user who is up first
			}

			else if(e.getSource() == loadGame) //when load game is clicked
			{
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					File file = fc.getSelectedFile();
				}
			}

			else if(e.getSource() == saveGame) //when save game is clicked
			{
				if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 
				{
					File file = fc.getSelectedFile();
				}				
			}
		}
	}
}
