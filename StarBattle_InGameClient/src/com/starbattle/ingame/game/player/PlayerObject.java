package com.starbattle.ingame.game.player;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.network.connection.objects.game.NP_PlayerData;

public class PlayerObject {

	private Location location=new Location();
	private float weaponangle;
	private String name;
	private int team;
	private PlayerDisplay display;
	
	public PlayerObject(String name, int team)
	{
		this.name=name;
		this.team=team;
		display=new PlayerDisplay();
	}
	
	public void update(float delta)
	{
		
	}
	
	public void update(NP_PlayerData data) {
		
		
		location.jumpTo(data.xpos, data.ypos);
	}
	
	public Location getLocation() {
		return location;
	}
	
	public PlayerDisplay getDisplay() {
		return display;
	}
	
	public String getName() {
		return name;
	}

	public int getTeam() {
		return team;
	}
}
