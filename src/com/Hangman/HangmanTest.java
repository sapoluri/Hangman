package com.Hangman;

import java.util.Vector;

import junit.framework.TestCase;

public class HangmanTest extends TestCase 
{
	Hangman game;	
	
	protected void setUp()
	{
		game = new Hangman();		
	}

	public void testCanary()
	{
		assertTrue(true);
	}
	
	public void testChooseAWordFromList()
	{
		Vector<String> wordList = new Vector<String>();
		wordList.add("word");
		wordList.add("solid");
		wordList.add("wood");
		game.chooseAWordFromList(wordList);
		assertTrue(game.getChosenWord().length() > 0);
	}
	
	public void testTriesLeftForWrongGuess()
	{
		game.setChosenWord("word");
		int triesLeftBeforeGuess = game.getTriesLeft();
		game.makeAGuess('a');
		assertTrue((triesLeftBeforeGuess - game.getTriesLeft()) == 1);		
	}
	
	public void testTriesLeftForRightGuess()
	{
		game.setChosenWord("word");
		int triesLeftBeforeGuess = game.getTriesLeft();
		game.makeAGuess('d');
		assertTrue((triesLeftBeforeGuess - game.getTriesLeft()) == 0);		
	}
	
	public void testGuessIsWrong()
	{
		game.setChosenWord("word");
		assertTrue(GuessType.Wrong == game.makeAGuess('a'));
	}
	
	public void testGuessIsRight()
	{
		game.setChosenWord("word");
		assertTrue(GuessType.Right == game.makeAGuess('o'));
	}
	
	public void testMarkCorrectGuess()
	{
		game.setChosenWord("wood");		
		game.makeAGuess('o') ;
		assertEquals(2, game.getNumberOfUnguessedLetters());
	}
	
	public void testStoreWrongLetterGuesses()
	{
		char guessLetter = 'a';
		game.setChosenWord("wood");		
		game.makeAGuess(guessLetter);
		assertTrue(game.getWrongGuessesString().contains(Character.toString(guessLetter)));
	}
	
	public void testIsLetterAlreadyGuessed()
	{
		game.setChosenWord("wood");
		char guessLetter = 'o';
		game.makeAGuess(guessLetter);
		assertEquals(GuessType.AlreadyGuessed, game.makeAGuess(guessLetter));
	}
	
	public void testIsGameWon()
	{
		String guessedWord = "wood";
		game.setChosenWord("wood");
		
		for(char guessLetter:guessedWord.toCharArray())
		{
			game.makeAGuess(guessLetter);					
			if(GameState.Running != game.isGameOver())
				break;
		}
		
		assertEquals(GameState.Won, game.isGameOver());
	}
	
	public void testIsGameLost()
	{
		String guessedWord = "abcefghijk";
		game.setChosenWord("wood");
		for(char guessLetter:guessedWord.toCharArray())
		{
			game.makeAGuess(guessLetter);		
			if(GameState.Running != game.isGameOver())
				break;
		}
		assertEquals(GameState.Lost, game.isGameOver());	
	}
	
	public void testIsGameRunning()
	{
		String guessedWord = "aboc";
		game.setChosenWord("wood");
		for(char guessLetter:guessedWord.toCharArray())
			game.makeAGuess(guessLetter);
		assertEquals(GameState.Running, game.isGameOver());
	}
	
}
