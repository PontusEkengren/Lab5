package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import view.UserInterface;
import model.Memory;
import model.User;

public class MemoryController {

	private int numberOfCards = 10;
	private Memory theModel;
	private UserInterface theView;

	public MemoryController(UserInterface theView, Memory theModel) {
		this.theView = theView;
		this.theModel = theModel;

		// this.theView.loggedInLayout();
		this.theView.addLoginListener(new LoginListener());
		this.theView.addCardListener(new CardListener());
		this.theView.addExitListener(new ExitListener());
		this.theView.addRegisterListener(new RegisterListener());
		this.theView.addHighScoreListener(new HighScoreListener());
		//
	}

	public void checkLogin(String name, ArrayList<User> users)
			throws IOException {
		boolean found = false;

		for (int i = 0; i < this.theModel.getUsers().size(); i++) {
			// Debugger //Prints all users in the list
			// System.out.println(users.get(i).getName());
			if (name.equals(users.get(i).getName())) {
				this.theView.loggedInLayout();
				found = true;
				// this.theView.repaint();
			}
		}
		if (found != true) {
			this.theView.loggedInFaild();
		}
	}

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

	private class CardListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent cl) {

			for (int i = 0; i < numberOfCards; i++) {

				if (cl.getActionCommand().equals("button" + i)) {
					//System.out.println("pressed" + i);
					//System.out.println(cl.getActionCommand().toString());
					theView.flipImage(i);// Want to send button number here
				}
			}

		}

	}
	private class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			
		}
		
	}
	private class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.displayRegister();
			
		}
		
	}
	private class HighScoreListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.displayHighScore();
			
		}
		
	}

}
