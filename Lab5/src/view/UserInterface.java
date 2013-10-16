package view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import model.Memory;

public class UserInterface extends JPanel {

	private Memory model;

	private JLabel statusLabel;
	private JTextField tempText;
	private JButton tempButton;

	public UserInterface(Memory model) {
		this.model = model;

		tempButton = new JButton("Guess");
		tempText = new JTextField("Hellow World!");
		//this.add(statusLabel);
		



}
}