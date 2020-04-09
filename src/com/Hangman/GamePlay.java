package com.Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay{
	
	Hangman hangmanGame = new Hangman();
	UI userinterface = new UI();
	
	public void startGame()
	{		
		//hangmanGame.chooseAWordFromList();
		userinterface.userInterfaceSetup();
		userinterface.addKeyListener(new KeyHandler());
		//userinterface.updateDisplay(hangmanGame.getMarkedString(), hangmanGame.getWrongGuessesString(),hangmanGame.getTriesLeft());
	}
	
	public class KeyHandler implements KeyListener
	{
		public void keyPressed(KeyEvent ke){
			
  	      char readInLetter = ke.getKeyChar();
  	      
  	      if(hangmanGame.isGameOver() == GameState.Running)
  	      {
  	    	  GuessType guess = hangmanGame.makeAGuess(readInLetter);
  	    	  if(guess == GuessType.AlreadyGuessed)
  	    		  userinterface.showGuessMessage("Letter already guessed");	
  	    	  else if(guess == GuessType.Right)
  	    		userinterface.showGuessMessage("Guess is right");
  	    	  else if(guess == GuessType.Wrong)
  	    		userinterface.showGuessMessage("Guess is wrong");
  	      }
  	      if(hangmanGame.isGameOver() == GameState.Won)
  	      {
  	    	 //userinterface.gameOver("Congratulations! You won.");
  	      }
  	     // else if(hangmanGame.isGameOver() == GameState.Lost)
			//userinterface.gameOver("Sorry. You lost.");
  	      
  	    //userinterface.updateDisplay(hangmanGame.getMarkedString(), hangmanGame.getWrongGuessesString(), hangmanGame.getTriesLeft());
  	    }
		
		public void keyTyped(KeyEvent ke)
		{
			
		}
		
		public void keyReleased(KeyEvent ke)
		{
			
		}
		
	}
	
	/*public static void main(String args[]) {
		GamePlay game = new GamePlay();
		game.startGame();
		    
	  }*/

}
