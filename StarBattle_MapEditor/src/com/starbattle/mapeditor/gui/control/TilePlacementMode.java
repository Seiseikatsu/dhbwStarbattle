package com.starbattle.mapeditor.gui.control;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.resource.SpriteSheet;

public abstract class TilePlacementMode {


	protected int xpos,ypos;

	protected void setPosition(MapLayer layer, int xpos, int ypos)
	{
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
	}
	
	public int getXpos() {
		return xpos;
	}
	
	public int getYpos() {
		return ypos;
	}
}
