package com.starbattle.gameserver.game.physics;

public class Location {

	private float xpos, ypos;

	public Location() {
	}

	public Location(float x, float y) {
		xpos = x;
		ypos = y;
	}

	public void moveX(float x) {
		xpos += x;
	}

	public void moveY(float y) {
		ypos += y;
	}

	public float getXpos() {
		return xpos;
	}

	public float getYpos() {
		return ypos;
	}

	public void moveTo(Location l) {
		this.xpos=l.getXpos();
		this.ypos=l.getYpos();
	}

	public Location copy() {
		return new Location(xpos,ypos);
	}

	public void flatY() {
		ypos= (int)Math.floor(ypos);
	}

	public void moveInDirection(Location targetLocation, float f) {
		float x=targetLocation.getXpos();
		float y=targetLocation.getYpos();
		
		
	}

}
