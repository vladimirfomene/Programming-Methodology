/*
 *  File: TextFieldExample.java
 *  -------------------------------
 *  This class displays a greeting whenever a name is entered.
 */
import javax.swing.*;
import java.awt.event.*;
import acm.program.*;

public class TextFieldExample extends ConsoleProgram{
	public void init(){
		textField = new JTextField(10);
		textField.setActionCommand("Name");
		add(new JLabel("Name"), SOUTH);
		add(textField, SOUTH);
		textField.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Name")){
			println("Hello, " + textField.getText());
		}
	}
	
	/* private instance variables */
	private JTextField textField;
}
