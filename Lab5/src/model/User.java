package model;
import java.io.Serializable;

public class User implements Serializable{

	private double id;
	private String name;
	private int score;

	public User(int id, String name, int score) {
		this.id = id;
		this.name = name;
		this.score = score;
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

	public double getId() {
		return id;
	}
	
	public String toString() {
		return "Id: " + getId() + ", Name: " + getName() + ", Score: "
				+ getScore();
	}

}
