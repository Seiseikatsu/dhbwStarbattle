package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JSplitPane;

import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.control.TilePlacementFill;
import com.starbattle.mapeditor.gui.control.TilePlacementMode;
import com.starbattle.mapeditor.gui.control.TilePlacementMoveBehind;
import com.starbattle.mapeditor.gui.control.TilePlacementMoveSelection;
import com.starbattle.mapeditor.gui.control.TilePlacementMoveSelectionRelease;
import com.starbattle.mapeditor.gui.control.TilePlacementRectangle;
import com.starbattle.mapeditor.gui.control.TilePlacementRemove;
import com.starbattle.mapeditor.gui.control.TilePlacementStandard;
import com.starbattle.mapeditor.gui.control.TileSelection;
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
		toolbarPanel = new ToolbarPanel(this);
		layerPanel = new LayerPanel(repaint, toolbarPanel);
		mapPanel = new MapPanel(new Repainter(), toolbarPanel.getToolSelection(), new TilePlacementAction(),
				layerPanel.getTilePlacementPreview());

		initLayout();
		// set map
		updateMap(map);

	}

	private void initLayout() {
		view.setLayout(new BorderLayout());
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, layerPanel.getView(), mapPanel.getView());
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

	public MapPanel getMapPanel() {
		return mapPanel;
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
			MapLayer layer = layerPanel.getSelectedLayer();
			TileSelection selection = layerPanel.getTileSelection();
			doTilePlacement(new TilePlacementStandard(layer, selection, mx, my));
		}

		@Override
		public void removeTile(int mx, int my) {
			MapLayer layer = layerPanel.getSelectedLayer();
			doTilePlacement(new TilePlacementRemove(layer, mx, my));
		}

		@Override
		public void fillRectangle(int x, int y, int w, int h) {
			MapLayer layer = layerPanel.getSelectedLayer();
			TileSelection selection = layerPanel.getTileSelection();
			doTilePlacement(new TilePlacementRectangle(layer, selection, new Rectangle(x, y, w, h)));
		}

		@Override
		public void fillTile(int tx, int ty) {
			MapLayer layer = layerPanel.getSelectedLayer();
			TileSelection selection = layerPanel.getTileSelection();
			doTilePlacement(new TilePlacementFill(layer, selection, tx, ty));
		}

		@Override
		public void moveSelectedTile(int mx, int my, int movex, int movey) {
			MapLayer layer = layerPanel.getSelectedLayer();
			doTilePlacement(new TilePlacementMoveSelection(layer, mx, my, movex, movey));
		}

		@Override
		public void moveBehind(int mx, int my) {
			MapLayer layer = layerPanel.getSelectedLayer();
			doTilePlacement(new TilePlacementMoveBehind(layer, mx, my));
		}

		@Override
		public void moveSelectedTileReleased() {
			doTilePlacement(new TilePlacementMoveSelectionRelease());
		}

	}

	private void doTilePlacement(TilePlacementMode tilePlacement) {
		MapLayer layer = layerPanel.getSelectedLayer();
		layer.place(tilePlacement);
		mapPanel.getView().repaint();
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
