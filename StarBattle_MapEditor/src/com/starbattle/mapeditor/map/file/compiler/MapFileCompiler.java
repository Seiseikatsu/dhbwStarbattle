package com.starbattle.mapeditor.map.file.compiler;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.starbattle.mapeditor.layer.DecorationLayer;
import com.starbattle.mapeditor.layer.GameLayer;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.layer.TiledLayer;
import com.starbattle.mapeditor.map.DecorationMapSystem;
import com.starbattle.mapeditor.map.DecorationTile;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.map.Tile;
import com.starbattle.mapeditor.map.TiledMapSystem;
import com.starbattle.mapeditor.map.file.MapFile;
import com.starbattle.mapeditor.map.file.MapFileDecoration;
import com.starbattle.mapeditor.map.file.MapFileDecorationLayer;
import com.starbattle.mapeditor.map.file.MapFileLayer;
import com.starbattle.mapeditor.map.file.MapFileTiledLayer;

public class MapFileCompiler {

	public static MapFile compileToFile(Map map) throws Exception {
		MapFile file = new MapFile();
		file.width = map.getWidth();
		file.height = map.getHeight();
		file.layer = new ArrayList<MapFileLayer>();
		for (MapLayer layer : map.getLayers()) {
			file.layer.add(compileLayer(layer));
		}
		return file;
	}

	public static Map decompileToMap(MapFile file) throws Exception {
		
		ArrayList<MapLayer> maplayer=new ArrayList<MapLayer>();
		for(MapFileLayer layer:file.layer)
		{
			maplayer.add(decompileLayer(layer));
		}
		Map map=new Map(maplayer);
		map.setSize(new Dimension(file.width,file.height));		
		return map;
	}

	private static MapFileLayer compileLayer(MapLayer layer) throws Exception {
		if (layer instanceof GameLayer) {
			MapFileTiledLayer layerFile = new MapFileTiledLayer();
			layerFile.resourceName = layer.getResource();
			layerFile.isGameLayer = true;
			layerFile.tiles = compileTiledLayer((TiledMapSystem) layer.getMap());
			return layerFile;
		} else if (layer instanceof TiledLayer) {
			MapFileTiledLayer layerFile = new MapFileTiledLayer();
			layerFile.resourceName = layer.getResource();
			layerFile.tiles = compileTiledLayer((TiledMapSystem) layer.getMap());
			return layerFile;
		} else if (layer instanceof DecorationLayer) {
			MapFileDecorationLayer layerFile = new MapFileDecorationLayer();
			layerFile.resourceName = layer.getResource();
			layerFile.tiles = compileDecorationLayer((DecorationMapSystem) layer.getMap());
			return layerFile;
		}
		throw new Exception("Map compile error: uknown layer type");
	}

	private static int[][][] compileTiledLayer(TiledMapSystem layer) {
		Tile[][] map = layer.getMap();
		int[][][] tiles = new int[map.length][map[0].length][2];
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				tiles[x][y][0] = map[x][y].getX();
				tiles[x][y][1] = map[x][y].getY();
			}
		}
		return tiles;
	}

	private static ArrayList<MapFileDecoration> compileDecorationLayer(DecorationMapSystem layer) {
		ArrayList<MapFileDecoration> tiles = new ArrayList<MapFileDecoration>();
		for(DecorationTile deco: layer.getMap())
		{
			MapFileDecoration tile=new MapFileDecoration();
			tile.xpos=deco.getXpos();
			tile.ypos=deco.getYpos();
			Rectangle selection=deco.getTileSelection();
			tile.x=selection.x;
			tile.y=selection.y;
			tile.w=selection.width;
			tile.h=selection.height;
			tiles.add(tile);
		}
		return tiles;
	}
	
	private static MapLayer decompileLayer(MapFileLayer layer) throws Exception {
		
		if(layer instanceof MapFileTiledLayer)
		{
			MapFileTiledLayer map=(MapFileTiledLayer)layer;
			if(map.isGameLayer)
			{
				return new GameLayer(map);
			}
			else
			{
				return new TiledLayer(map);
			}
		}		
		else if(layer instanceof MapFileDecorationLayer){
			return new DecorationLayer((MapFileDecorationLayer)layer);
		}
		throw new Exception("Map decompile error: unknown layer type");
	}
}
