/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run(){
		
		moveToBeeper(); 
		pickBeeper();
		returnToStartingPoint();
	}
	
	/*
	 *  Takes Karel to the corner
	 *  where the beeper is  
	 */
	private void moveToBeeper(){
		move();
		move();
		turnRight();
		move();
		turnLeft();
		move();
	}
	
	/*
	 *  Take Karel back to the starting 
	 *  Position
	 */
	
	private void returnToStartingPoint(){
		turnAround();
		move();
		move();
		move();
		turnRight();
		move();
		turnLeft();
		turnAround();
	}
		

}
