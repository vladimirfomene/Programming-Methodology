/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		//Create an array to store the rank from the 11 decades
		rank = new int[NDECADES];
		//Get the name of our entry
		name = line.substring(0, line.indexOf(" "));
		//Cut out the name from the string
		line = line.substring(line.indexOf(" "));
		//read the ranks from the line
		StringTokenizer strTokens = new StringTokenizer(line);
		//counter for array of decades
		int counter = 0;
		while(strTokens.hasMoreTokens()){
			rank[counter++] = Integer.parseInt(strTokens.nextToken());
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		// return the entry's name
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		// return the ranking of a particular decade
		return rank[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// return string version of the entry
		return name + " " + Arrays.toString(rank);
	}
	
	/* Private instance variables */
	private int[] rank;
	private String name;
}

