/*
 * File: Factorial.java
 * ================================================================
 * A program that shows off computing factorials
 */
import acm.program.*;

public class Factorial extends ConsoleProgram {

	private static final int MAX_NUM = 15;
	
	public void run() {
		for (int i = 0; i < MAX_NUM; i++) {
			int result = factorial(i);
			println(i + "! = " + result);
		}
	}
	
	/**
	 * Computes num!.
	 *
	 * @param num The number whose factorial should be computed.
	 * @return num!
	 */
	private int factorial(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
	}
}
