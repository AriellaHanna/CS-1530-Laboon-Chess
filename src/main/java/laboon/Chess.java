package laboon;

import java.util.*;
import java.text.*;
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
	private JButton newGame;
	private JButton reset;
	private JButton undo;
	private Control ButtonListener;
	private int game;
	private int t;
	private boolean win = false;
	private boolean test;    

    public static void main(String[] args) {
        new Chess(8);
    }

    public static String poodle() {
        return "poodle";
    }

	public Chess(int size)
	{
		game = size;
		frame = new JFrame("Laboon Chess");	//creates the JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
<<<<<<< HEAD
=======
		frame.setVisible(true);
>>>>>>> 60cd2eb5c1e779ce0bafcb2fe2b88e9f37c383c1
		ButtonListener = new Control();
		theButtons = new JButton[game][game];	//creates the game buttons
		newGame = new JButton("New Game");	//creates a new game button
		//undo = new JButton("Undo");	//creates an undo button so the player can undo their move if they choose
		//undo.setEnabled(false);
        title = new JLabel("", JLabel.CENTER);
		display = new JLabel();	//creates a JLabel that will be altered throughout the game telling the user who is up
        title.setText("Laboon Chess");
		display.setText("White is up first!");
        upperPanel = new JPanel();
		middlePanel = new JPanel();	//creates a JPanel
		lowerPanel = new JPanel();	//creates another JPanel
		
        upperPanel.setLayout(new GridLayout());
        upperPanel.add(title);

		middlePanel.setLayout(new GridLayout(game, game));	//Sets the way the components that will be added will be displayed
		lowerPanel.setLayout(new FlowLayout(game));	//See comment in the line above
		
		for (int i = 0; i < theButtons.length; i++)	//these two for loops add the JButtons for the game into the JPanel
		{
			for (int j = 0; j < theButtons[i].length; j++)
			{
				theButtons[i][j] = new JButton(" ");
				theButtons[i][j].addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked
				middlePanel.add(theButtons[i][j]);
			}
		}
		
		newGame.addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked
		//undo.addActionListener(ButtonListener);	//this line calls the ActionLister so that the buttons will function when clicked
		lowerPanel.add(newGame);	//adds new game button to the panel
		//lowerPanel.add(undo);	//adds undo button to the panel
		lowerPanel.add(display);	//adds the JLabel to the panel
        frame.add(upperPanel, BorderLayout.NORTH);
		frame.add(middlePanel, BorderLayout.CENTER);	//adds the one JLabel to the JFrame and puts it in the center of the GUI
		frame.add(lowerPanel, BorderLayout.SOUTH);	//adds the other JLabel to the JFrame and puts it in the southern part of the GUI
<<<<<<< HEAD
		frame.setVisible(true);
=======
>>>>>>> 60cd2eb5c1e779ce0bafcb2fe2b88e9f37c383c1
	} 

	private class Control implements ActionListener
	{
		public void actionPerformed(ActionEvent e)	//this creates the different actions that will take place when certain buttons are clicked
		{
			if(e.getSource() == newGame)	//when new game is clicked
			{
				undo.setEnabled(false);	//undo cannot be clicked
				t=0;	//number of turns is 0
				
				for(int i=0; i<game; i++)	//these for loops reset the game buttons to empty strings and allow them to be clicked again
				{
					for(int j=0; j<game; j++)
					{
						theButtons[i][j].setText(" ");
						theButtons[i][j].setEnabled(true);
					}
				}
				
				display.setText("White is up first!");	//tells the user who is up first
			}
			
			// else if(e.getSource() == undo)	//when undo is clicked
			// {
			// 	boolean not = !test;
			// 	reset.setText(" ");	//button that was just pressed is reset to an empty string
			// 	reset.setEnabled(true); //button allowed to be re-clicked
			// 	t--;	//turns decreased by 1
				
			// 	if(test)	//if it was just X's turn, they will go again
			// 	{
			// 		test = not;
			// 		display.setText("It's X's turn again.");
			// 		t--;
					
			// 	}
				
			// 	else	//if it was just O's turn, they will go again
			// 	{
			// 		test = not;
			// 		display.setText("It's O's turn again.");
			// 		t--;
			// 	}	
			// }
		}
	}    
}
