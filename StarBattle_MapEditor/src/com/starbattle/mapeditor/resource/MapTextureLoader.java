package com.starbattle.mapeditor.resource;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import com.starbattle.mapeditor.settings.MapresAutotileProperties;
import com.starbattle.mapeditor.settings.PropertiesReader;

public class MapTextureLoader {

	private static HashMap<String, SpriteSheet> textures = new HashMap<String, SpriteSheet>();
	private static ArrayList<String> availableTextures=new ArrayList<String>();
	
	public static void loadTextures() throws JAXBException {
		File path = new File("mapres");
		
		HashMap<String, AutotileMarks> autotiles = PropertiesReader.read();
		for (File file : path.listFiles()) {
			if (isMapres(file)) {
				String name = file.getName();
				Image texture = ResourceLoader.loadImage(file);
				
				AutotileMarks auto=null;
				if(autotiles.containsKey(name))
				{
				auto=autotiles.get(name);
				}
				textures.put(name, new SpriteSheet(texture,auto));
				if(!name.equals("gameTiles.png"))
				{
					availableTextures.add(name);
				}
			}
		}
	}

	
	private static AutotileMarks createAutotileMarks(MapresAutotileProperties properties)
	{
		AutotileMarks marks=new AutotileMarks();
		
		return marks;
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
