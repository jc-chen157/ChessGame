package backend.player;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerTimer {
	
	private int aMinute = 0;
	private int aSecond = 0;
	StringProperty property = new SimpleStringProperty();
	
	public PlayerTimer(){
		aMinute = 30;
		aSecond = 0;
		property.set("0 : " + aMinute +" : " + aSecond);
	}
	
	public void resetTimer(){
		aMinute = 30;
		aSecond = 0;
		property.set("0 : " + aMinute +" : " + aSecond);
	}
	
	public void setTimer(int pMinute, int pSecond) {
		aMinute = pMinute;
		aSecond = pSecond;
	}
	
	public int getMinute(){
		return aMinute;
	}
	
	public int getSecond(){
		return aSecond;
	}
	
	public StringProperty getStringProperty(){
		return property;
	}
	
	@Override
	public String toString(){
		return "0 : " + aMinute +" : " + aSecond;
	}
	
	public void tick() {
		while(aMinute != 0 || aSecond != 0){
			if(aSecond == 0){
				aMinute--;
				aSecond = 59;
			}else{
				aSecond--;
			}
			break;
		}
		property.set("0 : " + aMinute +" : " + aSecond);
	}
	
	public boolean isFinished(){
		return aMinute == 0 && aSecond == 0;
	}
}
