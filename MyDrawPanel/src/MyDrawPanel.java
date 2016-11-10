

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class MyDrawPanel extends JPanel{
	public void paintComponent(Graphics g){
		g.setColor(Color.orange);
		g.fillRect(20, 50, 100, 100);
	}
}
