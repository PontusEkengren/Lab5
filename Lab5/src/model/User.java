package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {

	private int id;
	private String name;
	private int score;
	private int highscore;

	public User(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
	}
	public void setHighScore(int hscore){
		highscore = hscore;
	}
	public int getHighScore(){
		if(highscore < 100){
			return highscore;
		}
		return 100;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addScore() {
		score += 1;
	}

	public int getScore() {
		return score;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "Id: " + getId() + ", Name: " + getName() + ", Score: "
				+ getScore();
	}
	public String toLowString() {
		return getId() + "," + getName() + "," + getScore() + ";";
	}

}
