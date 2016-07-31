package resource;

public class Grid {
	
	private int aHeight;
	private int aWidth;
	
	public Grid(){
		aHeight = 100;
		aWidth = 100;
	}
	
	
	public void autoAdujust(int pAdjusment){
		aHeight *= pAdjusment;
		aWidth *= pAdjusment;
	}
}
