package com.starbattle.mapeditor.map.file;

import java.io.Serializable;
import java.util.ArrayList;

public class MapFile implements Serializable {

	public ArrayList<MapFileLayer> layer;
	public int width,height;
	
}
