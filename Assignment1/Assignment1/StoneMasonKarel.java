/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run(){
		//execute the following while your front is clear
	    while(frontIsClear())
	    {
	    	//if you are the bottom repair north
	    	if(leftIsClear())
	    	{
	    		positionToRepairNorth();
	    		repairColumn();
	    		faceEastFromTop();
	    		jumpToNextColumn();
	    	} else {
	    		//Else repair south 
	    		positionToRepairSouth();
	    		repairColumn();
	    		faceEastFromBottom();
	    		jumpToNextColumn();
	    		
	    	}
	    }
	    
	    //Repairs the last column, takes care of the OBOB
	    if (leftIsClear()){
	    	positionToRepairNorth();
	    	repairColumn();
	    	faceEastFromTop();
	    } else {
	    	positionToRepairSouth();
	    	repairColumn();
	    	faceEastFromBottom();
	    }
	}
	
	/*	Pre-condition: Karel is facing East on an avenue with no beeper corners
	 * 	Post-condition: Karel is facing East but has filled all the corners with beepers
	 */
	private void repairColumn(){
		
		//check whether front is clear 
		while(frontIsClear()){
			
            //move or put a beeper when necessary
			if(beepersPresent()){
				move();
			} else{
				putBeeper();
				move();
			}
		}
	}
	/*
	 * Positions Karel to repair North
	 */
	private void positionToRepairNorth(){
		turnLeft();
	}
	
	/*
	 * Positions Karel to repair south
	 */
	private void positionToRepairSouth(){
		turnRight();
	}
	
	/*
	 * Positions Karel to face east from top
	 */
	private void faceEastFromTop(){
		turnRight();
	}
	/*
	 * Positions Karel to face east from bottom
	 */
	private void faceEastFromBottom(){
		turnLeft();
	}
	
	/*
	 * Move from one column to another
	 */
	private void jumpToNextColumn(){
		move();
		move();
		move();
		move();
	}
}
