package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

public class Memory {
	//private int done;
	private int numberOfCards=30;
	
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	private ArrayList<Card> newOrder = new ArrayList<Card>();

	public Memory() throws IOException, ClassNotFoundException {
		reset();
	}
	/**
	 * Just addes the user as requested with the parms told...
	 * @param id
	 * @param username
	 * @param score
	 */
	public void addUser(int id, String username, int score){
		User adding = new User(id, username, score);
		users.add(adding);
	}
	/**
	 * This savefile method saves the Users in the same catalog as the Jar file
	 * 
	 * @param saveFile
	 * @throws IOException
	 */
	public void save(String saveFile) throws IOException {
		//ClassLoader cl = this.getClass().getClassLoader();
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fout = new FileOutputStream(saveFile);
			//FileOutputStream fout = new cl.getResourceAsStream(saveFile);
			//OutputStream fout =  cl.getResourceAsStream(saveFile);
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
	/**
	 * This load file expects the file to be in the same catalog as the jar-file
	 * @param loadFile
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void load(String loadFile) throws ClassNotFoundException, IOException {
		//ClassLoader cl = this.getClass().getClassLoader();
		ObjectInputStream ois = null;

		try {
			FileInputStream fin = new FileInputStream(loadFile);
			//InputStream fin = cl.getResourceAsStream(loadFile);
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
	/**
	 * resets and restarts the game
	 * this is not yet implemented
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void reset() throws IOException, ClassNotFoundException {
		// Reset everything but the score
		load("test.lst");
		
		int k=0;
		for (int j = 0; j < 2; j++) {
		for (int i = 0; i < numberOfCards/2; i++) {
			
			//cards.add(new Card(k,i));//k goes from 0 to 9 for 10 cards
			cards.add(new Card(k,i));//And i goes from 0 to 4 (two times) for 10 cards
			//System.out.println("k: "+k+" i:"+i);
			k++;
		}
			
		}
		for (int i = 0; i < numberOfCards; i++) {
			//System.out.println(cards.get(i).getPairId());
		}
		//System.out.println("");
		//System.out.println("-----------------");
		//System.out.println("");
		shuffleCards();
		for (int i = 0; i < numberOfCards; i++) {
			newOrder.add(cards.get(i));
			//System.out.println(cards.get(i).getId());
		}
		//System.out.println("");
		//System.out.println("-----------------");
		//System.out.println("");
		for (int i = 0; i < numberOfCards; i++) {
			//System.out.println("nr: " + i +  ", " + cards.get(i).getPairId());
		} 
		
		
	}

	/**
	 * Check if both of the cards are in pair with eachother
	 * @param card1
	 * @param card2
	 * @return
	 */
	public boolean checkIfPair(Card card1,Card card2) {

		if(card1.getPairId()==card2.getPairId()){
			//System.out.println(card1.getPairId());
			//System.out.println(card2.getPairId());
			return true;
		}
		else{
			return false;
		}
		
	}
	/**
	 * This method checks if the game is completed by checking if all the cards are turned
	 * the last two cards in memory will always be a pair
	 * @return
	 */
	public boolean checkIfDone() {
		int numberOfFound=0;
		for (int i = 0; i < (cards.size()); i++) {
			if(cards.get(i).getFound()){
				numberOfFound++;
				if(numberOfFound==cards.size())
					return true;
			}
		}
		return false;
		
	}
	/**
	 * Checks if the username already exists in the loaded data from the files
	 * @param name
	 * @return 
	 * true or false
	 */
	public boolean checkIfDuplicate(String name){
		int hit = 0;
		for(int i=0; i< users.size(); i++){
			if(name.equals(users.get(i).getName().toString())){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 */
	public void saveUserInfo() {

	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Card> getCards() { // I know im sending the acutal array of cards and not a copy.. thats the point
		return cards;
	}
	public int getNoOfUsers(){
		return users.size();

	}

	/**
	 * 
	 */
	public void shuffleCards(){
		Collections.shuffle(cards);
	}
	/**
	 * returns the new card order when shuffle this is needed
	 * @return
	 */
	public ArrayList<Card> getNewCardOrder(){
		return newOrder;
	}
/*
	public void addScore(int id){
		users.get(id).addScore();

	}*/
	
	/**
	 * what happens when we login
	 * @param inputText
	 */
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
