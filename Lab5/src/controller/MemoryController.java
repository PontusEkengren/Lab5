package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import view.UserInterface;
import model.Card;
import model.Memory;
import model.User;


public class MemoryController {

	private int numberOfCards = 30;
	private int firstCard=-1; // To check if both of the cards have been drawn
	private int bothDrawn=0;
	private boolean everyOther=true;
	private int[] newOrderArray;
	private Memory theModel;
	private UserInterface theView;
	
	//Active Player Stuff
	int activeId;
	int activeScore=0;

	public MemoryController(UserInterface theView, Memory theModel) {
		this.theView = theView;
		this.theModel = theModel;
		

		setNewImageOrder(this.theModel.getNewCardOrder());
		
		this.theView.addLoginListener(new LoginListener());
		this.theView.addCardListener(new CardListener());
		this.theView.addExitListener(new ExitListener());
		this.theView.addRegisterListener(new RegisterListener());
		this.theView.addHighScoreListener(new HighScoreListener());
		this.theView.addRegisterButtonListener(new RegisterButtonListener());
		this.theView.addRegisterButtonListener2(new RegisterButtonListener2());

	}
	/**
	 * Sets the new highscore
	 * @param id
	 * @param highscore
	 */
	public void setNewHighScore(int id, int highscore){
		this.theModel.getUsers().get(id).setHighScore(highscore);
	}
	/**
	 * Takes the new scrabeled order from Memory and tells the order to UI
	 * @param newCardOrderList
	 */
	public void setNewImageOrder(ArrayList<Card> newCardOrderList){
		newOrderArray= new int[10];

		
		newOrderArray= new int[newCardOrderList.size()];
		for (int i = 0; i < newCardOrderList.size(); i++) {
			newOrderArray[i]=newCardOrderList.get(i).getId();
		}
		theView.setNewImageOrder(newOrderArray);

		this.theView.addResetListener(new ResetListener());
		//

	}
	/**
	 * This is the Id of the user whom is currently playing the game
	 * @param id
	 */
	public void getActiveId(int id){
		activeId = id-1;
	}
	/**
	 * Checks if the name of the user you want to login as exists in the user file
	 * if not found loginFaild
	 * @param name
	 * @param users
	 * @throws IOException
	 */
	public void checkLogin(String name, ArrayList<User> users)
			throws IOException {
		boolean found = false;

		for (int i = 0; i < this.theModel.getUsers().size(); i++) {
			// Debugger //Prints all users in the list
			// System.out.println(users.get(i).getName());
			if (name.equals(users.get(i).getName())) {
				this.theView.loggedInLayout();
				
				
				getActiveId(users.get(i).getId());
				//theView.setPersonalBest(theModel.getUsers().get(activeId).getHighScore());
				//System.out.println("Error 403 ID: " + activeId);
				//System.out.println("Error 404: " + theModel.getUsers().get(activeId).getName());
				//System.out.println("Error 405: " + theModel.getUsers().get(activeId).getHighScore());
				theView.setPersonalBest(theModel.getUsers().get(activeId).getHighScore());
				/*this.theView.displayPlayerScore(Integer.toString(users.get(i).getScore()));
				
				this.theView.setPlayerId(users.get(i).getId());
				this.theView.setPlayerName(users.get(i).getName());
				this.theView.setPlayerScore(users.get(i).getScore());*/
				found = true;
				// this.theView.repaint();
			}
		}
		if (found != true) {
			this.theView.loggedInFaild();
		}
	}
//Used for LoginButton
	private class LoginListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent ll) {
			try {
				checkLogin(theView.getLoginText(), theModel.getUsers());
			} catch (IOException e) {
				e.printStackTrace();// Have no idea what this is
									// (Auto-generated)
			}
		}
	}
//Used for all the Cards(buttons)
	private class CardListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent cl) {
			for (int i = 0; i < numberOfCards; i++) {
				
				if (cl.getActionCommand().equals("button" + i)) {
					if(theView.checkIfBothDrawn()==true)
						break;
					
					if (everyOther == true) {
						firstCard=i;
						theModel.getCards().get(i).setFound(true);
						bothDrawn++;
						everyOther = false;
					} else if(everyOther==false) {
						theModel.getCards().get(i).setFound(true);
						bothDrawn++;
						everyOther = true;
					}
					
					//System.out.println(bothDrawn);
					//System.out.println(i);
					
					/*if(bothDrawn==2&&everyOther==true){
						bothDrawn=0;
						theModel.getCards().get(i).setFound(false);
						theModel.getCards().get(firstCard).setFound(false);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}*/ 
					
					if(bothDrawn==2){
						//theView.flipPair
						activeScore++; //Just like golf you want as few of these as possible
						theView.setPlayerScoreLabel(activeScore);
						bothDrawn=0;
						
						if(theModel.checkIfPair(theModel.getCards().get(i), theModel.getCards().get(firstCard))==true){
							//System.out.println("Pair!");
							theView.flipPair(i);
							if(theModel.checkIfDone()==true)
							{
								theView.createMessage("Congrats you won!");
								if(activeScore<theModel.getUsers().get(activeId).getHighScore()){//if activeScore is less than last highscore
									theModel.getUsers().get(activeId).setHighScore(activeScore);//set new highscore
									theView.setPersonalBest(theModel.getUsers().get(activeId).getHighScore());
									theView.createMessage("You beat your previous best score!");
								}
								try {
									theModel.save("test.lst");
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}
						else{
							theModel.getCards().get(i).setFound(false);
							theModel.getCards().get(firstCard).setFound(false);
							theView.flipImage(i);
						}
					}
					else{
						theView.flipImage(i);
					}
				// System.out.println("pressed" + i);
					// System.out.println(cl.getActionCommand().toString());
					//theView.flipImage(i);// Want to send button number here

				}
			}
		}
	}
//Listen for exits
	private class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}
//Listen for rebutton
	private class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.displayRegister();

		}

	}
//Listen for Highscore
	private class HighScoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<User> users = theModel.getUsers();
			theView.displayHighScore();

		}

	}
	//Listen for reset(not yet implemented)
	private class ResetListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				theModel.reset();
				theView.repaint();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
//Listen for register
	private class RegisterButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (theView.getRegisterUsername().length() < 2) {						//If username is not null
				theView.createError("Username must be 3 or more characters");
			} else {
				if(theModel.checkIfDuplicate(theView.getRegisterUsername())){ 	//If there is a duplicate
					theView.createError("Username is already taken!");
				} else{ //If there is no duplicate
					theModel.addUser(theModel.getNoOfUsers() + 1, theView.getRegisterUsername(), 0);
					try {
						theModel.save("test.lst");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					theView.hideRegister();
				}
			}
		}
	}
//Listen for register
	private class RegisterButtonListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.displayRegister();

		}

	}

}
