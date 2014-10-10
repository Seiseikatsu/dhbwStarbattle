package com.starbattle.mapeditor.gui;

import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.window.ContentPanel;

public class MapPanel extends ContentPanel{

	private RepaintListener repaintListener;
	
	public MapPanel(Map map, RepaintListener repaint)
	{
		this.repaintListener=repaint;
		initLayout();
	}
	
	private void initLayout()
	{
		
	}
	
}
