/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		
		//Add name surfer graph
		graph = new NameSurferGraph();
		add(graph);
		
		//Add Name label to display
		add(new JLabel("Name"), SOUTH);
		
		//Adds a text field to the display
		textField = new JTextField(20);
		textField.setActionCommand("Name");
		add(textField, SOUTH);
		textField.addActionListener(this);
		
		// Button to clear display
		add(new JButton("Clear"), SOUTH);
		
		// Button to graph on display
		add(new JButton("Graph"), SOUTH);
		//Listen for button action events
		addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		// Clear canvas if user clicks clear button
		if (e.getActionCommand().equals("Clear")) {
			// Clears the canvas
			graph.clear();
		}
		
		if (e.getActionCommand().equals("Graph")) {
			graph.addEntry(nameDirectory.findEntry(textField.getText()));
			// Graph on the canvas
			graph.update();
		}

	}
	
	/* private instance variables */
	private JTextField textField;
	private NameSurferDataBase nameDirectory = new NameSurferDataBase(NAMES_DATA_FILE);
	private NameSurferGraph graph;
}
