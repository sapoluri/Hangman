package com.Hangman;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

@SuppressWarnings("serial")

public class UI extends Frame 
{	
	String wordLabel = "Word:";
	String wrongGuessLabel = "Wrong Guesses:";
	Shape hangmanShapes[] = {new Rectangle2D.Float(75, 350,100, 100), new Line2D.Float(125.0f,50.0f,125.0f,350.0f), new Line2D.Float(125.0f,50.0f,225.0f,50.0f), new Line2D.Float(225.f,50.0f,225.0f,100.0f), new Ellipse2D.Float(200.0f, 100.0f, 50.0f, 50.0f), new Line2D.Float(225.0f,150.0f,225.0f,250.0f), new Line2D.Float(225.0f,175.0f,175.0f,150.0f), new Line2D.Float(225.0f,175.0f,275.0f,150.0f), new Line2D.Float(225.0f,250.0f,175.0f,300.0f), new Line2D.Float(225.0f,250.0f,275.0f,300.0f)};
	String gameOverLabel;
	String _guessMessage;
	Hangman hangmanGame = new Hangman();
	int maxTries = hangmanGame.getTriesLeft();
	WordList loadList = new WordList();

	public void paint(Graphics g)
	{		
		Graphics2D ga = (Graphics2D)g;
		for(int i = 0; i < maxTries-hangmanGame.getTriesLeft(); i++)
			ga.draw(hangmanShapes[i]);	  
	   	  
	  	Font fontStyle1 = new Font("Arial",Font.PLAIN,30);
	    ga.setFont(fontStyle1);
	    ga.setColor(Color.green);
	    ga.drawChars(wordLabel.toCharArray(), 0, wordLabel.length(), 300, 200);
	    ga.drawChars(hangmanGame.getMarkedString().toCharArray(), 0, hangmanGame.getMarkedString().length(), 400, 200);
	    
	    Font fontStyle2 = new Font("Arial", Font.PLAIN, 20);
	    ga.setFont(fontStyle2);
	    ga.setColor(Color.red);
	    ga.drawChars(wrongGuessLabel.toCharArray(), 0, wrongGuessLabel.length(), 300, 100);
	    ga.drawChars(hangmanGame.getWrongGuessesString().toCharArray(), 0, hangmanGame.getWrongGuessesString().length(), 450, 100);
	    
	    Font fontStyle3 = new Font("Arial", Font.PLAIN, 20);
	    ga.setFont(fontStyle3);
	    ga.setColor(Color.black);
	    ga.drawChars(_guessMessage.toCharArray(), 0, _guessMessage.length(), 300, 250);    
	    
	    Font fontStyle4 = new Font("Arial", Font.PLAIN, 30);
	    ga.setFont(fontStyle4);
	    ga.setColor(Color.blue);
	    ga.drawChars(gameOverLabel.toCharArray(), 0, gameOverLabel.length(), 300, 350);
	}
	 
	public void startGame()
	{		
		hangmanGame.chooseAWordFromList(loadList.loadWordList());
		userInterfaceSetup();
		addKeyListener(new KeyHandler());
		update(getGraphics());
	}
	 
	public void userInterfaceSetup()
	{
		addWindowListener(new WindowAdapter()
		{ 
			public void windowClosing(WindowEvent we)
			{ 
				System.exit(0); 
			} 
		}
		);		   
		  
		    setSize(720, 480);
		    setVisible(true);		  
	  }

	public void showGameOver(String string) 
	{
		gameOverLabel = string;
	}

	public void showGuessMessage(String message) 
	{
		_guessMessage = message;
	}
	
	public class KeyHandler extends KeyAdapter
	{
		
		public void keyPressed(KeyEvent ke)
		{
			char readInLetter = ke.getKeyChar();
			
			if(hangmanGame.isGameOver() == GameState.Running)
	  	    {
				showGuessMessage(hangmanGame.makeAGuess(readInLetter).getMessage());	  
	  	    	showGameOver(hangmanGame.isGameOver().getMessage());		  	  
	  	    	update(getGraphics());
	  	    }  	      	  	  
		}		
		
	}
	
	public static void main(String args[]) 
	{
		UI game = new UI();
		game.startGame();
	}
	
}