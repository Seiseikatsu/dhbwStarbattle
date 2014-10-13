package com.starbattle.mapeditor.layer;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.starbattle.mapeditor.map.DecorationMapSystem;
import com.starbattle.mapeditor.map.DecorationTile;
import com.starbattle.mapeditor.map.file.MapFileDecoration;
import com.starbattle.mapeditor.map.file.MapFileDecorationLayer;


public class DecorationLayer extends MapLayer{

	
	
	public DecorationLayer(String tileSet) {
		name="Decoration Layer";
		resource=tileSet;
		map=new DecorationMapSystem();
	}

	public DecorationLayer(MapFileDecorationLayer layer) {
		
		name="Decoration Layer";
		resource=layer.resourceName;
		
		ArrayList<DecorationTile> tiles=new ArrayList<DecorationTile>();
		for(MapFileDecoration tile: layer.tiles)
		{
			Rectangle tileSelection=new Rectangle(tile.x,tile.y,tile.w,tile.h);
			tiles.add(new DecorationTile(tile.xpos, tile.ypos, tileSelection));
		}
		map=new DecorationMapSystem(tiles);
	}
	


}
