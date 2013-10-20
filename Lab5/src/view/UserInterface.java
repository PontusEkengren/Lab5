package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterface extends JPanel {

	private Timer timer;
	private int timerTime = 2000; // timer delay in ms this makes 2 seconds
	private JLabel WelcomeLabel, statusLabel2, faildLogin, test, cred;
	private JTextField username;
	private JButton LoginButton, FlipButton, CardButton[];
	private ImageIcon CardImage[], faceup, facedown, current;
	private JPanel cP, nP, sP, pP, northFL1, northFL2;
	private BorderLayout bl;
	private GridLayout gl;
	private BoxLayout boxL;
	private FlowLayout fL;

	public UserInterface() {
		super();

		// loading the panel
		JFrame frame = new JFrame("Memory");
		
		//Panels
		cP = new JPanel();	//Center Panel
		nP = new JPanel();	//North Panel
		sP = new JPanel();	//South Panel
		pP = new JPanel();	//Panel Panel, Maybe for the memorygame if we switch visibility of login and the game
		northFL1 = new JPanel();
		northFL2 = new JPanel(); //Just so structure the north login button NEW
		
		//Layouts
		bl = new BorderLayout();
		bl.setHgap(50);	//Sets how many pixel it should be between every object(Horisontal)
		bl.setVgap(50);	// - || - (Vertical)
		gl = new GridLayout(4,5);
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
		//connecting array of imagesIcons to actuall images
		
		//intilizing buttons to images
		/*for (int i = 0; i < 10; i++) {
			CardButton[i] = new JButton(CardImage[i]);
		}*/
		timer = new Timer(timerTime, new TimerListener());

		// Some things are not default visible
		statusLabel2.setVisible(false);
		FlipButton.setVisible(false);
		faildLogin.setVisible(false);
		
		// We're going to need these to actually see them
		this.setLayout(bl);
		this.add(cP, bl.CENTER);	//Central Panel to BorderLayout CENTER
		this.add(nP, bl.NORTH);		//North Panel to BorderLayout NORTH
		this.add(sP, bl.SOUTH);		//South Panel to BorderLayout SOUTH
		
		//North Panel setup
		nP.setLayout(boxL);
		nP.add(northFL1);
		northFL1.add(WelcomeLabel);
		nP.add(northFL2); //Panel med FlowLayout
		//NY KOD
		northFL2.add(username);
		northFL2.add(LoginButton);
		
		//Center Panel Setup
		cP.add(FlipButton);
		cP.add(faildLogin);
		
		//Sout Panel setup
		sP.add(cred);
		
		//Old Stuff
		/*this.add(WelcomeLabel);
		this.add(username);
		this.add(LoginButton);
		this.add(statusLabel2);
		this.add(faildLogin);
		this.add(FlipButton);
		this.add(faildLogin);*/

		// adding it all the ingredients the frame
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800, 600)); //Set dimensions of the main frame
		frame.setVisible(true);

	}
	

	public void addLoginListener(ActionListener ll) {
		LoginButton.addActionListener(ll);
	}

	public void addFlipListener(ActionListener fl) {
		FlipButton.addActionListener(fl);
	}

	public String getLoginText() {
		String tmp = username.getText();
		return tmp;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (this.getWidth() - current.getIconWidth()) / 2;
		int y = (this.getHeight() - current.getIconHeight()) / 2;
		current.paintIcon(this, g, x, y);
	}

	public void flipImage() {

		current = faceup;
		repaint();
		timer.start();
	}

	public void loggedInLayout() {
		statusLabel2.setVisible(true);
		WelcomeLabel.setVisible(false);
		username.setVisible(false);
		LoginButton.setVisible(false);
		FlipButton.setVisible(true);
		faildLogin.setVisible(false);
		// repaint();
	}

	public void loggedInFaild() {
		faildLogin.setVisible(true);

		// repaint();
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			current = facedown;
			repaint();
		}
	}

}