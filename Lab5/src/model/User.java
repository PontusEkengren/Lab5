package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {

	private int id;
	private String name;
	private int score;
	private int highscore;
/**
 * 
 * @param id 
 * is used to identify the User
 * @param name
 * Player name
 * @param score
 * Score. Like Golf you want as few of theese as possible
 */
	public User(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	/**
	 * 
	 * @param hscore
	 */
	public void setHighScore(int hscore){
		highscore = hscore;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHighScore(){
		if(highscore < 100){
			return highscore;
		}
		return 100;
	}
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 */
	public void addScore() {
		score += 1;
	}
	/**
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * 
	 */
	public String toString() {
		return "Id: " + getId() + ", Name: " + getName() + ", Score: "
				+ getScore();
	}
	public String toLowString() {
		return getId() + "," + getName() + "," + getScore() + ";";
	}

}
