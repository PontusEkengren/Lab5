package model;
import java.io.Serializable;

//import javax.swing.ImageIcon;

public class Card implements Serializable{
	private int id;
	private int pairId;
	//private ImageIcon icon[], standardCard;
	private boolean found;
	public Card(int id,int pairId) {
		//standardCard = new ImageIcon("images/facedown.png");
		this.id=id;
		this.pairId=pairId;
		//this.icon[0]=standardCard;
		//this.icon[1]=image;
	}
	/**
	 * 
	 * @return
	 */
	public int getId(){
		return id;
	}
	/**
	 * 
	 * @return
	 */
	public int getPairId(){
		return pairId;
	}
	/**
	 * 
	 * @param found
	 */
	public void setFound(boolean found){
		this.found=found;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getFound(){
		return found;
	}
	
	/*public ImageIcon getImage(){
		return this.icon[1];
	}*/
	

}
