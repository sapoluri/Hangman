package com.Hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

		  public char readInput() 
		  {
			  System.out.println("Enter your guess");
		    String word = " ";
		    BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
		    try {
		      word = br.readLine();
		    }
		    catch( IOException ex ) {
		      ex.printStackTrace();
		    }
		    return word.charAt( 0 );
		  }

		public void playerWon() {
			System.out.println("Congratulations! You Won.");			
		}
		
		public void playerLost()
		{
			System.out.println("Sorry! You Lost.");	
		}

		public void printAlreadyGuessed() {
			System.out.println("you have already guessed this letter");
		}

		public void printGuessIsWrong() {
			System.out.println("your guess is wrong");
		}

		public void printGuessIsRight() {
			System.out.println("your guess is right");
		}

		public void printGameStatus(int triesLeft, String wrongGuessesString,
				String markedString) {
			System.out.println("you have "+triesLeft+" guesses left");
			System.out.println("Wrong guesses: "+wrongGuessesString);
			System.out.println("Word "+markedString);
			
		}
}
