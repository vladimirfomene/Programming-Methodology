/*
* File : hello.java
* ========================
* This program purpose is to add hello to 
* our canvas which is the drawing canvas
*/

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class HelloProgram extends GraphicsProgram{
	public void run(){
		GLabel msg = new GLabel("Hello program", 50, 40);
		msg.setColor(Color.RED);
		msg.setFont("Helvetica-24");
		add(msg);
	}
}