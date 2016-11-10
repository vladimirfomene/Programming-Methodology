/*
 * File: BouncingBall.java
 * ========================================================
 * A program that simulates a bouncing ball.  Right now the
 * ball just falls, rather than bouncing.  Don't worry -
 * we'll fix that next time!
 */
import acm.program.*;
import acm.graphics.*;

import java.awt.*;

public class BouncingBall extends GraphicsProgram {
	/* The initial x velocity of the ball. */
	private static final double INITIAL_SPEED = 5.0;

	/* Gravitational acceleration. */
	private static final double GRAVITY = 0.5;

	/* How long to pause between frames. */
	private static final double PAUSE_TIME = 1000.0 / 24;
	
	public void run() {
		GOval ball = createBall();
		add(ball);
		moveBall(ball);
	}
	
	/**
	 * Creates the ball that will fall.
	 *
	 * @return The ball that falls.
	 */
	private GOval createBall() {
		GOval ball = new GOval(0, 0, 50, 50);
		ball.setFilled(true);
		ball.setColor(Color.BLUE);
		return ball;
	}
	
	/**
	 * Simulates the given ball falling.
	 *
	 * @param ball The ball to drop.
	 */
	private void moveBall(GOval ball) {
		double dx = INITIAL_SPEED;
		double dy = 0;
	
		/* While the ball hasn't yet hit the ground, keep pulling it down. */	
		while (ballAboveGround(ball)) {
			ball.move(dx, dy);
			dy += GRAVITY;
			
			pause(PAUSE_TIME);
		}
	}
	
	/**
	 * Returns whether the ball is still above ground.
	 *
	 * @param ball The ball to check.
	 * @return Whether that ball is above ground.
	 */
	private boolean ballAboveGround(GOval ball) {
		return ball.getY() < getHeight();
	}
}
