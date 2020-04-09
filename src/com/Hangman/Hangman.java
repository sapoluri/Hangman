package com.Hangman;

import java.util.Random;
import java.util.Vector;

enum GuessType 
{	
	Wrong ("Guess is wrong"), Right ("Guess is right"), AlreadyGuessed ("Letter already guessed");

	String _message;
	
	GuessType(String message)
	{
		_message=message;
	}
	
	public String getMessage()
	{
		return _message;	
	}

}

enum GameState 
{	
	Running (""), Lost ("Sorry. You lost."), Won ("Congratulations! You won.");
	
	String _gameOverMessage;
	
	GameState(String message)
	{
		_gameOverMessage = message;
	}
	
	public String getMessage()
	{
		return _gameOverMessage;
	}	

}

class Hangman
{
	private String chosenWord = "";
	private int maxTries = 10;
	private int triesLeft = maxTries;
	StringBuffer markedString = new StringBuffer();
	StringBuffer wrongGuessString = new StringBuffer();
	
	public void chooseAWordFromList(Vector<String> wordList) 
	{
		Random randomGenerator = new Random();
		setChosenWord(wordList.elementAt(randomGenerator.nextInt(wordList.size())).toLowerCase());
	}
	
	public void setChosenWord(String setWord)
	{
		chosenWord = setWord;
		
		for(char letter : chosenWord.toCharArray())
			markedString.append('_');
	}

	public String getChosenWord() 
	{		
		return chosenWord;
	}

	public GuessType makeAGuess(char guessLetter) 
	{
		guessLetter = Character.toLowerCase(guessLetter);
		
		if(IsLetterAlreadyGuessed(guessLetter))
		{
			return GuessType.AlreadyGuessed;
		}
		else
		{
			if(chosenWord.contains(Character.toString(guessLetter)))
			{
				markCorrectGuess(guessLetter);
				return GuessType.Right;
			}
			else
			{
				triesLeft = triesLeft-1;
				storeWrongLetterGuess(guessLetter);
				return GuessType.Wrong;				
			}
		}		
	}

	protected void markCorrectGuess(char markGuessLetter) 
	{
		int i = 0;
		
		for(char guessLetter : chosenWord.toCharArray())
		{
			if(guessLetter == markGuessLetter)
				markedString.setCharAt(i, markGuessLetter);
			
			i++;
		}
	}

	protected String getWrongGuessesString() 
	{
		return wrongGuessString.toString();
	}

	protected void storeWrongLetterGuess(char wrongGuess)
	{
		wrongGuessString.append(wrongGuess);
	}

	protected boolean IsLetterAlreadyGuessed(char guessedLetter) 
	{
		if(markedString.toString().contains(Character.toString(guessedLetter)))
			return true;	
		else
		{
			if(wrongGuessString.toString().contains(Character.toString(guessedLetter)))
				return true;
		}
		
		return false;
	}

	public int getNumberOfUnguessedLetters() 
	{
		int numberOfUnguessedLetters = 0;
		
		for(char guessLetter:markedString.toString().toCharArray())
		{
			if(guessLetter == '_')
				numberOfUnguessedLetters++;
		}
		
		return numberOfUnguessedLetters;
	}

	public GameState isGameOver() 
	{
		String finalString = new String(markedString);
		
		if(finalString.equals(chosenWord))
			return GameState.Won;
		else if(triesLeft == 0)
			return GameState.Lost;
		
		return GameState.Running;
	}
	
	public int getTriesLeft()
	{
		return triesLeft;
	}
	
	public String getMarkedString()
	{
		return markedString.toString();
	}
	
}