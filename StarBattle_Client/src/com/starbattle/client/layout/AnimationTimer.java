package com.starbattle.client.layout;

import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class AnimationTimer {

	private Timer timer;
	private ActionListener actionListener;
	
	public AnimationTimer()
	{
		timer=new Timer();
	}
	
	public void start(ActionListener actionListener, int fps)
	{
		this.actionListener=actionListener;
		timer.schedule(new AnimationTask(), 0,1000/fps);
	}
	
	private class AnimationTask extends TimerTask{

		@Override
		public void run() {
			actionListener.actionPerformed(null);
		}
		
	}
	
}
