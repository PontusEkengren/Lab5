package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Memory {

	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Card> cards = new ArrayList<Card>();

	public Memory() throws IOException, ClassNotFoundException {
		reset();

	}
	public void addUser(int id, String username, int score){
		User adding = new User(id, username, score);
		users.add(adding);
	}

	public void save(String saveFile) throws IOException {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fout = new FileOutputStream(saveFile);
			oos = new ObjectOutputStream(fout);

			oos.writeObject(users);

			System.out.println("Serializing successfully completed");
		} finally {
			try {
				if (oos != null)
					oos.close();
			} finally {
				oos.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void load(String loadFile) throws ClassNotFoundException,
			IOException {

		ObjectInputStream ois = null;

		try {
			FileInputStream fin = new FileInputStream(loadFile);
			ois = new ObjectInputStream(fin);

			users = (ArrayList<User>) ois.readObject();
		} catch (IOException e) {
			save(loadFile);
			load(loadFile);
		} catch (ClassNotFoundException e) {
			save(loadFile);
			throw e;
		}
	}

	public void reset() throws IOException, ClassNotFoundException {
		// Reset everything but the score
		load("test.lst");
		for (int i = 0; i < 10; i++) {
			cards.add(new Card(i));
					}
	}


	public boolean checkIfPair() {
		return false;
	}

	public boolean checkIfDone() {
		return false;
	}

	public void saveUserInfo() {

	}

	public ArrayList<User> getUsers() {
		return users;
	}
	public int getNoOfUsers(){
		return users.size();
	}

	public void loginButton(String inputText) {

		Iterator<User> itr = users.iterator();
		int u = 0;
		while (itr.hasNext()) {

			if (inputText == users.get(u).getName()) {
				u += 1;
			} // loggedInLayout(); i userinterface ska k'o'ras

			else {
				u += 1;
			} // faildLogin.setVisible(true); ska k'o'ras i user interface
		}
	}

}
