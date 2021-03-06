package com.starbattle.ingame.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.resource.player.ResourceException;

public class ParticleGraphics {

	private Image plasmaBall;
	public final static String PATH = ResourceContainer.PATH + "effects/";

	public ParticleGraphics() {

	}

	public void load(ResourceGarbageCollector resourceGarbageCollector) throws ResourceException {

		plasmaBall = loadImage("plasmaBall.png");
		resourceGarbageCollector.collect(plasmaBall);
	}

	private Image loadImage(String name) throws ResourceException {
		try {
			System.out.println("Loading ParticleImage: "+name);
			return new Image(ParticleGraphics.PATH + name);
		} catch (SlickException e) {
			e.printStackTrace();
			throw new ResourceException("Could not load ParticleImage \"" + name + "\"");
		}
	}
	
	public Image getPlasmaBall() {
		return plasmaBall;
	}
}
