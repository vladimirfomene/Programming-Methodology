/*
 * File: Countdown.java
 * ========================================================
 * A program that simulates a launch countdown.
 */
import acm.program.*;

public class Countdown extends ConsoleProgram {
	/* Number of seconds to count down. */
	private static final int COUNTDOWN_START = 20;
	
	/* When guidance becomes internal. */
	private static final int GUIDANCE_START = 15;
	
	/* When the ignition sequence starts. */
	private static final int IGNITION_START = 9;
	
	public void run() {		
		/* Do the launch countdown! */
		for (int i = COUNTDOWN_START; i > 0; i--) {
			println("T-" + i + " seconds.");
			
			/* Specific mission commands. */
			if (i == GUIDANCE_START) {
				println("Guidance is internal.");
			}
			if (i == IGNITION_START) {
				println("Ignition sequence start.");
			}
			
			/* Wait for one whole second. */
			pause(1000);
		}
		
		println("All engines running. Lift-off!");
	}
}
