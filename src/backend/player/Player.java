package backend.player;

public class Player {
	
	private String aName;
	private int aScore;
	private Title aTitle;
	private String aProfilePicture;
	
	public Player(String pName){
		aName = pName;
		aScore = 0;
		aTitle = Title.NOOB;
		aProfilePicture = null;
	}
	
	public String getName(){
		return aName;
	}
	
	public void setName(String pName){
		aName = pName;
	}
	
	public int getScore(){
		return aScore;
	}
	
	public void scoreIncrease(){
		aScore++;
		adjustTitle(aScore);
	}
	
	public void scoreDecrease(){
		aScore--;
		adjustTitle(aScore);
	}
	
	public Title getTitle(){
		return aTitle;
	}
	
	public void setPicturePath(String pPath){
		aProfilePicture = pPath;
	}
	
	public String getPicturePath(){
		return aProfilePicture;
	}
	
	private void adjustTitle(int pScore){
		
	}
}
