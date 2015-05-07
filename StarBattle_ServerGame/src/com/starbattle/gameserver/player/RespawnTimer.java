package com.starbattle.gameserver.player;

import java.util.Timer;
import java.util.TimerTask;

public class RespawnTimer {

	private Timer timer;
	private int respawnTime;
	private RespawnListener listener;
	private boolean running=false;
	
	public RespawnTimer()
	{
		
	}
	
	public void startRespawnTimer(int time, RespawnListener listener)
	{
		this.listener=listener;
		respawnTime=time;
		timer=new Timer();
		timer.schedule(new TimerUpdate(),0, 1000);
		running=true;
	}
	
	public void cancelTimer()
	{
		if(timer!=null)
		{
			timer.cancel();
			timer=null;
		}
	}
	
	private class TimerUpdate extends TimerTask{

		@Override
		public void run() {
			respawnTime--;
			if(respawnTime==0)
			{
				running=false;
				listener.doRespawn();
				this.cancel();		
			}
		}
		
	}
	
	public int getRespawnTime() {
		return respawnTime;
	}

	public boolean isRunning() {
		return running;
	}
	
}
