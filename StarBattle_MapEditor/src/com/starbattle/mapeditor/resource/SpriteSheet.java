package com.starbattle.mapeditor.resource;

import java.awt.Image;

public class SpriteSheet {

	public final static int TILE_SIZE=32;
	private int tileSize=64;
	private Image[][] tiles;
	private int width,height;
	
	public SpriteSheet(Image source)
	{
		int w=source.getWidth(null)/tileSize;
		int h=source.getHeight(null)/tileSize;
		tiles=new Image[w][h];
		for(int x=0; x<w; x++)
		{
			for(int y=0; y<h; y++)
			{
				int xp=x*tileSize;
				int yp=y*tileSize;
				int s=tileSize;
				Image tile=ResourceLoader.cutImage(source, xp,yp,s,s);
				tiles[x][y]=tile.getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_FAST);
			}
		}
		width=w;
		height=h;
	}
	
	public Image getTileGraphic(int x, int y)
	{
		return tiles[x][y];		
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
}
