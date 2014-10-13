package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.map.TiledMapSystem;
import com.starbattle.mapeditor.map.file.MapFileTiledLayer;


public class GameLayer extends MapLayer{

	
	public GameLayer() {
		name="Game Layer";
		resource="gameTiles.png";
		map=new TiledMapSystem();
	}

	public GameLayer(MapFileTiledLayer map) {
	
		name="Game Layer";
		resource="gameTiles.png";
		this.map=new TiledMapSystem(map);
	}
	


}
