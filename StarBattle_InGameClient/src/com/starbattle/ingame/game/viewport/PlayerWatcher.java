package com.starbattle.ingame.game.viewport;


import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.player.PlayerContainer;

public class PlayerWatcher {

	private PlayerContainer players;
	private Location location;
	
	public PlayerWatcher(PlayerContainer players) {
		this.players=players;

	}
	
	private float radius,angle;
	
	public void onDeath()
	{
		location=players.getMyPlayer().getLocation().copy();
		radius=0;
		angle=0;
	}
	

	public boolean isWatchingPlayer()
	{
		if(players.getMyPlayer().getHealth()>0)
		{
			return false;
		}
		return true;
	}
	
	public Location getWatchingLocation()
	{
		//update watching location
		radius+=0.0001f;
		angle+=0.01f;
		float xp=(float) (Math.cos(angle)*radius);
		float yp=(float) (Math.sin(angle)*radius);
		location.move(xp,yp);
		return location;
	}

	
}
