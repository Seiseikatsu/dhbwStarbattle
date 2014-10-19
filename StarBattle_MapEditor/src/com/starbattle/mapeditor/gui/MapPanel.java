package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import com.starbattle.mapeditor.gui.components.MapInfoBar;
import com.starbattle.mapeditor.gui.components.MapComponent;
import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.components.TilePlacementPreview;
import com.starbattle.mapeditor.gui.control.ToolSelection;
import com.starbattle.mapeditor.gui.listener.TilePlacementListener;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;

public class MapPanel extends ContentPanel{

	private RepaintListener repaintListener;
	private MapComponent mapComponent;  
	private MapInfoBar mapInfoBar;
	
	public MapPanel(RepaintListener repaint, ToolSelection toolSelection, TilePlacementListener listener,TilePlacementPreview preview)
	{
		this.repaintListener=repaint;
		mapInfoBar=new MapInfoBar(this);
		mapComponent=new MapComponent(listener,toolSelection,preview, mapInfoBar);
		initLayout();
	}
	
	public void setMap(Map map)
	{
		mapInfoBar.setMapSize(map.getWidth(), map.getHeight());
		mapComponent.setMap(map);
	}
	
	private void initLayout()
	{
	
		view.setLayout(new BorderLayout());
		view.add(new JScrollPane(mapComponent),BorderLayout.CENTER);
		view.add(mapInfoBar.getView(),BorderLayout.SOUTH);
	}

	public void setMapGridVisible(boolean state) {
		mapComponent.setMapGridVisible(state);
	}

	
}
