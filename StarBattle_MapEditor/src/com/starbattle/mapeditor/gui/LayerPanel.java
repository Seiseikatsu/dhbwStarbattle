package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.starbattle.mapeditor.gui.components.LayerComponent;
import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.listener.LayerListener;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;
import com.starbattle.mapeditor.window.VerticalLayout;

public class LayerPanel extends ContentPanel{

	private JPanel layerPanel=new JPanel();
	private RepaintListener repaintListener;
	private Map map;
	private int width=250;
	
	public LayerPanel(Map map, RepaintListener repaint)
	{
		this.repaintListener=repaint;
		this.map=map;
		initLayout();
		updateLayers();
	}
	
	private void initLayout()
	{
		
		view.setLayout(new BorderLayout());
		view.setPreferredSize(new Dimension(width,0));
		
		JScrollPane scroll=new JScrollPane(layerPanel);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		view.add(scroll,BorderLayout.CENTER);
		layerPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		layerPanel.setLayout(new VerticalLayout());
		
	}
	
	public void updateLayers()
	{
		layerPanel.removeAll();
		LayerListenerImpl listener=new LayerListenerImpl();
		for(MapLayer layer: map.getLayers())
		{
			LayerComponent lc= new LayerComponent(layer,listener);
			lc.getView().setPreferredSize(new Dimension(width-25,40));
			layerPanel.add(lc.getView());
		}
		view.revalidate();
		view.repaint();
	}
	
	private class LayerListenerImpl implements LayerListener{

		@Override
		public void moveLayer(MapLayer layer, boolean up) {
			map.moveLayer(layer, up);
			updateLayers();
		}

		@Override
		public void selectLayer(MapLayer layer) {
			
		}

		@Override
		public void editLayer(MapLayer layer) {
			
		}
		
	}
}
