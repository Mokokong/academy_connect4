package com.bptn.connect4;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.bptn.connect4.Exceptions.*;

/**
The player class is concerned with describing a player and things that relate to it. It keeps track of the name of a player, the order of the player in relation to other players in the game, and the move the player may want to make (which is just as simple as the user picking which column of the board they want their token to be dropped in). This class could also have logic to create only valid users. E.g. the playerNumber should not be greater than 4 based on the specification we've received.
*/
public class Player {

	private String name;
	private String playerNumber;
	private String playerPiece;
	static int id = 1;
	
	// Question: should scanner be static or not?
	// Static: it needs to be accessible to all objects of the class but that is subject to change if more classes 
	// need a scanner obj
	private Scanner scanner = new Scanner(System.in);
	
	public Player(String name, String playerNumber) {
	   
		this.name = name;
		this.playerNumber = playerNumber;
		
	}
	
	public Player(String name) {
		   
		this.name = name;
//		this.playerNumber = setplayerColor();
		this.playerNumber = Integer.toString(id);
		id++;
		this.playerPiece = setplayerColor();
		
	}
	
	
	// create getter methods
	public String getName() {
		
		return name;
	}
	
	
	public String getPlayerNumber() {
			
			return playerNumber;
		}
	
	//end of getters
	
	
	
	public int makeMove() {
	    System.out.println("Make your move. What column do you want to put a token in?");
	    int column ;
	    try {
	    	column = scanner.nextInt();
			
		} catch (InputMismatchException ime) {
			System.out.println("\nColumns must be numbers not alphanumeric\n");
			System.out.println("Re- make your move. What column do you want to put a token in?");
			scanner.nextLine();
			column = scanner.nextInt();
			scanner.nextLine();
		}
	    
	     
	    return column;
	}
	
	public String toString() {
	    return ("Player " + playerNumber + " is " + name);
	}


	// color selection
	
	private String setplayerColor() {
		int color ;
		String playerColor ="";
		System.out.println("Select your color from the following:");
		System.out.println("1. Red "
						+ "\n2. Green"
						+ "\n3. Yellow"
						+ "\n4. Blue"
						+ "\n5. Purple"
						+ "\n6. Cyan"
						+ "\nAny other number for the default color");
		try {
			color = scanner.nextInt();
			
		} catch (InputMismatchException ime) {
			System.out.println("Your selection was not a number."
							+ "\nPlease re-enter you color selection as above");
			scanner.nextLine();
			color = scanner.nextInt();
		}
		
		
		
		switch (color) 
		{
		
			case 1: {
				playerColor = (ConsoleColors.RED + playerNumber + ConsoleColors.RESET);
				break;
	
			}
			
			case 2: {
				playerColor = (ConsoleColors.GREEN + playerNumber + ConsoleColors.RESET);
				
				break;
	
			}
			
			case 3: {
				playerColor = (ConsoleColors.YELLOW +playerNumber + ConsoleColors.RESET);
				
				break;
	
			}
			
			case 4: {
				playerColor = (ConsoleColors.BLUE +playerNumber + ConsoleColors.RESET);
				
				break;
	
			}
			
			case 5: {
				playerColor = (ConsoleColors.PURPLE + playerNumber + ConsoleColors.RESET);
				
				break;
	
			}
			
			case 6: {
				playerColor = (ConsoleColors.CYAN +playerNumber + ConsoleColors.RESET);
				break;
	
			}
	
			default: {
				playerColor = playerNumber;
				break;
			}
		}
	
		return playerColor;
	}

	public String getPlayerPiece() {
		return playerPiece;
	}

	
	

}