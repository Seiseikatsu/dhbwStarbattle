package com.starbattle.mapeditor.gui.dialogs;


import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.starbattle.mapeditor.resource.MapTextureLoader;
import com.starbattle.mapeditor.window.ContentPanel;
import com.starbattle.mapeditor.window.VerticalLayout;

public class NewLayerDialog extends ContentPanel {

	private JComboBox<String> layerType,mapresName;
	private String[] types={"Tiled Layer","Decoration Layer"};

	public NewLayerDialog()
	{
	
		layerType=new JComboBox<String>(types);
		mapresName=new JComboBox<String>(MapTextureLoader.getMapresNames());
		view.setLayout(new VerticalLayout());
		
		JLabel title=new JLabel("New Layer");
		title.setFont(title.getFont().deriveFont(20f));
		
		view.add(title);
		view.add(Box.createVerticalStrut(10));
		view.add(new JLabel("Layer Type"));
		view.add(layerType);
		view.add(Box.createVerticalStrut(5));
		view.add(new JLabel("Resource Graphic"));
		view.add(mapresName);
	}
	
	public String getMapresName()
	{
		return (String) mapresName.getSelectedItem();
	}
	
	public int getLayerType()
	{
		return layerType.getSelectedIndex();
	}
}
