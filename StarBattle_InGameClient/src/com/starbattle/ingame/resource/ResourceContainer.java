package com.starbattle.ingame.resource;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.resource.player.PlayerGraphicResource;
import com.starbattle.ingame.resource.player.ResourceException;

public class ResourceContainer {

	public final static String PATH = "resource/";
	private HashMap<PlayerGraphics, PlayerGraphicResource> playerGraphics = new HashMap<PlayerGraphics, PlayerGraphicResource>();
	private HashMap<WeaponGraphics, Image> weaponGraphics = new HashMap<WeaponGraphics, Image>();
	private BackgroundGraphics backgroundGraphics = new BackgroundGraphics();
	private HudGraphics hudGraphics = new HudGraphics();

	public ResourceContainer() {

	}

	public void loadResources() throws ResourceException {
		loadPlayerGraphics();
		backgroundGraphics.load();
		hudGraphics.load();
		loadWeaponGraphics();
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
				weaponGraphics.put(resource, image);

			} catch (SlickException e) {
				e.printStackTrace();
				throw new ResourceException("Could not load Weapon Image: " + file);
			}
		}
	}

	public BackgroundGraphics getBackgroundGraphics() {
		return backgroundGraphics;
	}
	
	public Image getWeaponGraphics(WeaponGraphics graphics)
	{
		return weaponGraphics.get(graphics);
	}

	public PlayerGraphicResource getPlayerGraphics(PlayerGraphics graphics) {
		return playerGraphics.get(graphics);
	}

	public HudGraphics getHudGraphics() {
		return hudGraphics;
	}

}
