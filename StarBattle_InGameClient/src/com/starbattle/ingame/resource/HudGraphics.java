package com.starbattle.ingame.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.starbattle.ingame.resource.player.ResourceException;

public class HudGraphics {

	private Image airHUD, cursor;
	private SpriteSheet medals;
	public final static String PATH = ResourceContainer.PATH + "hud/";

	public HudGraphics() {

	}

	public void load(ResourceGarbageCollector resourceGarbageCollector) throws ResourceException {

		airHUD = loadImage("airBar.png");
		cursor = loadImage("cursor.png");
		medals = new SpriteSheet(loadImage("medals.png"), 32, 32);
		
		resourceGarbageCollector.collect(airHUD);
		resourceGarbageCollector.collect(cursor);
		resourceGarbageCollector.collect(medals);
	}

	private Image loadImage(String name) throws ResourceException {
		try {
			System.out.println("Loading HudImage: " + name);
			return new Image(HudGraphics.PATH + name);
		} catch (SlickException e) {
			e.printStackTrace();
			throw new ResourceException("Could not load HudImage \"" + name + "\"");
		}
	}

	public Image getMedal(int nr) {
		return medals.getSprite(nr, 0);
	}

	public Image getAirHUD() {
		return airHUD;
	}

	public Image getCursor() {
		return cursor;
	}
}
