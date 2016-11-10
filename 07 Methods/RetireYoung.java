/*
 * File: RetireYoung.java
 * =======================================================
 * A program that will let us retire young (?)
 */
import acm.program.*;

public class RetireYoung extends ConsoleProgram {
	public void run() {
		for (int i = 10; i > 0; i--) {
			println(i);
		}
		
		for (int i = 0; i < 10; i++) {
			println(i);
		}
		
		/* Start with only $137 to my name. */
		int accountBalance = 137;
		println("I used to have $" + 137);
		
		/* Get rich quick with computer science! */
		getRichQuick(accountBalance);
		println("Now I have $" + accountBalance);
	}
	
	/**
	 * A method that (allegedly!) makes us have ten million
	 * dollars!
	 * 
	 * @param money Your initial money.
	 */
	private void getRichQuick(int money) {
		/* Now I have ten million dollars! */
		money = 10000000;
	}
}
