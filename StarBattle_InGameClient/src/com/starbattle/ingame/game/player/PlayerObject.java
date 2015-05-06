package com.starbattle.ingame.game.player;

import com.starbattle.ingame.game.input.MouseCursor;
import com.starbattle.ingame.game.location.Location;
import com.starbattle.network.connection.objects.game.NP_PlayerData;

public class PlayerObject {

	private Location location=new Location();
	private float weaponangle;
	private String name;
	private int team;
	private PlayerDisplay display;
	private float xspeed,yspeed;
	
	public PlayerObject(String name, int team)
	{
		this.name=name;
		this.team=team;
		display=new PlayerDisplay();
	}
	
	public void update(float delta)
	{
		//location.move(xspeed, yspeed);
		display.updateBodyAnimation(delta,xspeed,yspeed);
		
		
	}
	
	public void update(NP_PlayerData data) {
		
		boolean facing=data.facingLeft;
		display.setLookingLeft(facing);
		this.xspeed=data.xspeed;
		this.yspeed=data.yspeed;
		location.jumpTo(data.xpos, data.ypos);
	}
	
	public float getWeaponAngle() {
		return weaponangle;
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

	public void updateWeaponAngle(MouseCursor mouseCursor) {
		this.weaponangle=mouseCursor.getWeaponAnlge();
	}
}
