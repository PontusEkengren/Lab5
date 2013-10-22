package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterface extends JPanel {

	ClassLoader cl = this.getClass().getClassLoader();
	private Timer timerCard, timerFaildLogin;
	private int timerTime = 2000; // timer delay in ms this makes 2 seconds
	private int numberOfCards = 30;
	
	private int bothDrawn=0;
	// to check for pairs
	private boolean pairChecker = false;
	private int[] pairNumber;
	private JLabel WelcomeLabel, statusLabel2, faildLogin, test, cred, rl_username;
	private JTextField username, rt_username;
	private JButton LoginButton, RegisterButton, FlipButton, CardButton[], rb_register;
	private ImageIcon CardImage[], faceup, facedown, current;
	private JPanel cP, nP, sP, pP, northFL1, northFL2, rP;
	private JTable highscore;
	private JScrollPane scrollPane;

	// Layouts
	private BorderLayout bl;
	private GridLayout gl;
	private BoxLayout boxL;
	private FlowLayout fL;
	private SpringLayout sp;
	private GridLayout registerLayout;

	// Menubar at the top
	private JMenuBar menuBar;
	private JMenuItem menuExit, menuHighScore, menuRegister, menuReset;
	private JMenu menu;
	
	//Custom Dialog
	private JDialog dialog;
	private JDialog register;
	
	//Player Info
	String playerName = "";
	int playerId;
	private int personalBestScore = 101;
	int playerTries = 0;
	private JLabel scoreLabel, personalBestLabel;
	private JLabel playerScoreLabel, personalBestScoreLabel;
	

	public UserInterface() {
		super();
		
		//Player Info
		scoreLabel = new JLabel("Score: ");
		personalBestLabel = new JLabel("Personal Best: ");
		//personalBestLabel = new JLabel("0");
		personalBestScoreLabel = new JLabel("");
		personalBestScoreLabel.setText(Integer.toString(101));
		playerScoreLabel = new JLabel("0");
		
		// loading the panel
		JFrame frame = new JFrame("Memory");
		dialog = new JDialog(frame,"Highscore", Dialog.ModalityType.DOCUMENT_MODAL);  //3rd argument adds modality
		register = new JDialog(frame, "Register New User", Dialog.ModalityType.DOCUMENT_MODAL);
		String[] columnNames = {"ID", "Username", "Score"};
		Object[][] data = {{new Integer(1), "Kevin", new Integer(personalBestScore)}};
		
		highscore = new JTable(data, columnNames);
		scrollPane = new JScrollPane(highscore);
		highscore.setFillsViewportHeight(true);
		
		//Dialog
	    dialog.setSize(300,130);
        dialog.setLayout(new FlowLayout());
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(false);
        dialog.add(highscore);
        
        //Register init
        sp = new SpringLayout();
        registerLayout = new GridLayout(2,2);
        rP = new JPanel();
        register.setSize(200,75);
        //register.setLayout(sp);
        register.setLocationRelativeTo(this);
        register.setVisible(false);
        //Register buttons
        register.add(rP);
        rP.setLayout(registerLayout);
        rl_username = new JLabel("Username: ");
        rt_username = new JTextField("username", 7);
        rb_register = new JButton("Register");
        rP.add(rl_username);
        rl_username.setLabelFor(rt_username);
        rP.add(rt_username);
        rP.add(rb_register);
        sp.putConstraint(SpringLayout.WEST, rt_username, 5, SpringLayout.EAST, rl_username);
        //sP.makeCompactGrid(rP, 1, 2, 6, 6, 6, 6);

        
		//Menubar
		menuBar = new JMenuBar();
		//MenuRow
		menu = new JMenu("File");
		menuBar.add(menu);
		//MenuItems
		menuExit = new JMenuItem("Exit");
		menuRegister = new JMenuItem("Register");
		menuHighScore = new JMenuItem("HighScore");
		menuReset = new JMenuItem("Reset");
		menuReset.setVisible(false);
		//menu.add(menuReset);
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
		RegisterButton = new JButton("Register");
		FlipButton = new JButton("Flip Image");
		username = new JTextField("Player Name");
		WelcomeLabel = new JLabel("Welcome to Memory!");
		statusLabel2 = new JLabel("Logged In");
		faildLogin = new JLabel("LoginFaild");
		test = new JLabel("LOREM IPSUM SHIT");
		cred = new JLabel("Credits & Copyright 2013 | Kev, Pent");
		// connecting array of imagesIcons to actual images
		CardImage = new ImageIcon[numberOfCards + 1];
		pairNumber = new int[2];

		CardImage[0] = new ImageIcon(cl.getResource("images/flags/se.png"));
		CardImage[1] = new ImageIcon(cl.getResource("images/flags/uk.png"));
		CardImage[2] = new ImageIcon(cl.getResource("images/flags/china.png"));
		CardImage[3] = new ImageIcon(cl.getResource("images/flags/ger.png"));
		CardImage[4] = new ImageIcon(cl.getResource("images/flags/usa.png"));
		CardImage[5] = new ImageIcon(cl.getResource("images/flags/alb.png"));
		CardImage[6] = new ImageIcon(cl.getResource("images/flags/bra.png"));
		CardImage[7] = new ImageIcon(cl.getResource("images/flags/can.png"));
		CardImage[8] = new ImageIcon(cl.getResource("images/flags/ita.png"));
		CardImage[9] = new ImageIcon(cl.getResource("images/flags/kor.png"));
		CardImage[10] = new ImageIcon(cl.getResource("images/flags/nor.png"));
		CardImage[11] = new ImageIcon(cl.getResource("images/flags/por.png"));
		CardImage[12] = new ImageIcon(cl.getResource("images/flags/rus.png"));
		CardImage[13] = new ImageIcon(cl.getResource("images/flags/tur.png"));
		CardImage[14] = new ImageIcon(cl.getResource("images/flags/un.png"));
		CardImage[15] = new ImageIcon(cl.getResource("images/flags/se.png"));
		CardImage[16] = new ImageIcon(cl.getResource("images/flags/uk.png"));
		CardImage[17] = new ImageIcon(cl.getResource("images/flags/china.png"));
		CardImage[18] = new ImageIcon(cl.getResource("images/flags/ger.png"));
		CardImage[19] = new ImageIcon(cl.getResource("images/flags/usa.png"));
		CardImage[20] = new ImageIcon(cl.getResource("images/flags/alb.png"));
		CardImage[21] = new ImageIcon(cl.getResource("images/flags/bra.png"));
		CardImage[22] = new ImageIcon(cl.getResource("images/flags/can.png"));
		CardImage[23] = new ImageIcon(cl.getResource("images/flags/ita.png"));
		CardImage[24] = new ImageIcon(cl.getResource("images/flags/kor.png"));
		CardImage[25] = new ImageIcon(cl.getResource("images/flags/nor.png"));
		CardImage[26] = new ImageIcon(cl.getResource("images/flags/por.png"));
		CardImage[27] = new ImageIcon(cl.getResource("images/flags/rus.png"));
		CardImage[28] = new ImageIcon(cl.getResource("images/flags/tur.png"));
		CardImage[29] = new ImageIcon(cl.getResource("images/flags/un.png"));
		CardImage[30] = new ImageIcon(cl.getResource("images/facedown.png"));

		// intilizing buttons to images

		CardButton = new JButton[numberOfCards + 1];
		for (int i = 0; i < numberOfCards; i++) {
			this.add(CardButton[i] = new JButton(CardImage[numberOfCards]));
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
		northFL2.add(RegisterButton);

		// Center Panel Setup
		// cP.add(FlipButton);
		cP.add(faildLogin);
		for (int i = 0; i < numberOfCards; i++) {
			cP.add(CardButton[i]);
		}

		// Sout Panel setup
		//sP.add(cred);
		sP.add(personalBestLabel);
		sP.add(personalBestScoreLabel);
		
		sP.add(scoreLabel);
		sP.add(playerScoreLabel);
		

		// Old Stuff
		/*
		 * this.add(WelcomeLabel); this.add(username); this.add(LoginButton);
		 * this.add(statusLabel2); this.add(faildLogin); this.add(FlipButton);
		 * this.add(faildLogin);
		 */

		// adding it all the ingredients the frame
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800, 800)); // New Framesize
		frame.setVisible(true);

	}
	/**
	 * 
	 * @param cl
	 */
	public void addCardListener(ActionListener cl) {

		for (int i = 0; i < numberOfCards; i++) {
			CardButton[i].setActionCommand("button" + i);
			CardButton[i].addActionListener(cl);
		}

	}

	//***** Action Listeners
	/**
	 * 
	 * @param ll
	 */
	public void addLoginListener(ActionListener ll) {
		LoginButton.addActionListener(ll);
	}
	/**
	 * 
	 * @param fl
	 */
	public void addFlipListener(ActionListener fl) {
		FlipButton.addActionListener(fl);
	}
	/**
	 * 
	 * @param mel
	 */
	public void addExitListener(ActionListener mel) { //Menu Exit Listener mel
		menuExit.addActionListener(mel);
	}
	/**
	 * 
	 * @param mrl
	 */
	public void addRegisterListener(ActionListener mrl) { //Menu Register Listener mrl
		menuRegister.addActionListener(mrl);
	}
	/**
	 * 
	 * @param mhl
	 */
	public void addHighScoreListener(ActionListener mhl) { //Menu HighScore Listener mhl
		menuHighScore.addActionListener(mhl);
	}
	/**
	 * 
	 * @param rbl
	 */
	public void addRegisterButtonListener(ActionListener rbl){ //Register Button Listener rbl
		rb_register.addActionListener(rbl);
	}
	/**
	 * 
	 * @param rbl2
	 */
	public void addRegisterButtonListener2(ActionListener rbl2){
		RegisterButton.addActionListener(rbl2);
	}
	/**
	 * 
	 * @param mrl
	 */
	public void addResetListener(ActionListener mrl){
		menuReset.addActionListener(mrl);
	}
	
	
	//*******************************************

	/**
	 * Sets the score of how many tries the player done in the south panel
	 * @param score
	 */
	public void setPlayerScoreLabel(int score){
		
		playerScoreLabel.setText(Integer.toString(score));
	}
	/**
	 * Sets the personalBestScore and updates the score on the south panel
	 * @param best
	 */
	public void setPersonalBest(int best){
		personalBestScore = best;
		personalBestScoreLabel.setText(Integer.toString(personalBestScore));
	}
	//*******************************************
	/**
	 * returns the username from loginform
	 * @return
	 */
	public String getLoginText() {
		String tmp = username.getText();
		return tmp;
	}
	/**
	 * Displays a dialog window with the highscore table.
	 * Sets visibility to true.
	 * @param userId
	 */
	public void displayHighScore(){
		dialog.setVisible(true);
	}
	/**
	 * Sets registers visibility to true
	 */
	public void displayRegister(){
		register.setVisible(true);
	}
	/**
	 * Returns the text from the textfield in the register field.
	 * @return
	 */
	public String getRegisterUsername(){
		return rt_username.getText();
	}
	/**
	 * Sets the register visibility to false
	 */
	public void hideRegister(){
		register.setVisible(false);
	}
	/**
	 * This method is used to generate error messages which will appear as "popups"
	 * The argument the method takes is the message that will be displayed for the user.
	 * @param error
	 */
	public void createError(String error){
		JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * This method is used to generate general message to the user which will appear as a popup for the user.
	 * The argument is the message which will be shown.
	 * @param message
	 */
	public void createMessage(String message){
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * set the new image for the button when clicked on
	 * if the two images are a match, they wont flip back.
	 * @param buttonNr
	 */
	public void flipImage(int buttonNr) {

		CardButton[buttonNr].setIcon(CardImage[buttonNr]);
		bothDrawn++;
		repaint();
		//Every-other time will it flip back
		if (pairChecker == true) {
			//System.out.println("if");
			pairNumber[1] = buttonNr;
			pairChecker = false;
			timerCard.start();
		} else if(pairChecker==false) {
			//System.out.println("elseif");
			pairNumber[0] = buttonNr;
			pairChecker = true;
		}
	}
	/**
	 * The Scrambled order from Memory is sent here to correspond
	 * the same order for the images.
	 * @param newCardOrderList
	 */
	public void setNewImageOrder(int[] newCardOrderList){
		ImageIcon tmp[] = new ImageIcon[numberOfCards];
		for (int i = 0; i < numberOfCards; i++) {
			//System.out.println(newCardOrderList[i]);
			tmp[i]=CardImage[i];
		}
		for (int i = 0; i < numberOfCards; i++) {
			CardImage[i] = tmp[newCardOrderList[i]];
		}
		/*
		CardImage[0] = tmp[newCardOrderList[0]];
		CardImage[1] = tmp[newCardOrderList[1]];
		CardImage[2] = tmp[newCardOrderList[2]];
		CardImage[3] = tmp[newCardOrderList[3]];
		CardImage[4] = tmp[newCardOrderList[4]];
		CardImage[5] = tmp[newCardOrderList[5]];
		CardImage[6] = tmp[newCardOrderList[6]];
		CardImage[7] = tmp[newCardOrderList[7]];
		CardImage[8] = tmp[newCardOrderList[8]];
		CardImage[9] = tmp[newCardOrderList[9]];
		*/
		repaint();
		//CardImage[10] = new ImageIcon("images/facedown.png");
	}

	/**
	 * This layout will append when a user is signed in.
	 * If you want to remove a button etc, set visible false here.
	 */
	public void loggedInLayout() {
		statusLabel2.setVisible(true);
		WelcomeLabel.setVisible(false);
		username.setVisible(false);
		LoginButton.setVisible(false);
		FlipButton.setVisible(true);
		faildLogin.setVisible(false);
		RegisterButton.setVisible(false);
		menuReset.setVisible(false);
		menuRegister.setVisible(false);

		for (int i = 0; i < numberOfCards; i++) {
			CardButton[i].setVisible(true);
		}
	}
	/**
	 * Method to display error message if logged in fails
	 */
	public void loggedInFaild() {
		faildLogin.setVisible(true);

		timerFaildLogin.start();
	}
	/**
	 * If two cards are drawn (face-up)
	 * @return
	 */
	public boolean checkIfBothDrawn(){
		if(bothDrawn>1)
			return true;
		else
			return false;
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timerCard.stop();
			CardButton[pairNumber[0]].setIcon(CardImage[numberOfCards]);
			CardButton[pairNumber[1]].setIcon(CardImage[numberOfCards]);
			repaint();
			bothDrawn=0;
		}
	}
	/**
	 */
	private class TimerListenerLogin implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			faildLogin.setVisible(false);
			timerFaildLogin.stop();
		}
	}
	/**
	 * if found-pair occurs, this happens instead of flipImage
	 * @param buttonNr
	 */
	public void flipPair(int buttonNr) {
			CardButton[buttonNr].setIcon(CardImage[buttonNr]);
			bothDrawn=0;
			repaint();
			//Every-other time will it flip back
			if (pairChecker == true) {
				//System.out.println("if");
				//pairNumber[1] = buttonNr;
				pairChecker = false;
			}
		
	}

}