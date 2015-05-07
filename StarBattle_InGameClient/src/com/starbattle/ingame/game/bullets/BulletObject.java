package com.starbattle.ingame.game.bullets;

import com.starbattle.ingame.game.location.Location;

public class BulletObject {

	private BulletDesign bulletDesign;
	private Location location;
	private float angle,speed;
	
	public BulletObject(BulletDesign bulletDesign, Location location) {
		
		this.bulletDesign=bulletDesign;
		this.location=location;
	}
	
	public void setMovement(float angle, float speed)
	{
		this.angle=angle;
		this.speed=speed;
	}
	
	public void update(float delta)
	{
		location.movePolar(angle,speed*delta);
	}
	
	public BulletDesign getBulletDesign() {
		return bulletDesign;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public float getSpeed() {
		return speed;
	}
}
