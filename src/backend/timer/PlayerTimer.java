package backend.timer;

import app.middleware.TimerModel;

/**
 * Timer which keeps track of the remaining time for the player
 * @author JiajunChen
 *
 */
public final class PlayerTimer extends Thread {
	
	private int aMinute = 0;
	private int aSecond = 0;
	private boolean isFinished = false;
	
	public PlayerTimer(){
		aMinute = 30;
		aSecond = 0;
	}
	
	public void resetTimer(){
		aMinute = 30;
		aSecond = 0;
		isFinished = false;
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
	
	@Override
	public String toString(){
		return "0 : " + aMinute +" : " + aSecond;
	}
	
	@Override
	public void run() {
		aMinute--;
		while(aMinute != 0 || aSecond != 0){
			if(aSecond == 0){
				aMinute--;
				aSecond = 59;
			}
			aSecond--;
			pause(1000);
		}
		isFinished = true;
	}
	
	public boolean isFinished(){
		return isFinished;
	}
	
	private void pause(long sleeptime) {
	    try{
	    	Thread.yield();
	    	/*
	    	 * TODO: Find a better solution to update the UI.
	    	 */
	    	TimerModel.getInstance().updateUI();
	        Thread.sleep(sleeptime);
	    }catch (InterruptedException ex){
	    	
	    }
	}
	
}
