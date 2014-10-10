package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.window.ContentPanel;

public class MainPanel extends ContentPanel {

	//panels
	private LayerPanel layerPanel;
	private MapPanel mapPanel;
	private ToolbarPanel toolbarPanel;
	
	//map
	private Map map;
	
	public MainPanel() {
		//init resource graphics
		MapTextureLoader.loadTextures();
		//create default map
		map=new Map((new Dimension(20,10)));
		//create views
		Repainter repaint=new Repainter();
		layerPanel = new LayerPanel(map,repaint);
		mapPanel = new MapPanel(map,repaint);
		toolbarPanel = new ToolbarPanel(map,repaint);
		initLayout();
	}

	private void initLayout() {
		view.setLayout(new BorderLayout());
		view.add(layerPanel.getView(),BorderLayout.WEST);
		view.add(mapPanel.getView(),BorderLayout.CENTER);
		view.add(toolbarPanel.getView(),BorderLayout.NORTH);
		
	}
	
	private class Repainter implements RepaintListener{

		@Override
		public void repaintAll() {
			view.repaint();
		}

		@Override
		public void repaintMap() {
			mapPanel.getView().repaint();
		}

		@Override
		public void refreshLayer() {
			layerPanel.updateLayers();
		}
		
	}

}
