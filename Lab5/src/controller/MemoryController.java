package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

//import view.TimerListener;
import view.UserInterface;
import model.Memory;
import model.User;

public class MemoryController {

	private Memory theModel;
	private UserInterface theView;
	private Timer timer;
	private int timerTime = 2000; // timer delay in ms this makes 2 seconds

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class FlipListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent fl) {
			theView.flipImage();
			// timer is running
			// theView.imageFaceDown();

		}

	}

	// When flip image happends: timer.start();

}
