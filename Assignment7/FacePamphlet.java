/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		//Add FacePamphlet Canvas
		//add(new FacePamphletCanvas());
		
		//Create FacePamphlet Database
		profileDatabase = new FacePamphletDatabase();
		
		//Create canvas and add to your program
		canvas = new FacePamphletCanvas();
		add(canvas);
		
		//label for name textfield
		add(new JLabel("Name"), NORTH);
		
		//nametextfield
		nameTextField = new JTextField(TEXT_FIELD_SIZE);
		add(nameTextField, NORTH);
		
		//add profile button
		addBtn = new JButton("Add");
		add(addBtn, NORTH);
	    
		//Delete Profile Button
		deleteBtn = new JButton("Delete");
		add(deleteBtn, NORTH);
		
		//Delete Profile Button
		lookupBtn = new JButton("Lookup");
		add(lookupBtn, NORTH);
		
		//statustextfield
		statusTextField = new JTextField(TEXT_FIELD_SIZE);
		statusTextField.addActionListener(this);
		add(statusTextField, WEST);
		
		//change profile status button
		JButton changeStatusBtn = new JButton("Change Status");
		add(changeStatusBtn, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//picturetextfield
		pictureTextField = new JTextField(TEXT_FIELD_SIZE);
		pictureTextField.addActionListener(this);
		add(pictureTextField, WEST);
		
		//change profile status button
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		//Add friend textfield
		addFriendTextField = new JTextField(TEXT_FIELD_SIZE);
		addFriendTextField.addActionListener(this);
		add(addFriendTextField, WEST);
		
		//change profile status button
		add(new JButton("Add Friend"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		
		//Add listeners
		addActionListeners();
		
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		handleNameBtnEvents(e);
		handleChangeStatusEvents(e);
		handleChangePictureEvents(e);
		handleAddFriendEvents(e);
	}
    
    /**
     * Handle events by add button, lookup button and delete button
     * @param e
     */
    public void handleNameBtnEvents(ActionEvent e){
    	if(!nameTextField.getText().equals("")){
    		if(e.getActionCommand().equals("Add")){
        		//Add a new profile
    			if(profileDatabase.containsProfile(nameTextField.getText())){
    				currentProfile = profileDatabase.getProfile(nameTextField.getText());
    				canvas.showMessage("The profile with the name " + currentProfile.getName() + " already exists.");
    				
    			}else{
    				currentProfile = new FacePamphletProfile(nameTextField.getText());
    				profileDatabase.addProfile(currentProfile);
    				canvas.showMessage("New Profile Created");
    			}
    			canvas.displayProfile(currentProfile);
				
        		
        	}
        	
        	if(e.getActionCommand().equals("Delete")){
        		//Delete a profile
        		if(profileDatabase.containsProfile(nameTextField.getText())){
        			currentProfile = null;
        			profileDatabase.deleteProfile(nameTextField.getText());
        			canvas.removeAll();
        			canvas.showMessage("Profile of " + nameTextField.getText() + " deleted.");
        		}else{
        			canvas.showMessage("A profile with the name " +  nameTextField.getText() + " does not exist.");
        			currentProfile = null;
        		}
        	}
        	
        	if(e.getActionCommand().equals("Lookup")){
        		//Lookup a profile
        		if(profileDatabase.containsProfile(nameTextField.getText())){
        			currentProfile = profileDatabase.getProfile(nameTextField.getText());
        			canvas.showMessage("Displaying " + currentProfile.getName() + ".");
        			canvas.displayProfile(currentProfile);
        		}else{
        			currentProfile = null;
        			canvas.removeAll();
        			canvas.showMessage("A profile with the name" + nameTextField.getText() + " does not exist");
        		}
        	}
    	}
    	
    }
    
    
    /**
     * Handle change of status event for the current profile
     * @param e
     */
    public void handleChangeStatusEvents(ActionEvent e){
    	
    	if(!statusTextField.getText().equals("")){
	    	if(e.getActionCommand().equals("Change Status") || e.getSource() == statusTextField){
	    		//Change Status
	    		if(currentProfile != null){
	    			currentProfile.setStatus(statusTextField.getText());
	    			canvas.displayProfile(currentProfile);
	    			canvas.showMessage("Status updated to " + currentProfile.getStatus()+ ".");
	    		}else{
	    			canvas.showMessage("Please select a profile to change status.");
	    		}
	    		
	    	}
	    	
	    	
    	}
    }
    
    /**
     * Handle change of picture event for the current profile
     * @param e
     */
    public void handleChangePictureEvents(ActionEvent e){
    	if(!pictureTextField.getText().equals("")){
	    	if(e.getActionCommand().equals("Change Picture") || e.getSource() == pictureTextField){
	    		//Change Picture
	    		if(currentProfile != null){
	    			String filename = pictureTextField.getText();
	    			GImage image = null;
	    			try {
	    				image = new GImage(filename);
	    				
	    			} catch (ErrorException ex) {
	    			// Code that is executed if the filename cannot be opened.
	    				canvas.showMessage("Unable to open image file: " + filename + "." );
	    			}
	    			if(image != null){
	    				currentProfile.setImage(image);
	    				canvas.displayProfile(currentProfile);
	    				canvas.showMessage("Picture updated");
	    			}
	    			
	    		}else{
	    			canvas.showMessage("Please select a profile to change picture");
	    		}
	    		
	    	}
    	}
    }
    
    /**
     * Handle add friend event for the current profile
     * @param e
     */
    public void handleAddFriendEvents(ActionEvent e){
    	if(!addFriendTextField.getText().equals("")){
	    	if(e.getActionCommand().equals("Add Friend") || e.getSource() == addFriendTextField){
	    		//Add Friend
	    		String newFriend = addFriendTextField.getText();
	    		if(currentProfile != null){
	    			if(profileDatabase.containsProfile(newFriend)){
	    				if(!currentProfile.isFriend(newFriend)){
	    					currentProfile.addFriend(newFriend);
		    				profileDatabase.getProfile(newFriend).addFriend(currentProfile.getName());
		    				canvas.showMessage(newFriend +  "added as a friend");
	    				}else{
	    					canvas.showMessage(currentProfile.getName() + " already has " + newFriend +  "as a friend.");
	    				}
	    				
	    			}else{
	    				canvas.showMessage(newFriend + "does not exist.");
	    			}
	    			
	    		}else{
	    			canvas.showMessage("Please select a profile to add friend");
	    		}
	    	}
    	}
    }
    
    /* Private Instance Variable */
    private JButton addBtn;
    private JButton deleteBtn;
    private JButton lookupBtn;
    private JTextField nameTextField;
    private JTextField statusTextField;
    private JTextField pictureTextField;
    private JTextField addFriendTextField;
    private FacePamphletDatabase profileDatabase;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;

}
