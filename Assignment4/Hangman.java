/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	/* Number of Guesses in Hangman */
	private static final int NO_OF_GUESSES = 8;
	
    public void run() {

    	createTrackedWord();
    	canvas.reset();
    	println("Welcome to Hangman!");
    	while(gameIsNotOver()){

    
    		println("The word now looks like this: " + getTrackedWord());
        	println("You have " + (NO_OF_GUESSES - trackWrongGuesses) + " guesses left.");
        	guess = getUserInput();
        	
        	//Keep track of the word if correct
        	if(isGuessInWord(guess) != -1){
        		trackingWord(guess); 
        	}else{
        		trackWrongGuesses++; //increment number of wrong guesses
        		canvas.noteIncorrectGuess(guess);
        	}
        	canvas.displayWord(this.getTrackedWord());
        	
    	}
    	printOutcome();
	}
    
    /**
     * Initialize the graphics part of the program.
     */
    public void init(){
    	canvas = new HangmanCanvas();
    	add(canvas);
    }
    /**
     * Gets character input from user via the console
     * and prompt the user in case of any error.
     * @return ch character entered by the user
     */
    private char getUserInput(){
    	char ch;
    	while(true){
    		String input = readLine("Your Guess: ");
    		if(input.equals("")){
    			println("Please enter a valid string!");
    			continue;
    		}
    		ch = input.charAt(0);
    		if(Character.isLetter(ch)) break;
    		println("Your guess is Illegal. Try again.");
    	}
		return ch;
    }
    
    private void printOutcome(){
    	if(won){
    		println("You guessed the word:"+ secretWord);
    		println("You win.");
    	}else{
    		println("You're completely hung.");
    		println("The word was:" + secretWord);
    		println("You lose");
    	}
    }
    
    /**
     * return index of character in guessed word
     * @param ch guessed character
     * @return index at which you will find the guess character.
     */
    private int isGuessInWord(char ch){
    	ch = Character.toUpperCase(ch);
		return secretWord.indexOf(ch);
		
    }
    
    private boolean gameIsNotOver(){
    	if(getTrackedWord().equalsIgnoreCase(secretWord)){
    		won = true;
    		return false;
    	}else if((trackWrongGuesses == NO_OF_GUESSES) && (!getTrackedWord().equalsIgnoreCase(secretWord))){
    		return false;
    	}else{
    		return true;
    	}
    	
    }
    
    /**
     * update the character list of tracked word.
     * @param ch new correct guess
     */
    private void trackingWord(char ch){
    	
    	for(int i = 0; i < secretWord.length(); i++){
    		if(Character.toUpperCase(ch) == secretWord.charAt(i)){
    			charList.set(i, ch);
    		}
    	}	
    }
    
    /**
     * Initialize a character arraylist with the list of all
     * the characters in a word.
     */
    private void createTrackedWord(){
    	
    	//Initializes the arraylist of tracked character
    	charList = new ArrayList<Character>();
   
    	for(int i = 0; i < secretWord.length(); i++){
    		charList.add('-');
    	}
    }
    
    /**
     *  
     */
    private String getTrackedWord(){
    	String result = "";
    	for(int i = 0; i < charList.size(); i++){
    		char ch = charList.get(i);
    		result += Character.toString(ch);
    	}
    	return result;
    }
    
    /* Private instances */
    private HangmanCanvas canvas;
    private boolean won = false;
    private ArrayList<Character> charList;
    private char guess;
    private HangmanLexicon wordList = new HangmanLexicon();
    private int trackWrongGuesses = 0;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private String secretWord = wordList.getWord(rgen.nextInt(wordList.getWordCount()));

}
