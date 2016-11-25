/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entryToDisplay.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entryToDisplay.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//Clears the canvas
		removeAll();
		
		//	Add background grid
		drawGraphBackground();
		
		//Graph All entries
		if(!(entryToDisplay.isEmpty())){
			graphAllEntry(entryToDisplay);
		}
		
	}
	
	
	/**
	 * Graphs all entries in list of entries
	 * @param listOfEntries to be graphed
	 */
	private void graphAllEntry(ArrayList<NameSurferEntry> listOfEntries){
		for(int j = 0; j < listOfEntries.size(); j++){
			//Choose Color
			Color paintColor = COLOR_COLLECTION[j % NUM_COLORS];
			graphNameEntry(listOfEntries.get(j), paintColor);
		}
	}
	
	/**
	 * Graph the curve for a namesurfer entry
	 * @param entry
	 */
	private void graphNameEntry(NameSurferEntry entry, Color paintColor){
		int xCordinate = 0;
		int spaceBtwVertLines = getWidth() / NDECADES;
		GLabel label;
		GLine line;
		for(int i = 0; i < NDECADES - 1; i++){
			//Add Label
			if(entry.getRank(i) != 0 ){
				label = new GLabel(entry.getName() + " " + entry.getRank(i), xCordinate + 3, entry.getRank(i) % getHeight());
				line = new GLine(xCordinate, entry.getRank(i)% getHeight(), xCordinate + spaceBtwVertLines, entry.getRank(i + 1) % getHeight());
			}else {
				label = new GLabel(entry.getName() + "  *", xCordinate + 3, (getHeight() - TOP_BORDER_OFFSET) % getHeight());
			    line = (entry.getRank(i + 1) != 0)? new GLine(xCordinate, (getHeight() - TOP_BORDER_OFFSET) % getHeight(), xCordinate + spaceBtwVertLines, entry.getRank(i + 1) % getHeight()) :
				new GLine(xCordinate, (getHeight() - TOP_BORDER_OFFSET) % getHeight(), xCordinate + spaceBtwVertLines, (getHeight() - TOP_BORDER_OFFSET) % getHeight());
			}
			//Set color on line and label
			label.setColor(paintColor);
			line.setColor(paintColor);
			
			//Add label and line to canvas
			add(label);
			add(line);
			
			//update x cordinate for drawing
			xCordinate = xCordinate + spaceBtwVertLines;
			
		}
			
	}
	
	
	/**
	 * Add the vertical lines to separate the decades and the horizontal lines 
	 * for borders
	 */
	private void drawGraphBackground(){
		int xCordinate = 0;
		int spaceBtwVertLines = getWidth() / NDECADES;
		for(int i = 1900; i <= 2000; i = i + 10){
			//draw line
			add(new GLine(xCordinate, 0, xCordinate, getHeight()));
			
			//add label
			add(new GLabel(String.valueOf(i), xCordinate + 5, getHeight() - 10));
			xCordinate = xCordinate + spaceBtwVertLines;
			
		}
		
		//Add horizontal border lines
		add(new GLine(0, TOP_BORDER_OFFSET, getWidth(), TOP_BORDER_OFFSET));
		add(new GLine(0, getHeight() - TOP_BORDER_OFFSET, getWidth(), getHeight() - TOP_BORDER_OFFSET));
		
	}
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* Private instance variables */
	private ArrayList<NameSurferEntry> entryToDisplay = new ArrayList<NameSurferEntry>();
}
