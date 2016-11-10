import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class TwoButtons {
	JFrame frame;
	JLabel label;
	
	public static void main(String[] args){
		TwoButtons gui = new TwoButtons();
		gui.go();
		
	}
	
	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton labelBtn = new JButton("Change Label");
		labelBtn.addActionListener(new LabelListener());
		
		JButton colorBtn = new JButton("Change Circle");
		colorBtn.addActionListener(new ColorListener());
		
		label = new JLabel("I'am a label");
		
		MyDrawPanel drawPanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.SOUTH, colorBtn);
		frame.getContentPane().add(BorderLayout.EAST, labelBtn);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.getContentPane().add(BorderLayout.WEST, label);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			label.setText("Ouch");
		}
	}
	
	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			frame.repaint();
		}
	}

}
