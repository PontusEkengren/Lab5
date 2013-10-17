package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class Memory {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	

	public Memory() throws IOException {
		reset();
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
	
	public void reset() throws IOException {
	//Reset everything but the score
		User temp = new User(1, "Kevin", 0);
		users.add(temp);
		save("test.lst");
		
	}
	
	public void turnCard() {
		
	}

	public boolean checkIfPair() {
		return false;
	}
	
	public boolean checkIfDone() {
		return false;
	}
	
	public void saveUserInfo() {
		
	}


}
