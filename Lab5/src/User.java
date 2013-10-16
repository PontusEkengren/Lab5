
public class User {
	
	private double id;
	private String name;
	private int score;
	
	public void setName(String name){
		this.name =name;
	}

	public String getName(){
	return name;
	}
	public void addScore(){
		score+=1;
	}	
	public int getScore(String name){
	return score;
	}	
	
	public void setId(int id){
		this.id =id;
	}
	
	public double getId(){
	return id;
	}	

}
