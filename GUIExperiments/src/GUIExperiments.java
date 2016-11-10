
import javax.swing.*;
import java.awt.event.*;

public class GUIExperiments implements ActionListener {
	
	public static void main(String[] args){
		GUIExperiments simpleUI = new GUIExperiments();
		simpleUI.go();
	}
	
	public void go(){
		JFrame frame = new JFrame();
		btn = new JButton("Click me");
		
		
		btn.addActionListener(this);
		
		frame.getContentPane().add(btn);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		btn.setText("You have been clicked");
	}
	/* Private Instance Variables */
	private JButton btn;
}
