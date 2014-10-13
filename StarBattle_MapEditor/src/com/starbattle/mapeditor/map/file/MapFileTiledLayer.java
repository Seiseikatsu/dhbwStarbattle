package com.starbattle.mapeditor.map.file;

import java.io.Serializable;

public class MapFileTiledLayer extends MapFileLayer implements Serializable{

	public int[][][] tiles;
	public boolean isGameLayer=false;
}
