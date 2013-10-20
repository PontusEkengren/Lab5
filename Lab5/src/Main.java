import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import controller.MemoryController;
import model.Memory;
import view.UserInterface;

public class Main {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		Main main = new Main();
		main.init();
	}

	private void init() throws IOException, ClassNotFoundException {

		Memory theModel = new Memory();
		UserInterface theView = new UserInterface();
		MemoryController theController = new MemoryController(theView, theModel);
		theView.setVisible(true);

	}
}
