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
		ypos = +y;
	}

	public float getXpos() {
		return xpos;
	}

	public float getYpos() {
		return ypos;
	}

}
