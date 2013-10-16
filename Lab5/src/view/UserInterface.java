package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import model.Memory;

public class UserInterface extends JPanel {

	private Memory model;

	private Timer timer;
	private int timerTime = 2000; //timer delay in ms this makes 2 seconds
	private JLabel WelcomeLabel, statusLabel2;
	private JTextField tempText;
	private JButton LoginButton, FlipButton;
	private ImageIcon faceup, facedown, current;


	public UserInterface(Memory model) {
		super();
		this.model = model;

		//loading images
		faceup = new ImageIcon("images/faceup.png");
		facedown = new ImageIcon("images/facedown.png");
		current = facedown;
		//loading buttons and text
		LoginButton = new JButton("Login");
		FlipButton = new JButton("Flip Image");
		tempText = new JTextField("Player Name");
		WelcomeLabel = new JLabel("Welcome to Memory!");
		statusLabel2 = new JLabel("Logged In");
		LoginListener ll = new LoginListener();
		FlipListener fl = new FlipListener();
		timer = new Timer(timerTime, new TimerListener());
		
		statusLabel2.setVisible(false);
		FlipButton.setVisible(false);
		this.add(WelcomeLabel);
		this.add(tempText);
		this.add(LoginButton);
		this.add(statusLabel2);
		this.add(FlipButton);

		LoginButton.addActionListener(ll);
		FlipButton.addActionListener(fl);

	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int x = (this.getWidth() - current.getIconWidth())/2;
		int y = (this.getHeight() - current.getIconHeight())/2;
		current.paintIcon(this, g, x, y);
	}
	
	public void flipImage() {
		current= faceup;
		timer.start();
		repaint();
	}
	
	private class FlipListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			flipImage();
			
		}
		
	}

	public void loggedInLayout() {
		statusLabel2.setVisible(true);
		WelcomeLabel.setVisible(false);
		tempText.setVisible(false);
		LoginButton.setVisible(false);
		FlipButton.setVisible(true);
	}


	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			loggedInLayout();
		}
	}
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			current = facedown;
			repaint();
			
		}
		
	}
}