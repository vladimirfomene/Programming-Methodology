import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class MyDrawPanel extends JPanel{
	
	public void paintComponent(Graphics g){
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		
		Color randomColor = new Color(red, green, blue);
		g.setColor(randomColor);
		g.fillOval(70, 70, 100, 100);
	}
}
