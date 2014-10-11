package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

import com.starbattle.mapeditor.gui.components.MapComponent;
import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.components.TilePlacementPreview;
import com.starbattle.mapeditor.gui.listener.TilePlacementListener;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;

public class MapPanel extends ContentPanel{

	private RepaintListener repaintListener;
	private MapComponent mapComponent;  
	
	public MapPanel(Map map, RepaintListener repaint, TilePlacementListener listener,TilePlacementPreview preview)
	{
		this.repaintListener=repaint;
		mapComponent=new MapComponent(map,listener,preview);
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout());
		view.add(new JScrollPane(mapComponent),BorderLayout.CENTER);
	}
	
}
