package com.starbattle.gameserver.map;

import java.util.ArrayList;

import org.newdawn.slick.tiled.TiledMap;

import com.starbattle.gameserver.game.Team;

public class GameTileLoader {

	//IDs in Game TileSet (resource/tilesets/gameset.png) for Blocks
	public final static int TILE_COLLISION=1;
	public final static int TILE_DEATHBLOCK=2;	
	public final static int TILE_SPAWNPOINT_BLUE=3;
	public final static int TILE_SPAWNPOINT_RED=4;
	public final static int TILE_FLAG_BLUE=5;
	public final static int TILE_FLAG_RED=6;
	
	
	public static SpawnPointList findSpawnpoints(TiledMap map, int gameLayerID) 
	{
		SpawnPointList list=new SpawnPointList();
		for(int x=0; x<map.getWidth(); x++)
		{
			for(int y=0; y<map.getHeight(); y++)
			{
				int tileID=map.getTileId(x, y, gameLayerID);
				if(tileID==TILE_SPAWNPOINT_BLUE)
				{
					list.add(new SpawnPoint(x, y, Team.BLUE_TEAM));
				}
				else  if(tileID==TILE_SPAWNPOINT_RED)
				{
					list.add(new SpawnPoint(x, y, Team.RED_TEAM));
				}
			}
		}
		return list;
	}


	public static void removeTiles(TiledMap map,int gameLayerID, int tileID) {
		for(int x=0; x<map.getWidth(); x++)
		{
			for(int y=0; y<map.getHeight(); y++)
			{
				int id=map.getTileId(x, y, gameLayerID);
				if(id==tileID)
				{
					map.setTileId(x, y, gameLayerID, 0);
				}
			}
		}
	}
	
}
