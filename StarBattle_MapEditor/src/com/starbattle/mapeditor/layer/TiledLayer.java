package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.map.TiledMapSystem;
import com.starbattle.mapeditor.map.file.MapFileTiledLayer;
import com.starbattle.mapeditor.resource.AutotileMarks;


public class TiledLayer extends MapLayer{

	
	public TiledLayer(String tileSet) {
		name="Tile Layer";
		resource=tileSet;
		map=new TiledMapSystem();
	}

	public TiledLayer(MapFileTiledLayer map, AutotileMarks marks) {
		name="Tile Layer";
		resource=map.resourceName;
		this.map=new TiledMapSystem(map,marks);
	}


}
