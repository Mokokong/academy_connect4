package com.bptn.connect4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.bptn.connect4.Exceptions.*;

public class Game {

    private Player[] players;
    private Board board;
    private static Scanner scanner = new Scanner(System.in);

    public Game() {
        
    	int count = getPlayerNumber();

        this.players = new Player[count];	
        this.board = new Board();	 	
    }

    public void setUpGame() {
    
        // create players(for 2 players only)
    	//createPlayers();
    	
    	// create 2 or more players
    	createMorePlayers();

        // set up the board using the appropriate method
    	board.boardSetUp();
    	
        // print the board the using appropriate method
        board.printBoard();
    }

    public void printWinner(Player player) {
        System.out.println(player.getName() + " is the winner");
    }

    public void playerTurn(Player currentPlayer) {
        // player move
    	playerMove( currentPlayer);
        // print board
        board.printBoard();
    }

    public void play() {
        boolean noWinner = true;
        this.setUpGame();
        int currentPlayerIndex = 0;

        while (noWinner) {
            if (board.boardFull())// provide condition) **
            	{
                System.out.println("Board is now full. Game Ends.");
                return;
            }

            Player currentPlayer = players[currentPlayerIndex];
            // Override default tostring for Player class
//            System.out.println("It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer);
//           
            
            System.out.println("It is player " + currentPlayer.getPlayerNumber() + "'s turn. " + currentPlayer);
            playerTurn(currentPlayer);
            
            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerPiece())) {
                printWinner(currentPlayer);
                scanner.close();
                noWinner = false;
            } else {
                currentPlayerIndex = (currentPlayerIndex+1 )%players.length;// reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).**
            }
        }
    }


    
    // internal methods
    private void createPlayers() {
    	
		System.out.println("Enter player 1's name: ");

		players[0] = new Player(scanner.nextLine());
		
		System.out.println("Enter player 2's name: ");
		String playerTwoName = scanner.nextLine();

		/**
		 * Add logic to prevent a user from giving a second name that's equal to the first.
		 *  Allow the user to try as long as the names are not different.
		 */
		while (true) {

			boolean notRepeated = playerTwoName.equals(players[0].getName()) ? false : true;
			if (notRepeated) {
				break;
			}

			

			System.out.println("Error! Both Players cannot have the same name.");
			System.out.println("Enter player 2's name: ");
			playerTwoName = scanner.nextLine();

		}
		
		players[1] = new Player(playerTwoName);

	}

	private void playerMove(Player currentPlayer) {
		int col = currentPlayer.makeMove();
		
		

			try {
	
				moveValid(col);
	
			} catch (InvalidMoveException ime) {
	
				System.out.println(ime.getMessage());
				col = currentPlayer.makeMove();
			
			}
		
		// while column full ask player for a different move
		while (board.columnEntryFull(col)) {
			col = currentPlayer.makeMove();
			try {
				
				moveValid(col);
	
			} catch (InvalidMoveException ime) {
	
				System.out.println(ime.getMessage());

			}
			
		}
		
		
//		while (!board.addToken(col, currentPlayer.getPlayerNumber())) {
//		       // call board method to add token.
//		    	 board.addToken(col,  currentPlayer.getPlayerNumber());
//		}
		
		while (!board.addToken(col, currentPlayer.getPlayerPiece())) {
		       // call board method to add token.
		    	 board.addToken(col,  currentPlayer.getPlayerPiece());
		}


	}

	
	// checks if the selected col is within bounds
	private void moveValid(int col) throws InvalidMoveException{
		if(col > board.getBoard()[0].length-1) {
    		throw new InvalidMoveException("\nYou are out of the board. Possible Col selections range from 0 - "
    										+ Integer.toString(board.getBoard()[0].length-1)
    										+ "\n");
    	}
		
	}

	
	//prompts game creator to make select number of players
	private int getPlayerNumber() {
		
		int playerCount=-1;
		
		while (true) {
			
			// ensure input is an int
			try {
				System.out.println("How many players would you like to have?"
						+ "\nInput must be greater than or equal to 2");

				playerCount = scanner.nextInt();
				
			} catch (InputMismatchException ime) {
				System.out.println("Your input was not number >=2"
						+ "\nHow many players would you like to have?"
						+ "\nInput must be greater than or equal to 2");
		
				scanner.nextLine();
				playerCount = scanner.nextInt();
			}
			
			if(playerCount>1) {
				break;
			}
			
		}
		
				
		
		
		return playerCount;
	}
	
	// creates 2 or more players..upper limit on players not implemented
	private void createMorePlayers() {
	    	
			System.out.println("\nEnter player 1's name: ");
			scanner.nextLine();
			players[0] = new Player(scanner.nextLine());
			
			for (int i = 1; i < players.length; i++) {
				
				System.out.println("\nEnter player "+(i+1)+"'s name: ");
				String nextplayerName = scanner.nextLine();

				/**
				 * Add logic to prevent a user from giving a second name that's equal to the
				 * first. Allow the user to try as long as the names are not different.
				 */
				while (true) {
					//check if  name already exists
					boolean repeated = nameAlreadyTaken(nextplayerName);
					if (!repeated) {
						break;
					}

					System.out.println("\nError! That name is already taken");
					System.out.println("Re-Enter player "+(i+1)+"'s name: ");
					nextplayerName = scanner.nextLine();

				}


				players[i] = new Player(nextplayerName);

				
				
			}
			
			
		}

	 // check if name already taken
	 private boolean nameAlreadyTaken(String name) {
		 
		 ArrayList<Player> playersArray = new ArrayList<>(Arrays.asList(players)) ;
		 
		 // remove all null players
		 while (playersArray.remove(null)) {}
		 
		
		 for (Player player : playersArray) {
			 
			 if (player.getName().equals(name)) {
				 return true;
				
			}
			
		}
		return false; 
	 }

}