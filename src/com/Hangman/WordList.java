package com.Hangman;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

public class WordList 
{
	
	protected Vector<String> loadWordList() 
	{		
		Vector<String> wordList = new Vector<String>();
		
		try
		{
		    FileInputStream fstream = new FileInputStream("src/com/Hangman/textfile.txt");
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    String strLine;
		    	    	    	    
		    while (true) 
		    {		    	
		    	strLine = br.readLine();  
		    	
		    	if(strLine != null)
		    	{
		    		wordList.add(strLine);
		    	}
		    	else
		    	{
		    		break;
		    	}
		    }
		    
		    in.close();		    
		}
		catch (Exception e)
		{
		      System.err.println("Error: " + e.getMessage());
		}
		return wordList;
	}

}
