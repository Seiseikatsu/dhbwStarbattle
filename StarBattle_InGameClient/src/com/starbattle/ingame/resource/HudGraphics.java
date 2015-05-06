package com.starbattle.ingame.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.resource.player.ResourceException;

public class HudGraphics {

	private Image airHUD,cursor;
	public final static String PATH = ResourceContainer.PATH + "hud/";

	public HudGraphics() {

	}

	public void load() throws ResourceException {

		airHUD = loadImage("airBar.png");
		cursor=loadImage("cursor.png");

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

	public Image getAirHUD() {
		return airHUD;
	}
	
	public Image getCursor() {
		return cursor;
	}
}
