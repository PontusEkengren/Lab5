package model;

import java.util.*;

public class Memory {
	
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	

	public Memory() {
		reset();
	}
	
	public void reset() {
	//Reset everything but the score
		User temp = new User(1, "Kevin", 0);
		users.add(temp);
		
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
