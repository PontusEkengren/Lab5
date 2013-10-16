package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.Memory;

public class UserInterface extends JPanel {

	private Memory model;

	private JLabel statusLabel;
	private JTextField tempText;
	private JButton tempButton;

	public UserInterface(Memory model) {
		this.model = model;

		tempButton = new JButton("Login");
		tempText = new JTextField("Username");
		statusLabel = new JLabel("Welcome to Memory!");
		this.add(statusLabel);
		this.add(tempText);
		this.add(tempButton);
		
		//this.add(statusLabel);
		



}
}