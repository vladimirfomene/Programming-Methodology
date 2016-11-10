/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		scores = new int[nPlayers][N_CATEGORIES];
		playGame();
	}
	
	/*
	 * The method playGame calls
	 * playYahtzee to start playing the game.
	 */
	private void playGame() {
		playYahtzee();
	}
	
	private void playYahtzee(){
		for(int i = 0; i <  N_SCORING_CATEGORIES; i++){
			playRound();
		}
		calculatePlayersScores();
		declareWinner();
	}
	
	/*
	 * Calculates lower scores, upper scores, upper bonuses and total for all players
	 */
	private void calculatePlayersScores(){
		for(int i = 0; i < nPlayers; i++){
			scores[nPlayers][UPPER_SCORE] = computeUpperScore(i + 1);
			scores[nPlayers][UPPER_BONUS] = awardUpperBonus(i + 1, scores[nPlayers][UPPER_SCORE]);
			scores[nPlayers][LOWER_SCORE] = computeLowerScore(i + 1);
			scores[nPlayers][TOTAL] = computeTotalScore(i + 1);
		}
		
	}
	
	/*
	 * Return the upper score of a particular player
	 */
	private int computeUpperScore(int playerIndex){
		int result = 0;
		for(int i = 0; i < UPPER_SCORE; i++){
			result += scores[playerIndex - 1][i];
		}
		return result;
	}
	
	/*
	 * Computes and return our upper bonus based on the upper score.
	 */
	private int awardUpperBonus(int playerIndex, int upperScore){
		int result = 0;
		if(upperScore >= 63){
			result = 35;
		}
		return result;
	}
	
	/*
	 * Computes the lower score and returns it
	 */
	private int computeLowerScore(int playerIndex){
		int result = 0;
		for(int i = THREE_OF_A_KIND; i < LOWER_SCORE; i++){
			result += scores[playerIndex - 1][i];
		}
		return result;
	}
	
	/*
	 * Computes the total score of a particular player
	 */
	private int computeTotalScore(int playerIndex){
		return scores[playerIndex - 1][UPPER_BONUS] + scores[playerIndex - 1][UPPER_SCORE] +
		scores[playerIndex - 1][LOWER_SCORE];
	}
	
	/*
	 * Prints out the winner with his total score.
	 */
	private void declareWinner(){
		int indexOfWinner = getWinnerIndex();
		display.printMessage("Congratulations, " + playerNames[indexOfWinner] + "you are the winner with a score of " +
					scores[indexOfWinner][TOTAL]);
		
	}
	
	/*
	 * returns the index of the player's score with the max total
	 */
	private int getWinnerIndex(){
		int container = scores[0][TOTAL];
		int index = 0;
		for(int i = 1; i < nPlayers; i++){
			if(scores[i][TOTAL] > container){
				container = scores[i][TOTAL];
				index = i;
			}
		}
		return index;
	}
	
	/*
	 *  Play round calls each player and then
	 *  demands that he or she play their turn
	 */
	private void playRound(){
		
		//A round is made up of each player's turn
		for(int i = 0; i < nPlayers; i++){
			playYourTurn(i + 1); //Player indexes start at 1
		}
	}
	
	/*
	 * Allows a particular player to play his turn.
	 */
	private void playYourTurn(int playerIndex){
		for(int i = 0; i < NUM_ROLL_PER_PLAYER; i++){
			
			//for first roll
			if(i == 0){
				display.printMessage(playerNames[playerIndex - 1] + ", it's your turn. Click \"roll dice\" button to roll the dice.");
				display.waitForPlayerToClickRoll(playerIndex);
				
				//Randomly rolling the dice
				rollDice();
				
				//Display dice
				display.displayDice(dice);
				
			}else {
				//Inform the player to select dice and roll again
				display.printMessage("Select the dice you which to re-roll, and click \" Roll again\"");
				
				//For second and third roll
				display.waitForPlayerToSelectDice();
				
					
				//Roll dices that have been selected
				int diceToReRoll = reRollDice();
				
				if(diceToReRoll == 0) break;
					
				//Display dice
				display.displayDice(dice);
				
			}
			
			
			
		}
		display.printMessage("Select a category that corresponds to your dice configuration.");
		checkCategoryUpdateScoreCard(playerIndex);
	}
	
	
	private void checkCategoryUpdateScoreCard(int playerIndex){
		
		int category;
		boolean checkedCategory = false;
		
		//Check if that category is available and then check if the dice configuration matches that category
		do{
			category = display.waitForPlayerToSelectCategory();
		} while(!availableCategory[category]);
		
		//Mark that category as chosen and check if dice configuration fits category
		availableCategory[category] = true;
		checkedCategory = YahtzeeMagicStub.checkCategory(dice, category);
		
		
		//updateScoreCard
		if(checkedCategory){
			//compute value, write a method to compute score based on category
			int score = computeCategoryValue(category);
			
			//update score array
			scores[playerIndex - 1][category] = score;
			
			//updateScoreCard
			display.updateScorecard(category, playerIndex, score);
		}else{
			//Score in case the dice configuration does not fit the chosen category
			int score = 0;
			
			//update score array
			scores[playerIndex - 1][category] = score;
			
			//updateScoreCard
			display.updateScorecard(category, playerIndex, score);
		}
		
	}
	
	private int reRollDice(){
		int diceReRolled = 0;
		for(int k = 0; k < N_DICE; k++){
			if(display.isDieSelected(k)){
				
				diceReRolled++;
				
				//Roll dice
				dice[k] = rgen.nextInt(1,6);
					
			}
		}
		return diceReRolled;
	}
	
	private void rollDice(){
		for(int j = 0; j < N_DICE; j++){
			dice[j] = rgen.nextInt(1, 6);
		}
	}
	
	/*
	 * Takes in a category and computes its score value
	 */
	private int computeCategoryValue(int category){
		int result = 0;
		switch(category){
			case ONES:
				result = computeScoreForMultipleNumbers(category);
			case TWOS:
				result = computeScoreForMultipleNumbers(category);
			case THREES:
				result = computeScoreForMultipleNumbers(category);
			case FOURS:
				result = computeScoreForMultipleNumbers(category);
			case FIVES:
				result = computeScoreForMultipleNumbers(category);
			case SIXES:
				result = computeScoreForMultipleNumbers(category);
			case THREE_OF_A_KIND:
				result = computeNumberOfKind(category);
			case FOUR_OF_A_KIND:
				result = computeNumberOfKind(category);
			case FULL_HOUSE:
				result = computeFullHouse();
			case SMALL_STRAIGHT:
				result = computeSmallStraight();
			case LARGE_STRAIGHT:
				result = computeLargeStraight();
			case YAHTZEE:
				result = computeYahtzee();
			case CHANCE:
				result = computeChance();
		}
		return result;
	}
	
	/*
	 * This methods computes the score for categories like ones, twos, threes...etc
	 */
	private int computeScoreForMultipleNumbers(int number){
		int result = 0;
		for(int i = 0; i < dice.length; i++){
			if(dice[i] == number) result += number;
		}
		return result;
	}
	
	/*
	 * This methods take the category and returns you the score corresponding to that category
	 */
	private int computeNumberOfKind(int category){
		int result = 0;
		for(int i = 0; i < dice.length; i++) result += category;
		return result;
	}
	
	/*
	 * Returns the score corresponding to the full house
	 */
	private int computeFullHouse(){
		return 25;
	}
	
	/*
	 * Returns the score corresponding to the small straight
	 */
	private int computeSmallStraight(){
		return 30;
	}
	
	/*
	 * Returns the score corresponding to the large straight
	 */
	private int computeLargeStraight(){
		return 40;
	}
	
	/*
	 * Returns the score corresponding to yahtzee category
	 */
	private int computeYahtzee(){
		return 50;
	}
	
	/*
	 * Returns the score corresponding to the chance category
	 */
	private int computeChance(){
		int result = 0;
		for(int i = 0; i < dice.length; i++){
			result += dice[i];
		}
		return result;
	}
	
	/*
	 * check whether a dice configuration corresponds to a particular category.
	 */
	private boolean checkCategory(int[] dice, int category){
	    boolean isValid = false;
		switch(category){
		case THREE_OF_A_KIND:
			isValid = isThreeOfAKind();
		case FOUR_OF_A_KIND:
			isValid = isFourOfAKind();
		case FULL_HOUSE:
			isValid = isFullHouse();
		case YAHTZEE:
			isValid = isYahtzee();
		case SMALL_STRAIGHT:
			isValid = isSmallStraight();
		case LARGE_STRAIGHT:
			isValid = isLargeStraight();
		default:
			isValid = true;
		}
		return isValid;
	}
	
	
	/*
	 * This methods checks to see whether you have a large straight or small straight 
	 * based on the argument start
	 */
	private boolean abstractStraight(int start){
		int test = start;
		boolean isStraight = true;
		
		for(int i = 0; i < dice.length; i++){
			
			if(test != dice[i]){
				isStraight = false;
				break;
			} else {
				test++;
			}
			
			
		}
		return isStraight;
	}
	
	/*
	 * Checks dice configuration against small straight
	 */
	private boolean isSmallStraight(){
		return abstractStraight(2);
	}
	
	/*
	 * checks dice configuration against large straight
	 */
	private boolean isLargeStraight(){
		return abstractStraight(1);
	}
	
	/*
	 * Checks dice configuration for numbers of same kind
	 */
	private boolean sameKind(int frequency){
		
		boolean isSameKind = false;
		
		for(int j = 0; j < dice.length; j++){
			int test = dice[j];
			int numberFrequency = 0;
			
			for(int i = 0; i < dice.length; i++){
				if(test == dice[i]){
					numberFrequency++;
				}
			}
			
			if(numberFrequency == frequency){
				isSameKind = true;
				break;
			}
		}
		return isSameKind;
	}
	
	/*
	 * Checks for three of a kind in a dice configuration
	 */
	private boolean isThreeOfAKind(){
		return sameKind(3);
	}
	
	/*
	 * Checks for three of a kind in a dice configuration
	 */
	private boolean isFourOfAKind(){
		return sameKind(4);
	}
	
	/*
	 * Checks for yahtzee in a dice configuration
	 */
	private boolean isYahtzee(){
		return sameKind(5);
	}
	
	/*
	 * Checks for yahtzee in a dice configuration
	 */
	private boolean isFullHouse(){
		
		//Check for three of a kind 
		//Then check for two of a kind
		return sameKind(3) && sameKind(2);
	}
	
/* Private instance variables */
	private boolean[] availableCategory = new boolean[N_SCORING_CATEGORIES];
	private int[][] scores;
	private int[] dice = new int[N_DICE];
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
