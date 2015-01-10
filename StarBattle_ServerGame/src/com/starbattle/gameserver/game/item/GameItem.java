package com.starbattle.gameserver.game.item;

public class GameItem {

	public final static int ITEM_FLAG_BLUE=0;
	public final static int ITEM_FLAG_RED=1;
	
	
	private float x,y;
	private int itemID;
	private boolean active;
	private int cooldown;
	private boolean infiniteCooldown=false;
	
	public GameItem(int id, float x, float y)
	{
		this.itemID=id;
		this.x=x;
		this.y=y;
	}
	
	public void collect(int cooldown)
	{
		if(active)
		{
			active=false;
			this.cooldown=cooldown;
		}
	}
	
	public void collect()
	{
		if(active)
		{
			active=false;
			infiniteCooldown=true;
		}
	}
	
	public void respawn()
	{
		active=true;
		cooldown=0;
		infiniteCooldown=false;
	}
	
	public void updateCooldown()
	{
		if(!active&&!infiniteCooldown)
		{
		cooldown--;
		if(cooldown==0)
		{
			respawn();//self respawn after cooldown
		}
		}
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public boolean isActive() {
		return active;
	}
	
}
