package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.GuessMyWordModel;

public class UserInterface extends JPanel {

	private Memory model;

	private JLabel statusLabel;
	private JTextField inputGuess;
	private JButton guessButton;

	public UserInterface(Memory model) {
		this.model = model;

		statusLabel = new JLabel("Welcome to Guess My Word!");
		inputGuess = new JTextField(3);
		guessButton = new JButton("Guess");

		this.add(statusLabel);
		
		//GuessListener gl = new GuessListener();
		//HÄr skapar vi actionslisteners och
		//använder dem
		//guessButton.addActionListener(gl);
		//inputGuess.addActionListener(gl);
	}
/*
	private void processGuess() {
		String temp = inputGuess.getText();
		if (temp != null) {
			char guess = temp.charAt(0);
			model.handleGuess(guess);
			if(model.isSolved()){
				JOptionPane.showMessageDialog(this, "Yeehaa!");
				model.reset();
			}
			statusLabel.setText(model.getGuessSoFar());

		}
	}
	
	private class GuessListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		processGuess();
		}

		}
	*/


	private static final long serialVersionUID = 1L;
}
