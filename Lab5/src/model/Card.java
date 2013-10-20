package model;
import java.io.Serializable;

//import javax.swing.ImageIcon;

public class Card implements Serializable{
	private int id;
	//private ImageIcon icon[], standardCard;
	private boolean found;
	public Card(int id) {
		//standardCard = new ImageIcon("images/facedown.png");
		this.id=id;
		//this.icon[0]=standardCard;
		//this.icon[1]=image;
	}
	
	public int getId(){
		return id;
	}
	
	public void setFound(boolean found){
		this.found=found;
	}
	
	
	public boolean getFound(){
		return found;
	}
	
	/*public ImageIcon getImage(){
		return this.icon[1];
	}*/
	

}
