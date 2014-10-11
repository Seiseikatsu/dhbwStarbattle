package com.starbattle.mapeditor.layer;

import com.starbattle.mapeditor.map.DecorationMapSystem;


public class DecorationLayer extends MapLayer{

	
	
	public DecorationLayer(String tileSet) {
		name="Decoration Layer";
		resource=tileSet;
		map=new DecorationMapSystem();
	}
	


}
