package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.map.TiledMapSystem;
import com.starbattle.mapeditor.map.file.MapFileTiledLayer;


public class TiledLayer extends MapLayer{

	
	public TiledLayer(String tileSet) {
		name="Tile Layer";
		resource=tileSet;
		map=new TiledMapSystem();
	}

	public TiledLayer(MapFileTiledLayer map) {
		name="Tile Layer";
		resource=map.resourceName;
		this.map=new TiledMapSystem(map);
	}


}
