package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Memory;

public class UserInterface extends JPanel {

	private Memory model;

	private JLabel WelcomeLabel, statusLabel2;
	private JTextField tempText;
	private JButton tempButton;

	public UserInterface(Memory model) {
		this.model = model;

		tempButton = new JButton("Login");
		tempText = new JTextField("Player Name");
		WelcomeLabel = new JLabel("Welcome to Memory!");
		statusLabel2 = new JLabel("Logged In");
		LoginListener ll = new LoginListener();

		statusLabel2.setVisible(false);

		this.add(WelcomeLabel);
		this.add(tempText);
		this.add(tempButton);
		this.add(statusLabel2);

		tempButton.addActionListener(ll);

	}

	public void loggedInLayout() {
		statusLabel2.setVisible(true);
		WelcomeLabel.setVisible(false);
		tempText.setVisible(false);
		tempButton.setVisible(false);

	}

	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			loggedInLayout();
		}
	}
}