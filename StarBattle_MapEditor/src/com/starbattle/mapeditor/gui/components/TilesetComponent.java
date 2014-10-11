package com.starbattle.mapeditor.gui.components;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JScrollPane;

import com.starbattle.mapeditor.gui.control.TileSelection;
import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.window.ContentPanel;

public class TilesetComponent extends ContentPanel{


	private TilesetRender render;
	
	public TilesetComponent(TilePlacementPreview preview)
	{
		render=new TilesetRender(preview);
		initLayout();
	}
	
	private void initLayout()
	{
		JScrollPane p=new JScrollPane(render);
		view.setLayout(new BorderLayout());
		view.add(p,BorderLayout.CENTER);
	}
	

	
	public void open(MapLayer layer, TileSelection selection)
	{
		render.open(layer, selection);
	}

	
	
	
	
}
