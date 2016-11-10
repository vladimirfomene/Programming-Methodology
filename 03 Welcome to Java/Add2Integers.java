/*
 * File: Add2Integers.java
 * -----------------------
 * A simple ConsoleProgram to add two integers
 * and display their total.
 */

import acm.program.*;

public class Add2Integers extends ConsoleProgram {
	
   public void run() {
      println("This program adds two numbers.");
      int n1 = readInt("Enter n1: ");
      int n2 = readInt("Enter n2: ");
      int total = n1 + n2;
      println("The total is " + total + ".");
   }
   
}

