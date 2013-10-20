package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterface extends JPanel {

	private Timer timer;
	private int timerTime = 2000; // timer delay in ms this makes 2 seconds
	private JLabel WelcomeLabel, statusLabel2, faildLogin;
	private JTextField username;
	private JButton LoginButton, FlipButton;
	private JButton CardButton[];
	private ImageIcon faceup, facedown, current;
	private ImageIcon CardImage[];

	public UserInterface() {
		super();

		// loading tthe panel
		JFrame frame = new JFrame("Memory");

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
		//connecting array of imagesIcons to actuall images
		CardImage=new ImageIcon[10];
		CardImage[0]= new ImageIcon("images/DIGIT_0.gif");
		CardImage[1]= new ImageIcon("images/DIGIT_1.gif");
		CardImage[2]= new ImageIcon("images/DIGIT_2.gif");
		CardImage[3]= new ImageIcon("images/DIGIT_3.gif");
		//intilizing buttons to images
		CardButton=new JButton[10];
		for (int i = 0; i < 4; i++) {
			this.add(CardButton[i] = new JButton(CardImage[i]));
			CardButton[i].setVisible(false);
		}
		timer = new Timer(timerTime, new TimerListener());

		// Some things are not default visible
		statusLabel2.setVisible(false);
		FlipButton.setVisible(false);
		faildLogin.setVisible(false);
		// We're going to need these to actually see them
		this.add(WelcomeLabel);
		this.add(username);
		this.add(LoginButton);
		this.add(statusLabel2);
		this.add(faildLogin);
		this.add(FlipButton);
		this.add(faildLogin);

		// adding it all the ingredients the frame
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(800, 600));
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
		
		for (int i = 0; i < 4; i++) {
			CardButton[i].setVisible(true);
		}
			
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