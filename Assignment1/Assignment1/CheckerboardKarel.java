/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run(){
        //create a first checket row
		createCheckerRow();
		
		//create checker rows while not yet at ceiling
		while(leftIsClear()){
			adjustAtRightWall();
			createCheckerRow();	
			
			if(rightIsClear()){
				adjustAtLeftWall();
				createCheckerRow();
			}else{
				//At ceiling turn around for loop exit
				turnAround();
			}

		}
		turnLeft();
	}
	
	/*
	 * create a checker row
	 */
	private void createCheckerRow(){
		if(noBeepersPresent()){
			putBeeper();
		}
		
		while(frontIsClear()){
			
            if(frontIsClear()){
				move();
			}
			
            if(frontIsClear()){
            	move();
            	putBeeper();
            }
		}
	}
	
	/*
	 * Position karel to create checker on next row when at left
	 * wall of the world
	 */
	private void adjustAtLeftWall(){
		turnRight();
		move();
		turnLeft();
		turnAround();
	}
	
	/*
	 * Position karel to create checker on next row when at left
	 */
	private void adjustAtRightWall(){
		turnLeft();
		move();
		turnRight();
		turnAround();
	}
	
	
}

