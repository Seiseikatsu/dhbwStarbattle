package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JSplitPane;

import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.control.TileSelection;
import com.starbattle.mapeditor.gui.control.TilePlacement;
import com.starbattle.mapeditor.gui.listener.TilePlacementListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;

public class MainPanel extends ContentPanel {

	// panels
	private LayerPanel layerPanel;
	private MapPanel mapPanel;
	private ToolbarPanel toolbarPanel;

	// map
	private Map map;

	public MainPanel() {

		// create default map
		map = new Map((new Dimension(20, 10)));
		// create views
		Repainter repaint = new Repainter();
		layerPanel = new LayerPanel(repaint);
		toolbarPanel = new ToolbarPanel(this);
		mapPanel = new MapPanel(new Repainter(), new TilePlacementAction(), layerPanel.getTilePlacementPreview());

		initLayout();
		// set map
		updateMap(map);

	}

	private void initLayout() {
		view.setLayout(new BorderLayout());
		JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,layerPanel.getView(),mapPanel.getView());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(250);
		view.add(splitPane, BorderLayout.CENTER);
		view.add(toolbarPanel.getView(), BorderLayout.NORTH);
	}

	public void updateMap(Map map) {
		this.map = map;
		layerPanel.setMap(map);
		toolbarPanel.setMap(map);
		mapPanel.setMap(map);
		layerPanel.updateLayers();
		view.repaint();
	}

	public void updateLayer() {
		layerPanel.updateLayers();
	}
	
	public Map getMap() {
		return map;
	}

	private class TilePlacementAction implements TilePlacementListener {

		@Override
		public void placeTile(int mx, int my) {
			// TODO Auto-generated method stub
			MapLayer layer = layerPanel.getSelectedLayer();
			TileSelection selection = layerPanel.getTileSelection();
			new TilePlacement(layer, selection, mx, my);
			mapPanel.getView().repaint();
		}
	}

	private class Repainter implements RepaintListener {

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
			updateLayer();
		}

	}

}
