/** Programmet demonstrerar hur ett javax.swing.Timer-objekt
 *  kan användas för att utföra något med fördröjning.
 *  Timer-metoderna start() och stop() anropas för att starta
 *  respektive stoppa Timern.
 *
 *  Detta kan t ex användas i spelet Memory så att när spelaren
 *  vänder en bricka vänds den automatiskt tillbaka efter en 
 *  bestämd tid.
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
class FlipPanel extends JPanel {
	
	private Timer timer;
	private ImageIcon not, cross, present_icon;
	private JButton flip;
	private int delay = 2000; // Fördröjning i ms
	
	// Konstruktor
	public FlipPanel() {
		super();
		
		/* Ladda in bilderna - ska göras under initeringen.
		 */
		cross 	= new ImageIcon("images/cross.gif");
		not 	= new ImageIcon("images/not.gif");
		present_icon = not;

		flip = new JButton("Flip image");
		this.add(flip);
		flip.addActionListener(new ButtonListener());
		
		/* Timern med lyssnare. 
		 * Delay är här valt til 2000 millisekunder.
		 */
		timer = new Timer(delay, new TimerListener());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (this.getWidth() - present_icon.getIconWidth())/2;
		int y = (this.getHeight() - present_icon.getIconHeight())/2;
		present_icon.paintIcon(this, g, x, y);
	}
	
	/** Knapplyssnaren: Om knappen trycks ska kryss-bilden visas och 
	 *  timern startas.
	 */
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			present_icon = cross;
			timer.start();
			repaint();
		}
	}
	
	/** Timerlyssnaren: När timer genererar ett ActionEvent (här - 
	 *  efter 2000 ms) ska ringbilden visas igen och timern stoppas.
	 */
	private class TimerListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			timer.stop();
			present_icon = not;
			repaint();
		}
	}
}

public class DelayWithTimer {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("DelayWithTimer");
		frame.getContentPane().add(new FlipPanel());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.setVisible(true);
	}
}