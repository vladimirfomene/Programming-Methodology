/*
* File : Olympics.java
* ======================
* Inspired by the Olympic flag from the textbook
* Demonstration of use the acm.graphics library.
*/

import java.awt.*;
import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;

public class Olympics extends GraphicsProgram{
	
	/** Private Constants */
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 200;
	
	public void run(){
		
		frame(180, 80, Color.GRAY);       //Put up a frame for your flag
		upperCircle(space, side);	//UpperCircles
		lowerCircle(space, side);	//LowerCircles
		addCopyright("Crafted by Vladimir Fomene", Color.RED); //add copyright material

	}
	
	private void lowerCircle(int space, int side){
		
		for(int i = 0; i < 2; i++){
			int offset = (side + space) * i;
			GOval circle = new GOval(250 + offset, 150, side, side);
			circle.setColor(rgen.nextColor());
			add(circle);
		}
	}
	
	private void upperCircle(int space, int side){
		
		for(int i = 0; i < 3; i++){
			int offset = (side + space) * i;
			GOval circle = new GOval(200 + offset, 100, side, side);
			circle.setColor(rgen.nextColor());
			add(circle);
			
		}
	}
	
	private void frame(int xCoordinate, int yCoordinate, Color color){
		
		GRect frame = new GRect(xCoordinate, yCoordinate, FRAME_WIDTH, FRAME_HEIGHT);
		frame.setFilled(true);
		frame.setColor(color);
		add(frame);
		
	}
	
	private void addCopyright(String copyright, Color color){
		GLabel label = new GLabel(copyright,10 , 50);
		label.setFont("Helvetica-36");
		label.setColor(color);
		add(label);
	}
	
	/** Instance variables ***/
	private int space = 5;
	private int side = 100;
	private RandomGenerator rgen = RandomGenerator.getInstance();
}