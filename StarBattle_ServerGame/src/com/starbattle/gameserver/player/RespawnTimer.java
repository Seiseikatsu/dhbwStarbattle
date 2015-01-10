package com.starbattle.gameserver.player;

import java.util.Timer;
import java.util.TimerTask;

public class RespawnTimer {

	private Timer timer;
	private int respawnTime;
	
	public RespawnTimer()
	{
		
	}
	
	public void startRespawnTimer(int time)
	{
		respawnTime=time;
		timer=new Timer();
		timer.schedule(new TimerUpdate(), time);
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
				this.cancel();		
			}
		}
		
	}
	
}
