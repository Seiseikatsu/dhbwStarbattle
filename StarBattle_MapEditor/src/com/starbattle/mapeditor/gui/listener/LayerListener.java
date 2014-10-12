package com.starbattle.mapeditor.gui.listener;

import com.starbattle.mapeditor.layer.MapLayer;

public interface LayerListener {

	/**
	 * 
	 * @param layer target map layer
	 * @param up  if true move up, if false move down
	 */
	public void moveLayer(MapLayer layer, boolean up);
	
	public void repaintMap();
	
	public void selectLayer(MapLayer layer);
	
	public void editLayer(MapLayer layer);
	
}
