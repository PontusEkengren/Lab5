package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterface extends JPanel {

	private Timer timerCard, timerFaildLogin;
	private int timerTime = 2000; // timer delay in ms this makes 2 seconds
	private int numberOfCards = 10;
	// to check for pairs
	private boolean pairChecker = false;
	private int[] pairNumber;
	private JLabel WelcomeLabel, statusLabel2, faildLogin, test, cred;
	private JTextField username;
	private JButton LoginButton, FlipButton, CardButton[];
	private ImageIcon CardImage[], faceup, facedown, current;
	private JPanel cP, nP, sP, pP, northFL1, northFL2;

	// Layouts
	private BorderLayout bl;
	private GridLayout gl;
	private BoxLayout boxL;
	private FlowLayout fL;

	// Menubar at the top
	private JMenuBar menuBar;
	private JMenuItem menuExit, menuHighScore, menuRegister;
	private JMenu menu;
	
	//Custom Dialog
	private JDialog dialog;

	public UserInterface() {
		super();

		// loading the panel
		JFrame frame = new JFrame("Memory");
		dialog = new JDialog(frame,"Click a button");  
		
		//Dialog
	    dialog.setSize(300,130);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(false);
        
		//Menubar
		menuBar = new JMenuBar();
		//MenuRow
		menu = new JMenu("File");
		menuBar.add(menu);
		//MenuItems
		menuExit = new JMenuItem("Exit");
		menuRegister = new JMenuItem("Register");
		menuHighScore = new JMenuItem("HighScore");
		menu.add(menuRegister);
		menu.add(menuHighScore);
		menu.add(menuExit);

		frame.setJMenuBar(menuBar);

		// Panels
		cP = new JPanel(); // Center Panel
		nP = new JPanel(); // North Panel
		sP = new JPanel(); // South Panel
		pP = new JPanel(); // Panel Panel, Maybe for the memorygame if we switch
							// visibility of login and the game
		northFL1 = new JPanel();
		northFL2 = new JPanel(); // Just so structure the north login button NEW

		// Layouts
		bl = new BorderLayout();
		bl.setHgap(50); // Sets how many pixel it should be between every
						// object(Horisontal)
		bl.setVgap(50); // - || - (Vertical)
		gl = new GridLayout(4, 5);
		boxL = new BoxLayout(nP, BoxLayout.PAGE_AXIS);
		fL = new FlowLayout();

		// loading images
		faceup = new ImageIcon("images/faceup.png");
		facedown = new ImageIcon("images/facedown.png");
		current = facedown;

		// loading buttons and text
		LoginButton = new JButton("Login");
		FlipButton = new JButton("Flip Image");
		username = new JTextField("Player Name");
		WelcomeLabel = new JLabel("Welcome to Memory!");
		statusLabel2 = new JLabel("Logged In");
		faildLogin = new JLabel("LoginFaild");
		test = new JLabel("LOREM IPSUM SHIT");
		cred = new JLabel("Credits & Copyright 2013 | Kev, Pent");
		// connecting array of imagesIcons to actuall images
		CardImage = new ImageIcon[numberOfCards + 1];
		pairNumber = new int[2];

		CardImage[0] = new ImageIcon("images/DIGIT_0.gif");
		CardImage[1] = new ImageIcon("images/DIGIT_1.gif");
		CardImage[2] = new ImageIcon("images/DIGIT_2.gif");
		CardImage[3] = new ImageIcon("images/DIGIT_3.gif");
		CardImage[4] = new ImageIcon("images/DIGIT_4.gif");
		CardImage[5] = new ImageIcon("images/DIGIT_5.gif");
		CardImage[6] = new ImageIcon("images/DIGIT_6.gif");
		CardImage[7] = new ImageIcon("images/DIGIT_7.gif");
		CardImage[8] = new ImageIcon("images/DIGIT_8.gif");
		CardImage[9] = new ImageIcon("images/DIGIT_9.gif");
		CardImage[10] = new ImageIcon("images/facedown.png");

		// intilizing buttons to images

		CardButton = new JButton[numberOfCards + 1];
		for (int i = 0; i < numberOfCards; i++) {
			this.add(CardButton[i] = new JButton(CardImage[10]));
			CardButton[i].setVisible(false);
		}

		timerCard = new Timer(timerTime, new TimerListener());
		timerFaildLogin = new Timer(timerTime + 1000, new TimerListenerLogin());

		// Some things are not default visible
		statusLabel2.setVisible(false);
		FlipButton.setVisible(false);
		faildLogin.setVisible(false);

		// We're going to need these to actually see them
		this.setLayout(bl);
		this.add(cP, bl.CENTER); // Central Panel to BorderLayout CENTER
		this.add(nP, bl.NORTH); // North Panel to BorderLayout NORTH
		this.add(sP, bl.SOUTH); // South Panel to BorderLayout SOUTH

		// North Panel setup
		nP.setLayout(boxL);
		nP.add(northFL1);
		northFL1.add(WelcomeLabel);

		nP.add(northFL2); // Panel med FlowLayout
		northFL2.add(username);
		northFL2.add(LoginButton);

		// Center Panel Setup
		// cP.add(FlipButton);
		cP.add(faildLogin);
		for (int i = 0; i < numberOfCards; i++) {
			cP.add(CardButton[i]);
		}

		// Sout Panel setup
		sP.add(cred);

		// Old Stuff
		/*
		 * this.add(WelcomeLabel); this.add(username); this.add(LoginButton);
		 * this.add(statusLabel2); this.add(faildLogin); this.add(FlipButton);
		 * this.add(faildLogin);
		 */

		// adding it all the ingredients the frame
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(720, 600)); // New Framesize
		frame.setVisible(true);

	}

	public void addCardListener(ActionListener cl) {

		for (int i = 0; i < numberOfCards; i++) {
			CardButton[i].setActionCommand("button" + i);
			CardButton[i].addActionListener(cl);
		}

	}

	public void addLoginListener(ActionListener ll) {
		LoginButton.addActionListener(ll);
	}

	public void addFlipListener(ActionListener fl) {
		FlipButton.addActionListener(fl);
	}
	public void addExitListener(ActionListener mel) { //Menu Exit Listener mel
		menuExit.addActionListener(mel);
	}
	public void addRegisterListener(ActionListener mrl) { //Menu Register Listener mrl
		menuRegister.addActionListener(mrl);
	}
	public void addHighScoreListener(ActionListener mhl) { //Menu HighScore Listener mhl
		menuHighScore.addActionListener(mhl);
	}

	public String getLoginText() {
		String tmp = username.getText();
		return tmp;
	}
	public void displayHighScore(){
		dialog.setVisible(true);
	}
	public void displayHighScore(int userId){
		dialog.setVisible(true);
	}

	public void flipImage(int buttonNr) {

		CardButton[buttonNr].setIcon(CardImage[buttonNr]);
		
		repaint();
		if (pairChecker == true) {
			System.out.println("if");
			pairNumber[1] = buttonNr;
			pairChecker = false;
			timerCard.start();
		} else if(pairChecker==false) {
			System.out.println("elseif");
			pairNumber[0] = buttonNr;
			pairChecker = true;
		}

	}

	public void loggedInLayout() {
		statusLabel2.setVisible(true);
		WelcomeLabel.setVisible(false);
		username.setVisible(false);
		LoginButton.setVisible(false);
		FlipButton.setVisible(true);
		faildLogin.setVisible(false);

		for (int i = 0; i < numberOfCards; i++) {
			CardButton[i].setVisible(true);
		}
	}

	public void loggedInFaild() {
		faildLogin.setVisible(true);

		timerFaildLogin.start();
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timerCard.stop();
			CardButton[pairNumber[0]].setIcon(CardImage[10]);
			CardButton[pairNumber[1]].setIcon(CardImage[10]);
			repaint();
		}
	}

	private class TimerListenerLogin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			faildLogin.setVisible(false);
			timerFaildLogin.stop();
		}
	}

}