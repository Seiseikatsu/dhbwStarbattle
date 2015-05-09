package com.starbattle.ingame.resource;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import com.starbattle.ingame.resource.player.PlayerGraphicResource;
import com.starbattle.ingame.resource.player.ResourceException;

public class ResourceContainer {

	public final static String PATH = "resource/";
	private HashMap<PlayerGraphics, PlayerGraphicResource> playerGraphics = new HashMap<PlayerGraphics, PlayerGraphicResource>();
	private HashMap<WeaponGraphics, SpriteSheet> weaponGraphics = new HashMap<WeaponGraphics, SpriteSheet>();
	private BackgroundGraphics backgroundGraphics = new BackgroundGraphics();
	private HudGraphics hudGraphics = new HudGraphics();
	private ParticleGraphics particleGraphics=new ParticleGraphics();
	private FontContainer fonts=new FontContainer();
	private SoundContainer sounds=new SoundContainer();

	public ResourceContainer() {

	}

	public void loadResources() throws ResourceException {
		fonts.loadFonts();
		loadPlayerGraphics();
		backgroundGraphics.load();
		hudGraphics.load();
		particleGraphics.load();
		loadWeaponGraphics();
		sounds.loadSounds();
	}

	private void loadPlayerGraphics() throws ResourceException {
		for (PlayerGraphics resource : PlayerGraphics.values()) {
			playerGraphics.put(resource, new PlayerGraphicResource(resource.getFile()));
		}
	}

	private void loadWeaponGraphics() throws ResourceException {
		for (WeaponGraphics resource : WeaponGraphics.values()) {
			String file = resource.getFile();
			System.out.println("Loading WeaponImage: " + file);
			Image image;
			try {
				image = new Image(PATH + "weapons/" + file);
				SpriteSheet sprites=new SpriteSheet(image, image.getWidth(), resource.getSpriteHeight());
				weaponGraphics.put(resource, sprites);

			} catch (SlickException e) {
				e.printStackTrace();
				throw new ResourceException("Could not load Weapon Image: " + file);
			}
		}
	}

	public BackgroundGraphics getBackgroundGraphics() {
		return backgroundGraphics;
	}
	
	public SpriteSheet getWeaponGraphics(WeaponGraphics graphics)
	{
		return weaponGraphics.get(graphics);
	}

	public PlayerGraphicResource getPlayerGraphics(PlayerGraphics graphics) {
		return playerGraphics.get(graphics);
	}

	public HudGraphics getHudGraphics() {
		return hudGraphics;
	}
	
	public ParticleGraphics getParticleGraphics() {
		return particleGraphics;
	}

	public FontContainer getFonts() {
		return fonts;
	}
	
	public SoundContainer getSounds() {
		return sounds;
	}
}
