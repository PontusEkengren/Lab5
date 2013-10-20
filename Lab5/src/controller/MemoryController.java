package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import view.UserInterface;
import model.Memory;
import model.User;

public class MemoryController {

	private Memory theModel;
	private UserInterface theView;

	public MemoryController(UserInterface theView, Memory theModel) {
		this.theView = theView;
		this.theModel = theModel;

		// this.theView.loggedInLayout();
		this.theView.addLoginListener(new LoginListener());
		this.theView.addFlipListener(new FlipListener());

	}

	public void checkLogin(String name, ArrayList<User> users)
			throws IOException {
		boolean found = false;

		for (int i = 0; i < this.theModel.getUsers().size(); i++) {
			System.out.println(users.get(i).getName());// Prints all the
														// Username's in the
														// list
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
				e.printStackTrace();//Have no idea what this is (Auto-generated)
			}
		}
	}

	private class FlipListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent fl) {
			theView.flipImage();

		}

	}


}
