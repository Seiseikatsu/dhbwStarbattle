package com.starbattle.mapeditor.gui.control;

import java.awt.Rectangle;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;
import com.starbattle.mapeditor.resource.SpriteSheet;

public class TilePlacement {

	private int xpos, ypos;
	private Rectangle tileSelection;

	public TilePlacement(MapLayer layer, TileSelection selection, int xpos, int ypos) {

		//set tile position
		if (layer instanceof DecorationLayer) {
			this.xpos = xpos;
			this.ypos = ypos;
		} else {
			
			this.xpos = xpos / SpriteSheet.TILE_SIZE;
			this.ypos = ypos / SpriteSheet.TILE_SIZE;
			//special case "-0" to -1 
			if(xpos<0&&xpos>-SpriteSheet.TILE_SIZE)
			{
				this.xpos=-1;
			}
			if(ypos<0&&ypos>-SpriteSheet.TILE_SIZE)
			{
				this.ypos=-1;
			}		
		}
		//set tileset selection area
		int x = selection.getX();
		int y = selection.getY();
		int w = selection.getW();
		int h = selection.getH();
		tileSelection = new Rectangle(x, y, w, h);

		layer.place(this); // place tile
	}

	public Rectangle getTileSelection() {
		return tileSelection;
	}

	public int getXpos() {
		return xpos;
	}

	public int getYpos() {
		return ypos;
	}
}
