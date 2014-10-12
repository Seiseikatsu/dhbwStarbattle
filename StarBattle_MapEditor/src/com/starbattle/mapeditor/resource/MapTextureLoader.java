package com.starbattle.mapeditor.resource;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MapTextureLoader {

	private static HashMap<String, SpriteSheet> textures = new HashMap<String, SpriteSheet>();
	private static ArrayList<String> availableTextures=new ArrayList<String>();
	
	public static void loadTextures() {
		File path = new File("mapres");
		for (File file : path.listFiles()) {
			if (isMapres(file)) {
				String name = file.getName();
				Image texture = ResourceLoader.loadImage(file);
				textures.put(name, new SpriteSheet(texture));
				if(!name.equals("gameTiles.png"))
				{
					availableTextures.add(name);
				}
			}
		}
	}

	public static HashMap<String, SpriteSheet> getSpriteSheets() {
		return textures;
	}

	public static String[] getMapresNames() {
		return availableTextures.toArray(new String[0]);
	}

	private static boolean isMapres(File file) {
		if (file.getName().toLowerCase().endsWith(".png")) {
			return true;
		}
		return false;
	}

}
