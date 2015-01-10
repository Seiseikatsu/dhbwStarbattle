package com.starbattle.gameserver.game.item;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ItemContainer {

	private ArrayList<GameItem> items=new ArrayList<GameItem>();
	private Timer updateTimer=new Timer();
	
	public ItemContainer()
	{
		updateTimer.schedule(new UpdateTimerTask(), 0, 1000); //update every seconds all item cooldowns
	}
	
	public void placeItem(int id, float x, float y)
	{
		items.add(new GameItem(id, x, y));
	}
	
	public ArrayList<GameItem> getItems() {
		return items;
	}
	
	private class UpdateTimerTask extends TimerTask{

		@Override
		public void run() {
			for(int i=0; i<items.size(); i++)
			{
				items.get(i).updateCooldown();
			}
		}
		
	}
}
