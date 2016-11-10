/*
 * File: SeeingStars.java
 * =============================================================
 * A program that draws pretty stars on the screen!
 */
import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class SeeingStars extends GraphicsProgram {
	/* Constants controlling the location of the star. */
	private static final double STAR_CENTER_X = 200;
	private static final double STAR_CENTER_Y = 200;
	
	/* Constants controlling the location of the star. */
	private static final double SECOND_STAR_CENTER_X = 450;
	private static final double SECOND_STAR_CENTER_Y = 200;
	
	/* Constant controlling the radius of the star. */
	private static final double STAR_RADIUS = 100;
	
	/* Constant controlling how many points the star has. */
	private static final int NUM_STAR_POINTS = 7;
	
	private void drawStar(double x, double y, int numPoints) {
		for (int i = 0; i < numPoints; i++) {
			/* Compute the angles of the current point and the next point. */
			double theta     =  i      * 2 * Math.PI / numPoints;
			double nextTheta = (i + 2) * 2 * Math.PI / numPoints;
			
			/* Construct a line between those points. */
			GLine line = new GLine(x + STAR_RADIUS * Math.cos(theta),
					               y - STAR_RADIUS * Math.sin(theta),
					               x + STAR_RADIUS * Math.cos(nextTheta),
					               y - STAR_RADIUS * Math.sin(nextTheta));
			
			/* Update the line color. */
			line.setColor(Color.BLUE);
			
			/* Display the line. */
			add(line);			
		}
	}
	
	public void run() {
		drawStar(STAR_CENTER_X, STAR_CENTER_Y, 7);
		drawStar(SECOND_STAR_CENTER_X, SECOND_STAR_CENTER_Y, 8);
	}
}
