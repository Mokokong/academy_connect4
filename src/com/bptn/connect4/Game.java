package com.bptn.connect4;

import java.util.Scanner;

import com.bptn.connect4.Exceptions.*;

public class Game {

    private Player[] players;
    private Board board;
    private static Scanner scanner = new Scanner(System.in);

    public Game() {
        // Let's default it two players for now. Later, you can improve upon this to allow the game creator to choose how many players are involved.
        this.players = new Player[2];	// complete line.**
        this.board = new Board();	 	// complete line**
    }

    public void setUpGame() {
    
        // create players
    	createPlayers();

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
            
            if (board.checkIfPlayerIsTheWinner(currentPlayer.getPlayerNumber())) {
                printWinner(currentPlayer);
                scanner.close();
                noWinner = false;
            } else {
                currentPlayerIndex = (currentPlayerIndex+1 )%2;// reassign the variable to allow the game to continue. Note the index would wrap back to the first player if we are at the end. Think of using modulus (%).**
            }
        }
    }


    
    // internal methods
    private void createPlayers() {
    	
		System.out.println("Enter player 1's name: ");
//		players[0] = new Player(scanner.nextLine(), "1");
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

			// wrap the code in here with a conditional block that enables the check
			// described above.**

			System.out.println("Error! Both Players cannot have the same name.");
			System.out.println("Enter player 2's name: ");
			playerTwoName = scanner.nextLine();

		}
		
//		players[1] = new Player(playerTwoName, "2");
		
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

	
	

}