package com.starbattle.mapeditor.gui.components;

import java.util.HashMap;

import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class MapRender {

	private HashMap<String,SpriteSheet> textures;
	private int tileSize=32;
	
	public MapRender()
	{
		textures=MapTextureLoader.getSpriteSheets();
	}
	
	public void renderLayer(MapLayer layer)
	{
		
	}
	
}
