package com.starbattle.ingame.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.resource.player.ResourceException;

public class BackgroundGraphics {

	private Image spaceBackground;
	public final static String PATH = ResourceContainer.PATH + "textures/";

	public BackgroundGraphics() {

	}

	public void load(ResourceGarbageCollector resourceGarbageCollector) throws ResourceException {

		spaceBackground = loadImage("space_background.jpg");
		resourceGarbageCollector.collect(spaceBackground);
	}

	private Image loadImage(String name) throws ResourceException {
		try {
			System.out.println("Loading BackgroundImage: "+name);
			return new Image(BackgroundGraphics.PATH + name);
		} catch (SlickException e) {
			e.printStackTrace();
			throw new ResourceException("Could not load BackgroundImage \"" + name + "\"");
		}
	}
	
	public Image getSpaceBackground() {
		return spaceBackground;
	}
}
