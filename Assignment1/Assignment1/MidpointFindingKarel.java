/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// You fill in this part
	public void run(){
		moveToMidpoint();
	}
	
	private void moveToMidpoint(){
		
		while(frontIsClear()){
			move();
			if(frontIsClear()){
				move();
				putBeeper();
				goAndDropBeeper();
				moveToWhereKarelStopped();
			}else{
				putBeeper();
				goAndDropBeeper();
				moveToWhereKarelStopped();
			}
			
			
		}
		collectBeepers();
		runToMidpoint();
	}
	
	private void goAndDropBeeper(){
		moveToWall();
		putBeeper();

		
	}
	
	private void moveToWhereKarelStopped(){
		turnAround();
		move();
		while(noBeepersPresent()){
			move();
		}
		pickBeeper();
	}
	
	private void collectBeepers(){
		moveToWall();
		while(beepersPresent()){
			pickBeeper();
			turnAround();
			if(noBeepersPresent()){
				move();
			}
			while(beepersPresent()){
				move();
			}
			putBeeper();
			moveToWall();
		}
	}
	
	private void runToMidpoint(){
		turnAround();
		move();
		while(beepersPresent()){
			pickBeeper();
			move();
		}
		
		turnAround();
		move();
		putBeeper();
		turnAround();
	}
	
	private void moveToWall(){
		turnAround();
		while(frontIsClear()){
			move();
		}
	
	}

}
