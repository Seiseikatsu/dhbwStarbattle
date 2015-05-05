package com.starbattle.ingame.game.viewport;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.player.PlayerObject;

public class Viewport {

	private int width, height;
	private Location location = new Location();

	public Viewport(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public void view(PlayerObject player) {
		location.jumpTo(player.getLocation());
	}

	public void scoll(float x, float y) {
		location.move(x, y);
	}

	public Location getScreenLocation(Location relative) {
		Location newLocation = new Location(location);
		newLocation.subtract(relative);
		newLocation.move(width/2, height/2);	
		return newLocation;
	}

	public float getXpos() {
		return location.getXpos();
	}

	public float getYpos() {
		return location.getYpos();
	}

	public int getMapX() {
		return (int) -location.getXpos() + (width / 2);
	}

	public int getMapY() {
		return (int) -location.getYpos() + (height / 2);
	}
	
	@Override
	public String toString() {
		return "Viewport  @ "+location.toString();
	}

}
