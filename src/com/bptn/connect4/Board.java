package com.bptn.connect4;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


import com.bptn.connect4.Exceptions.*;



public class Board {
    // add instance variables
	private String [][] board;
	private Scanner scanner = new Scanner(System.in);


    public void boardSetUp() {
        
    	// select number of columns and rows
    	
    	 colRowSelection();
    	 
        // initialize empty board with dashes (-)
        intializeBoard("-");
      
    }

    
    public void printBoard() {
    	System.out.println("\n");
    	
        for (String[] row : board) {
            System.out.println(Arrays.toString(row));
        }
        
        System.out.println("\n");
    }

    public boolean columnFull(int col) {
    	// check if the column is full by just checking the 0'th row's value
        if (board[0][col].equals("-")) {
        	
            return false;
        }
        
      
        return true;
    }

    
    
    //  to evaluate whether column full when a new move is being placed 
    public boolean columnEntryFull(int col) {
    	if (columnFull(col)) {
    		
    		try {
    			columnFullthrow();
    		} catch (ColumnFullException cfe) {
    			System.out.println(cfe.getMessage());
    		}
    		return true;
    		
    	}else {
    		return false;
    	}
    }
    public boolean boardFull() {
        // True understanding this code.
        for (int i = 0; i < this.board[0].length; i++) {
            if (!columnFull(i)) {
                return false;
            }
        }
        scanner.close();
        return true;
    }

    public boolean addToken(int colToAddToken, String token) {
    	
    	
        int rowToAddToken = board.length - 1;
        
        return insertSuccessful(rowToAddToken,colToAddToken, token);
    }

    public boolean checkIfPlayerIsTheWinner(String playerNumber) {
    	
        if (checkHorizontal(playerNumber)) {
        	System.out.println("Player won on Horizontal");
            return true;
        } else if (checkLeftDiagonal(playerNumber)) {
        	System.out.println("Player won on LeftDiagonal");
            return true;
        }
        // what other conditions should we include here?**
        else if (checkRightDiagonal(playerNumber)) {
        	System.out.println("Player won on RightDiagonal");
            return true;
        }
        else if (checkVertical(playerNumber)) {
        	System.out.println("Player won on Vertical");
            return true;
        }
        
        else {
        
        return false;
        }
    }

    public boolean checkVertical(String playerNumber) {
        for (int col = 0; col < board[0].length; col++) {
            // length - 3 here because we are comparing 4 in a row items
            for (int row = 0; row < board.length - 3; row++) {
                if (board[row][col].equals(playerNumber)) {
                    if (board[row][col] == board[row + 1][col]
                            && board[row][col] == board[row + 2][col]
                            && board[row][col] == board[row + 3][col]) {
                        return true;
                    }
                }
            }
        }

        return false;

    }

    public boolean checkHorizontal(String playerNumber) {
        for (int col = 0; col < board[0].length-3; col++) {
           
        	for (int row = 0; row < board.length ; row++) {
                if (board[row][col].equals(playerNumber)) {
                    if (board[row][col] == board[row][col+1]
                            && board[row][col] == board[row ][col+2]
                            && board[row][col] == board[row ][col+ 3]) {
                        return true;
                    }
                }
            }
        
        
        
        }
        return false;
    }

     public boolean checkLeftDiagonal(String playerNumber) {
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[0].length - 3; col++) {
                if (board[row][col].equals(playerNumber)) {
                    if (board[row][col] == board[row + 1][col + 1]
                            && board[row][col] == board[row + 2][col + 2]
                            && board[row][col] == board[row + 3][col + 3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkRightDiagonal(String playerNumber) {
       // implment method and return an appropriate return type**
    	

    	 for (int row = 0; row < board.length - 3; row++) {

    		 	for (int col = board[0].length-1; col >= 3; col--)
            
            {
                if (board[row][col].equals(playerNumber)) {
                    if (board[row][col] == board[row + 1][col - 1]
                            && board[row][col] == board[row + 2][col - 2]
                            && board[row][col] == board[row + 3][col - 3]) {
                    	
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //internal methods
    
    // initialize board with custom empty string 
    private void intializeBoard(String emptyChar) {
    	
  	  for (String[] row : board) {
  	       	
        	Arrays.fill(row,emptyChar);
        }
    }

   
    // throw exception only when dimension not valid
    private void dimensionNotValid(int dimension) throws MinimumDimensionException {
    	
    	if (dimension <=3) 
    	{
    		throw new MinimumDimensionException("\nDimensions of the Board cannont be less than 4."
    											+ "Resetting dimension to minimum value of 4\n");
    		
    	}
    }
    
    
    private void colRowSelection() {
    	
    	System.out.println("------ Board Set up -------");
        System.out.println("Number of rows: ");
        
        int rows;
        int columns; 
        
        // row input exception handling
        try {
        	 rows = scanner.nextInt(); 
			
		} catch (InputMismatchException ime) {
			System.out.println("Row must be number");
			System.out.println("Re-Enter number of rows: ");
	        scanner.nextLine();
			rows = scanner.nextInt();
		}
        
        System.out.println("\nNumber of cols: ");
        // column input exception handling
		try {
			 columns = scanner.nextInt();// receive column value

		} catch (InputMismatchException ime) {
			System.out.println("Column must be number");
			System.out.println("Re-Enter number of columns: ");
			scanner.nextLine();
			columns = scanner.nextInt();
		}
       
       // check that min row and col are 4 or more
		// row 
		try {
			
			dimensionNotValid(rows);
			
		} catch (MinimumDimensionException mde) {
			System.out.println(mde.getMessage());
			// set dimension to min valid value
			rows = 4;
			
		}
		// column
		
		try {

			dimensionNotValid(columns);

		} catch (MinimumDimensionException mde) {
			System.out.println(mde.getMessage());
			// set dimension to min valid value
			columns = 4;

		}
       
        this.board = new String [rows][columns]; // initialize a row by column array;
//    	col = columns;
    	
    }


    public String[][] getBoard() {
		return board;
	}


	private boolean insertSuccessful(int rowToAddToken,int colToAddToken, String token) {
    	
    	// searching for the right row level of the board to place the token 
        while (rowToAddToken >=0) {
            if (board[rowToAddToken][colToAddToken].equals("-")) {
               // We know the right row and column to place the token. Place it and then return true**.
            	board[rowToAddToken][colToAddToken]=token;
                return true;
            } else {
                rowToAddToken -= 1;
            }
        }

        return false;
    	
    }

	private void columnFullthrow() throws ColumnFullException {
		
		throw new ColumnFullException("\nYour selected column is full.\n");
	}
}