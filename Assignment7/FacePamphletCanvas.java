/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		//You fill this in
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel msgLabel = new GLabel(msg);
		msgLabel.setFont(MESSAGE_FONT);
		add(msgLabel, (getWidth()/ 2) - (msg.length()/2) , getHeight() - BOTTOM_MESSAGE_MARGIN);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		
		//Clears the display
		removeAll();
		
		//Adds Profile name
		GLabel nameLabel = new GLabel(profile.getName());
		nameLabel.setColor(Color.BLUE);
		nameLabel.setFont(PROFILE_NAME_FONT);
		add(nameLabel, LEFT_MARGIN, TOP_MARGIN + (nameLabel.getHeight()));
		
		//Add Profile picture
		if(profile.getImage() == null){
			//Draw Square
			GRect square = new GRect(LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + (nameLabel.getHeight()), IMAGE_WIDTH, IMAGE_HEIGHT);
			add(square);
			GLabel noImageLabel = new GLabel("No Image");
			add(noImageLabel, (LEFT_MARGIN + (square.getWidth() /  2)) - (noImageLabel.getWidth()/ 2), square.getY() + (square.getHeight()/2) + noImageLabel.getHeight());
		}else{
			//Draw Picture
			GImage profilePicture = profile.getImage();
			profilePicture.scale(IMAGE_WIDTH / profilePicture.getWidth(), IMAGE_HEIGHT / profilePicture.getHeight());
			add(profilePicture, LEFT_MARGIN, IMAGE_MARGIN + TOP_MARGIN + (nameLabel.getHeight()));
		}
		
		//Add Status
		GLabel statusLabel;
		if(profile.getStatus().equals("")){
			statusLabel = new GLabel("No current Status");
			statusLabel.setFont(PROFILE_STATUS_FONT);
			add(statusLabel, LEFT_MARGIN, getHeight() - STATUS_MARGIN);
		}else{
			statusLabel = new GLabel(profile.getName() + " is " + profile.getStatus());
			statusLabel.setFont(PROFILE_STATUS_FONT);
			add(statusLabel, LEFT_MARGIN, getHeight() - STATUS_MARGIN);
		}
		
		//Add Friend List
		GLabel friendHeader = new GLabel("Friends:");
		friendHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		Double startingHeight = IMAGE_MARGIN + TOP_MARGIN + (nameLabel.getHeight());
		add(friendHeader, getWidth() / 2, startingHeight);
		Iterator iter = profile.getFriends();
		while(iter.hasNext()){
			String key = (String) iter.next();
			GLabel friendLabel = new GLabel(key);
			friendLabel.setFont(PROFILE_FRIEND_FONT);
			startingHeight += friendLabel.getHeight();
			add(friendLabel,getWidth() / 2, startingHeight);
		}
		
	}

	
}
