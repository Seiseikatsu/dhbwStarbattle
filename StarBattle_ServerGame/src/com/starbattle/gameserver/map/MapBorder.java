package com.starbattle.gameserver.map;

import org.newdawn.slick.geom.Rectangle;

import com.starbattle.gameserver.game.physics.Location;

public class MapBorder {

	public final static int BORDER_TILE_SIZE = 10;
	private Rectangle rect;

	public MapBorder(int w, int h) {

		rect = new Rectangle(-BORDER_TILE_SIZE, -BORDER_TILE_SIZE, w + BORDER_TILE_SIZE*2, h + BORDER_TILE_SIZE*2);
	}

	public boolean isInBorder(Location l) {
		float xp = l.getXpos();
		float yp = l.getYpos();
		return rect.contains(xp, yp);
	}

	public boolean isBelowBorder(Location l) {
		float y = l.getYpos();
		return y > rect.getMaxY();
	}
}
