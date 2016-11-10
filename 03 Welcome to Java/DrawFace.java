/*
 *  File: DrawFace.java
 *  ===========================
 *  Draws a face to the screen
 */


import acm.program.*;
import acm.graphics.*;



public class DrawFace extends GraphicsProgram {
	
	

	/** width of the face */
	private static final int FACE_WIDTH = 100;
	
	/** height of the face */
	private static final int FACE_HEIGHT = 200;
	
	public void run(){
		GFace face = new GFace(FACE_WIDTH, FACE_HEIGHT);
		add(face, (getWidth() - FACE_WIDTH) / 2, (getHeight() - FACE_HEIGHT) / 2);
		
	}
}
