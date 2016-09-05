package user.Resource;

public class Player {
	private String aName;
	
	public Player(String pName){
		aName = pName;
	}
	
	public String getName(){
		return aName;
	}
	
	public void setName(String pName){
		aName = pName;
	}
}
