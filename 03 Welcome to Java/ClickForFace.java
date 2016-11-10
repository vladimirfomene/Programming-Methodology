/*
 *  File: ClickForFace.java
 *  ==========================
 *  Add a face on the canvas when a user mouse click
 */


import acm.graphics.*;
import acm.program.*;
import java.awt.event.*;

public class ClickForFace extends GraphicsProgram {
	
	/** width of the face */
	private static final int FACE_WIDTH = 100;
	
	/** height of the face */
	private static final int FACE_HEIGHT = 200;
	
	public void init(){
		addMouseListeners();
	}
	
	public void mouseClicked(MouseEvent e){
		GFace face = new GFace(FACE_WIDTH,FACE_HEIGHT);
		add(face, e.getX(), e.getY());
	}
}
