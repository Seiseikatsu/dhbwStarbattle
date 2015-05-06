package com.starbattle.ingame.game.location;

public class Location {

	private float xpos, ypos;

	public Location() {
	}

	public Location(float x, float y) {
		xpos = x;
		ypos = y;
	}

	public Location(Location l) {
		xpos = l.getXpos();
		ypos = l.getYpos();
	}

	public void move(float x, float y) {
		xpos += x;
		ypos += y;
	}

	public void move(Location l) {
		xpos += l.getXpos();
		ypos += l.getYpos();
	}
	
	public void subtract(Location l)
	{
		xpos=l.getXpos()-xpos;
		ypos=l.getYpos()-ypos;
		
//		xpos -= l.getXpos();
	//	ypos -= l.getYpos();
	}

	public void jumpTo(float x, float y) {
		xpos = x;
		ypos = y;
	}

	public void jumpTo(Location location) {
		xpos = location.getXpos();
		ypos = location.getYpos();
	}

	public float getXpos() {
		return xpos;
	}

	public float getYpos() {
		return ypos;
	}

	
	@Override
	public String toString() {
		return xpos+" x "+ypos;
	}

	public void factor(float factor) {
		xpos*=factor;
		ypos*=factor;
	}
}
