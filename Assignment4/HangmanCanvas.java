/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	/* Private instance variables */
	private int numberOfIncorrectGuesses = 0;
	private String incorrectWord = "";
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* remove all GObjects on canvas */
		removeAll();
		/* Draws Scaffold */
		drawScaffold();
	}
	
	/**
	 * 
	 * Methods Draws hangman's scaffold
	 */
	private void drawScaffold(){
		GLine scaffold = new GLine(getWidth()/2 - BEAM_LENGTH, (50 + SCAFFOLD_HEIGHT), getWidth()/2 - BEAM_LENGTH, 50);
		GLine beam = new GLine(getWidth()/2 - BEAM_LENGTH, 50, getWidth()/2, 50);
		GLine rope = new GLine(getWidth()/2, 50, getWidth()/ 2, 50 + ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		//Get element at this location before printing label
		GObject gObj = getElementAt(50, 350);
		
		//Clear position if there is an element there
		if(gObj != null){
			GLabel prevLabel = (GLabel) gObj;
			remove(prevLabel);
		}
		GLabel trackedWord = new GLabel(word, 50, 350);
		add(trackedWord);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		numberOfIncorrectGuesses++;
		hangOnePartOfTheBody(numberOfIncorrectGuesses);
		incorrectWord += Character.toString(letter);
		printIncorrectWord(incorrectWord);
	}
	
	/**
	 * 
	 * @param word
	 */
	private void printIncorrectWord(String word){
		
		//Get element at this location
		GObject gObj = getElementAt(50, 400);
		
		//clear location if an element exist there.
		if(gObj != null){
			GLabel prevLabel = (GLabel) gObj;
			remove(prevLabel);
		}
		//create GLabel and add to screen.
		GLabel incorrectWord = new GLabel(word, 50, 400);
		add(incorrectWord);
	}
	
	/**
	 * 
	 * @param numberOfIncorrectGuesses
	 */
	private void hangOnePartOfTheBody(int numberOfIncorrectGuesses){
		switch(numberOfIncorrectGuesses){
			case 1:
				drawHead();
				break;
			case 2:
				drawBody();
				break;
			case 3:
				drawLeftArm();
				break;
			case 4:
				drawRightArm();
				break;
			case 5:
				drawLeftLeg();
				break;
			case 6:
				drawRightLeg();
				break;
			case 7:
				drawLeftFoot();
				break;
			case 8:
				drawRightFoot();
				break;
			default:
				break;
		}
	}
	
	/**
	 * draw the head of hangman
	 */
	private void drawHead(){
		GOval head = new GOval((getWidth()/2) - HEAD_RADIUS, 50 + ROPE_LENGTH, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(head);
	}
	
	/**
	 * Draws the body of hangman
	 */
	private void drawBody(){
		GLine body = new GLine(getWidth()/ 2, (50 + ROPE_LENGTH + 2 * HEAD_RADIUS), getWidth() /2 , (50 + ROPE_LENGTH + 2 * HEAD_RADIUS) + BODY_LENGTH );
		add(body);
	}
	
	/**
	 * Draws the left Arm of hangman
	 */
	private void drawLeftArm(){
		GLine lowerLeftArm = new GLine((getWidth() / 2) - HEAD_RADIUS - UPPER_ARM_LENGTH, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD,
				(getWidth() / 2) - HEAD_RADIUS - UPPER_ARM_LENGTH, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		GLine upperLeftArm = new GLine((getWidth() / 2) - HEAD_RADIUS - UPPER_ARM_LENGTH, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD, 
		getWidth() / 2, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD);
		add(lowerLeftArm);
		add(upperLeftArm);
	}
	
	/**
	 * Draws the right Arm of hangman
	 */
	private void drawRightArm(){
		GLine upperRightArm = new GLine((getWidth() / 2), 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD,
				(getWidth() / 2) + HEAD_RADIUS + UPPER_ARM_LENGTH, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD);
		GLine lowerLeftArm = new GLine((getWidth() / 2) + HEAD_RADIUS + UPPER_ARM_LENGTH, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD, 
				(getWidth() / 2) + HEAD_RADIUS + UPPER_ARM_LENGTH, 50 + ROPE_LENGTH + (HEAD_RADIUS * 2) + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(upperRightArm);
		add(lowerLeftArm);
	}
	
	/**
	 * Draws the left leg of hangman
	 */
	private void drawLeftLeg(){
		GLine hip = new GLine(getWidth() / 2, 50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH, getWidth() / 2 - (HIP_WIDTH / 2),
				50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH);
		GLine leftLeg = new GLine(getWidth() / 2 - (HIP_WIDTH / 2), 50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH, getWidth() / 2 - (HIP_WIDTH / 2),
				50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH + LEG_LENGTH);
		add(hip);
		add(leftLeg);
	}
	
	/**
	 * Draws the right leg of hangman
	 */
	private void drawRightLeg(){
		GLine hip = new GLine(getWidth() / 2, 50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH, getWidth() / 2 + (HIP_WIDTH / 2),
				50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH);
		GLine rightLeg = new GLine(getWidth() / 2 + (HIP_WIDTH / 2), 50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH, getWidth() / 2 + (HIP_WIDTH / 2),
				50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH + LEG_LENGTH);
		add(hip);
		add(rightLeg);
		
	}
	
	/**
	 * Draws hangman's left foot
	 */
	private void drawLeftFoot(){
		GLine leftFoot = new GLine(getWidth() / 2 - (HIP_WIDTH / 2), 50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH + LEG_LENGTH, getWidth() / 2 - (HIP_WIDTH / 2) - FOOT_LENGTH,
				50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH + LEG_LENGTH);
		add(leftFoot);
	}
	
	/**
	 * Draws hangman's right foot
	 */
	private void drawRightFoot(){
		GLine rightFoot = new GLine(getWidth() / 2 + (HIP_WIDTH / 2), 50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH + LEG_LENGTH, getWidth() / 2 + (HIP_WIDTH / 2) + FOOT_LENGTH,
				50 + ROPE_LENGTH + (2 * HEAD_RADIUS)+ BODY_LENGTH + LEG_LENGTH);
		add(rightFoot);
	}
	

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}
