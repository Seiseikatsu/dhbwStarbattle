package com.starbattle.mapeditor.gui.components;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import com.starbattle.mapeditor.layer.MapLayer;
import com.starbattle.mapeditor.window.ContentPanel;

public class TilesetComponent extends ContentPanel{


	private TilesetRender render=new TilesetRender();
	
	public TilesetComponent()
	{
		initLayout();
	}
	
	private void initLayout()
	{
		JScrollPane p=new JScrollPane(render);
		view.setLayout(new BorderLayout());
		view.add(p,BorderLayout.CENTER);
	}
	
	public void open(MapLayer layer)
	{
		render.open(layer);
	}
	
	
	
	
	
}
