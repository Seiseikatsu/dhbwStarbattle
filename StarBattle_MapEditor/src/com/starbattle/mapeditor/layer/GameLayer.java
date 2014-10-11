package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.map.TiledMapSystem;


public class GameLayer extends MapLayer{

	
	public GameLayer() {
		name="Game Layer";
		resource="gameTiles.png";
		map=new TiledMapSystem();
	}
	


}
