/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.*;
import acm.util.*;
import java.util.*;

public class HangmanLexicon {
	
	/** Private instance variables */
	private ArrayList<String> lexiconList = new ArrayList<String>();
	
	// This is the HangmanLexicon constructor
	public HangmanLexicon() {
	// your initialization code goes here
		BufferedReader rd = openLexiconFile("HangmanLexicon.txt");
		readLexicons(rd);
	}
	
	/**
	 * Open the lexicon text file for reading.
	 * @return pointer to the file that has to read.
	 * @param file name of file to be read
	 */
	private BufferedReader openLexiconFile(String file){
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader(file));
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
		return rd;
		
	}
	
	private void readLexicons(BufferedReader rd){
		try{
			while(true){
				String line = rd.readLine();
				if(line == null) break;
				lexiconList.add(line);
			}
			rd.close();
		}catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lexiconList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lexiconList.get(index);
	}
}
