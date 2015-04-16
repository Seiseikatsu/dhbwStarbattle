package com.starbattle.ingame.resource;

import java.util.HashMap;

import com.starbattle.ingame.resource.player.PlayerGraphicResource;
import com.starbattle.ingame.resource.player.ResourceException;

public class ResourceContainer {

	public final static String PATH = "resource/";
	private HashMap<PlayerGraphics, PlayerGraphicResource> playerGraphics = new HashMap<PlayerGraphics, PlayerGraphicResource>();
	private BackgroundGraphics backgroundGraphics = new BackgroundGraphics();

	public ResourceContainer() {

	}

	public void loadResources() throws ResourceException {
		loadPlayerGraphics();
		backgroundGraphics.load();
	}

	private void loadPlayerGraphics() throws ResourceException {
		for (PlayerGraphics resource : PlayerGraphics.values()) {
			playerGraphics.put(resource, new PlayerGraphicResource(resource.getFile()));
		}
	}

	public BackgroundGraphics getBackgroundGraphics() {
		return backgroundGraphics;
	}

	public HashMap<PlayerGraphics, PlayerGraphicResource> getPlayerGraphics() {
		return playerGraphics;
	}

}
