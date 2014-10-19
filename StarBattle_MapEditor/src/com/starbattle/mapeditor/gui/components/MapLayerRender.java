package com.starbattle.mapeditor.gui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.GameLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;
import com.starbattle.mapeditor.map.DecorationMapSystem;
import com.starbattle.mapeditor.map.DecorationTile;
import com.starbattle.mapeditor.map.Tile;
import com.starbattle.mapeditor.map.TiledMapSystem;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class MapLayerRender {

	private HashMap<String,SpriteSheet> textures;
	private Dimension mapSize;
	
	public MapLayerRender()
	{
		textures=MapTextureLoader.getSpriteSheets();
	}
	
	public void setMapSize(Dimension mapSize) {
		this.mapSize = mapSize;
	}
	
	private int tiles;
	
	public int renderLayer(Graphics g, MapLayer layer, int x, int y)
	{
		tiles=0;
		if(layer instanceof TiledLayer)
		{
			renderTiledLayer(g, layer, x, y);
		}
		else if(layer instanceof DecorationLayer)
		{
			renderDecoLayer(g, (DecorationLayer)layer, x, y);
		}
		else if(layer instanceof GameLayer)
		{
			renderTiledLayer(g, layer, x, y);
		}
		return tiles;
	}
	
	private void renderTiledLayer(Graphics g, MapLayer layer, int sx, int sy)
	{
		int w=mapSize.width;
		int h=mapSize.height;
		TiledMapSystem map=(TiledMapSystem) layer.getMap();
		SpriteSheet sprites=textures.get(layer.getResource());
		for(int x=0; x<w; x++)
		{
			for(int y=0; y<h; y++)
			{
				int xp=sx+x*SpriteSheet.TILE_SIZE;
				int yp=sy+y*SpriteSheet.TILE_SIZE;
				Tile tile=map.getTile(x, y);
				if(!tile.isEmpty())
				{
					tiles++;
					int xid=tile.getX();
					int yid=tile.getY();
					Image img=sprites.getTileGraphic(xid, yid);
					g.drawImage(img,xp,yp,null);
				}
			}
		}
	}
	
	private void renderDecoLayer(Graphics g, DecorationLayer layer, int sx, int sy)
	{
		DecorationMapSystem map=(DecorationMapSystem)layer.getMap();
		SpriteSheet sprites=textures.get(layer.getResource());
		for(int i=0; i<map.getMap().size(); i++)
		{
			DecorationTile tile=map.getMap().get(i);
			Rectangle rect=tile.getTileSelection();
			tiles++;
			for(int x=0; x<rect.width; x++)
			{
				for(int y=0; y<rect.height; y++)
				{
					int xp=sx+tile.getXpos()+(x*SpriteSheet.TILE_SIZE);
					int yp=sy+tile.getYpos()+(y*SpriteSheet.TILE_SIZE);
					
					int tix=rect.x+x;
					int tiy=rect.y+y;
					Image img=sprites.getTileGraphic(tix, tiy);
					g.drawImage(img,xp,yp,null);
				}
			}
		}
	}
}
