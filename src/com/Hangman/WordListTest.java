package com.Hangman;

import java.util.Vector;

import junit.framework.TestCase;

public class WordListTest extends TestCase 
{

	WordList list;
	protected void setUp()
	{
		list = new WordList();		
	}
	
	public void testLoadWordListSuccessful()
	{
		Vector<String> wordList = list.loadWordList();
		assertTrue(wordList.size() > 0);
	}

}
