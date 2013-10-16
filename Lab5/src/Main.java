import java.awt.Dimension;

import javax.swing.JFrame;
import model.Memory;
import view.UserInterface;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.init();
	}

	private void init() {
		
		Memory model = new Memory();
		UserInterface panel = new UserInterface(model);
		JFrame frame = new JFrame("Memory"); 
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500, 600));
		frame.setVisible(true);

	}
}
