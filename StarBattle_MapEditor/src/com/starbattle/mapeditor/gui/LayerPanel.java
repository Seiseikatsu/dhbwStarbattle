package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.mapeditor.gui.components.LayerComponent;
import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.components.TilesetComponent;
import com.starbattle.mapeditor.gui.listener.LayerListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;
import com.starbattle.mapeditor.window.VerticalLayout;

public class LayerPanel extends ContentPanel {

	private JPanel layerPanel = new JPanel();
	private RepaintListener repaintListener;
	private Map map;
	private int width = 250;
	private TilesetComponent tileSet = new TilesetComponent();
	private JScrollPane layerView;
	private JPanel tilesetView=new JPanel();

	public LayerPanel(Map map, RepaintListener repaint) {
		this.repaintListener = repaint;
		this.map = map;
		initLayout();
		updateLayers();
	}

	private void initLayout() {

		view.setLayout(new BorderLayout());
		layerPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		layerPanel.setLayout(new VerticalLayout());

		// init layer overview
		layerView = new JScrollPane(layerPanel);
		layerView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		layerView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		layerView.setPreferredSize(new Dimension(width, 0));

		//init tileset view
		tilesetView.setLayout(new BorderLayout());
		tilesetView.add(new JScrollPane(tileSet.getView()),BorderLayout.CENTER);
		JButton closeLayerView=new JButton("Back");
		tilesetView.add(closeLayerView,BorderLayout.NORTH);
		tilesetView.setPreferredSize(new Dimension(400,0));	
		openLayerOverview();
		
		closeLayerView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openLayerOverview();
			}
		});
	}

	private void openLayerOverview() {
		view.remove(tilesetView);
		view.add(layerView, BorderLayout.CENTER);
		view.revalidate();
		view.repaint();
	}

	private void openTilesetView() {
		view.remove(layerView);
		view.add(tilesetView,BorderLayout.CENTER);
		view.revalidate();
		view.repaint();
	}

	public void updateLayers() {
		layerPanel.removeAll();
		LayerListenerImpl listener = new LayerListenerImpl();
		for (MapLayer layer : map.getLayers()) {
			LayerComponent lc = new LayerComponent(layer, listener);
			lc.getView().setPreferredSize(new Dimension(width - 25, 40));
			layerPanel.add(lc.getView());
		}
		view.revalidate();
		view.repaint();
	}

	private class LayerListenerImpl implements LayerListener {

		@Override
		public void moveLayer(MapLayer layer, boolean up) {
			map.moveLayer(layer, up);
			updateLayers();
		}

		@Override
		public void selectLayer(MapLayer layer) {
			layer.setVisible(true);
			tileSet.open(layer);
			openTilesetView();
		}

		@Override
		public void editLayer(MapLayer layer) {

		}

	}
}
