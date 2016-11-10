/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	/** Diameter of the ball in pixels */
	private static final int DIAM_BALL = BALL_RADIUS * 2;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** Amount Y velocity is increased each cycle as a
	 * result of gravity */
	 private static final double GRAVITY = 6;
	 
	 /** X Velocity */
	 private static final double Y_VEL = 3;
	 
	 /** Animation delay or pause time between ball moves */
	 private static final int DELAY = 50;
	
	

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
		setupBricks(NBRICKS_PER_ROW, NBRICK_ROWS);
		createPaddle(PADDLE_WIDTH, PADDLE_HEIGHT);
		createBall(BALL_RADIUS * 2, BALL_RADIUS * 2);
		addMouseListeners();
		
	}

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		
		setVelocity();
		while(!isGameOver()){
			moveBall();
			checkForCollisions();
			pause(DELAY);
		}
	}
	
	private void setupBricks(int numberOfBrickInRows, int numberOfRows){
		for(int i = 0; i < numberOfRows; i++ ){
			for(int j = 0; j < numberOfBrickInRows; j++){
				GRect brick = new GRect(rowStartX + (BRICK_WIDTH + BRICK_SEP) * j, BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP) * i, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				brick.setColor(returnColor(i));
				add(brick);
			}
		}

	}
	
	private void createPaddle(int width, int height){
		paddle = new GRect(startXPaddle, startYPaddle, width, height);
		paddle.setFilled(true);
		add(paddle);
	}
	
	private Color returnColor(int rowNumber){
		switch(rowNumber){
		case 0:
		case 1:
			return Color.RED;
		case 2: 
		case 3:
			return Color.ORANGE;
		case 4:
		case 5:
			return Color.YELLOW;
		case 6:
		case 7:
			return Color.GREEN;
		case 8:
		case 9:
			return Color.cyan;
		default:
			return Color.BLACK;
		}
	}
	

	
	/*
	 * Creates the ball on the centre of screen
	 */
	private void createBall(int width, int height){
		ball = new GOval((APPLICATION_WIDTH / 2) - BALL_RADIUS, (APPLICATION_HEIGHT / 2) - BALL_RADIUS, width, height);
		ball.setFilled(true);
		add(ball);
	}
	public void mouseMoved(MouseEvent e){
		
		/* The mouse tracks the middle point of the paddle. 
		 * If the middle point of the paddle is between half paddle width of the screen
		 * and half a paddle width before the end of the screen, 
		 * the x location of the paddle is set at where the mouse is minus half a paddle's width, 
		 * and the height remains the same
		 */
		if ((e.getX() < getWidth() - PADDLE_WIDTH/2) && (e.getX() > PADDLE_WIDTH/2)) {
			paddle.setLocation(e.getX() - PADDLE_WIDTH/2,  APPLICATION_HEIGHT - (PADDLE_HEIGHT + PADDLE_Y_OFFSET));
		}
		
		
	}
	
	/*
	 * move the ball using a random value for xVelocity and a constant for yVelocity
	 */
	private void setVelocity(){
		if(ball != null){
			vx = rgen.nextDouble(1.00, 3.00);
			if(rgen.nextBoolean(0.5)) vx = -vx;
			vy += GRAVITY;
			
		}
		
	}
	
	/*
	 * Check for collision between the wall and the ball
	 * and bounces the ball once it collides with the wall
	 */
	private void moveBall(){
		double diffY = 0; //Calculates the distance by which the ball exceeds the bases
		double diffX = 0; //Calculates the distance by which the ball exceeds the sides
		
		ball.move(vx, vy);
		if(ball != null){
			//check if the ball has collided with base
			if(ball.getY()== APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT){
				checkPaddleOrBrickCollision();
				
			//check if the ball has collided with bottom	
			}else if(ball.getY()> APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT){
				numberOfTurnsLeft--;
				remove(ball);
				ball = null;
				createBall(BALL_RADIUS * 2, BALL_RADIUS * 2);
			}else if( ball.getY() <= 0){
			
				vy = -vy; //reverse the vertical velocity
				
				/* calculates the y difference by which it exceeds the top */
				diffY = ball.getY();
			
			//check if the ball has collided with the right side
			}else if(ball.getX() > APPLICATION_WIDTH - DIAM_BALL){
				vx = -vx; //reverse the horizontal velocity
				
				/* calculates the x difference by which it exceeds the right */
				diffX = ball.getX() - (APPLICATION_WIDTH - DIAM_BALL);
				
			//check if the ball has collided with the left side
			}else if(ball.getX() <= 0){
				vx = -vx; //reverse the horizontal velocity
				
				/*Calculates the x difference by which it exceeds the left */
				diffX = ball.getX();
			}
			
			//move the ball by the calculated difference
			ball.move(-2 * diffX, -2 * diffY);
		}
		
	}
	
	/*
	 * Returns true if the game is over
	 */
	private boolean isGameOver(){
		 return ((numberOfTurnsLeft == 0 || brickCounter == 0));
		
	}
	
	/*
	 * 
	 */
	private GObject getCollidingObject(double xPosition, double yPosition){
		GObject collider = null;
		//get collider at upperleft corner
		if(getElementAt(xPosition, yPosition) != null){
			collider =  getElementAt(xPosition, yPosition);
			
		//get collider at lowerleft corner	
		}else if(getElementAt(xPosition, yPosition + (2 * BALL_RADIUS)) != null){
			collider = getElementAt(xPosition,  yPosition + (2 * BALL_RADIUS));
			
		//get collider at upper right corner
		}else if(getElementAt(xPosition + (2 * BALL_RADIUS), yPosition ) != null){
			collider = getElementAt(xPosition + (2 * BALL_RADIUS), yPosition );
		
		//get collider at lower right corner
		}else if(getElementAt(xPosition + (2 * BALL_RADIUS), yPosition + + (2 * BALL_RADIUS)) != null){
			collider = getElementAt(xPosition + (2 * BALL_RADIUS), yPosition + + (2 * BALL_RADIUS));
		}
		//return object or null
		return collider;
		
	}
	
	private void checkPaddleOrBrickCollision(){
		tmp = getCollidingObject(ball.getX(), ball.getY());
		if(tmp != null){
			if(tmp == paddle){
				vy = -vy;
			}else{
				remove(tmp);
				tmp = null;
				brickCounter--;
				vy = -vy;
			}
		}
		
	}
	
	private void checkForCollisions(){
		checkPaddleOrBrickCollision();
	}
	
	

	/** Row start x coordinate */
	private int rowStartX = (APPLICATION_WIDTH - ((NBRICKS_PER_ROW - 1) * BRICK_SEP) - (BRICK_WIDTH * NBRICKS_PER_ROW)) / 2;
	private int startXPaddle = (APPLICATION_WIDTH - PADDLE_WIDTH) / 2;
	private int startYPaddle = APPLICATION_HEIGHT - (PADDLE_HEIGHT + PADDLE_Y_OFFSET);
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private GRect paddle;
	private GOval ball = null; 
	private GObject tmp = null;
	private double vx; 
	private double vy = Y_VEL;
	private int numberOfTurnsLeft = NTURNS;
	private int brickCounter = NBRICKS_PER_ROW * NBRICK_ROWS;
}
