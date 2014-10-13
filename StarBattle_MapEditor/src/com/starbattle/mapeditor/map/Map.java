package com.starbattle.mapeditor.map;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.GameLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;

public class Map implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<MapLayer> layers = new ArrayList<MapLayer>();
	private Dimension size;
	
	public Map(Dimension size) {
		
		setSize(size);	
		addLayer(new GameLayer()); // add game layer per default
		
	}
	

	public void addLayer(MapLayer layer) {
		layer.init(size.width, size.height);
		layers.add(layer);
	}
	
	public void removeLayer(MapLayer layer)
	{
		layers.remove(layer);
	}

	private void setSize(Dimension size) {
		this.size=size;
	}

	public void resizeMap(int xplus, int yplus) {
		for (MapLayer layer : layers) {
			layer.resize(xplus, yplus);
		}
	}

	public void moveLayer(MapLayer layer, boolean up) {
		int id = layers.indexOf(layer);
		int newid = id;
		if (up) {// move up
			newid++;
		} else {// move down
			newid--;
		}
		if (newid < 0) {
			newid = layers.size() - 1;
		} else if (newid >= layers.size()) {
			newid = 0;
		}

		MapLayer old = layers.get(newid);

		// create new list
		ArrayList<MapLayer> list = new ArrayList<MapLayer>();
		for (int i = 0; i < layers.size(); i++) {
			if (i == id) {
				// add old
				list.add(old);
			} else if (i == newid) {
				// add new
				list.add(layer);
			} else {
				// add others
				list.add(layers.get(i));
			}
		}
		// overwrite old list
		layers = list;
	}

	public ArrayList<MapLayer> getLayers() {
		return layers;
	}
	
	public int getWidth()
	{
		return size.width;
	}
	
	public int getHeight()
	{
		return size.height;
	}
	
	public Dimension getSize() {
		return size;
	}
}
