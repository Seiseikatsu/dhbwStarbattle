package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.map.TiledMapSystem;


public class TiledLayer extends MapLayer{

	
	public TiledLayer(String tileSet) {
		name="Tile Layer";
		resource=tileSet;
		map=new TiledMapSystem();
	}


}
