package com.starbattle.ingame.game.map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class GameMap {

	private final static String path="resource/maps/";
	private TiledMap map;
	private int gameLayerID;
	
	public GameMap()
	{
		
	}
	
	public void loadMap(String name)
	{
		try {
			map=new TiledMap(path+name+".tmx");
			gameLayerID=map.getLayerIndex("Game");	
			System.out.println("Loaded Map with "+map.getLayerCount()+" Layers (GameLayerID: "+gameLayerID+")");
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
		
	public void renderBackground(int x, int y)
	{
		
		for(int i=0; i<gameLayerID; i++)
		{
				map.render(x, y, i);						
		}
	}

	public void renderForeground(int x, int y)
	{
		for(int i=gameLayerID+1; i<map.getLayerCount(); i++)
		{
				map.render(x, y, i);						
		}
	}
	
	
	public int getGameTileID(int x, int y)
	{
		return map.getTileId(x, y, gameLayerID);
	}
	
	

}
