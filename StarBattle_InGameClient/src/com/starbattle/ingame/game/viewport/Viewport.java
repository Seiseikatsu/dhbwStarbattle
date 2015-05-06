package com.starbattle.ingame.game.viewport;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.player.PlayerObject;

public class Viewport {

	private float width, height;
	private Location location = new Location();

	public Viewport(int w, int h) {
		this.width = w/(float)GameMap.TILE_SIZE/2f;
		this.height = h/(float)GameMap.TILE_SIZE/2f;

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
		newLocation.move(width, height);	
		newLocation.factor(GameMap.TILE_SIZE);
		return newLocation;
	}

	public float getXpos() {
		return location.getXpos();
	}

	public float getYpos() {
		return location.getYpos();
	}

	public int getMapX() {
		return (int) ((-location.getXpos() + width)*GameMap.TILE_SIZE);
	}

	public int getMapY() {
		return (int) ((-location.getYpos() + height)*GameMap.TILE_SIZE);
	}
	
	@Override
	public String toString() {
		return "Viewport  @ "+location.toString();
	}

}
