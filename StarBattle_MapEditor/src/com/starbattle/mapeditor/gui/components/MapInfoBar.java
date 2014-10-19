package com.starbattle.mapeditor.gui.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.starbattle.mapeditor.gui.MapPanel;
import com.starbattle.mapeditor.resource.ResourceLoader;

public class MapInfoBar {

	private JToolBar view=new JToolBar();
	private JLabel numberOfTiles=new JLabel();
	private JLabel mapSize=new JLabel();
	private MapPanel mapPanel;
	
	public MapInfoBar(MapPanel mapPanel)
	{
		this.mapPanel=mapPanel;
		
		view.setFloatable(false);
		initLayout();
	}
	
	private void initLayout()
	{

		addInfo("Map Size", "rectangle.png", mapSize);
		addInfo("Tiles", "grid.png", numberOfTiles);
		
		addCheckbox("Show Map Grid", true, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean state=((JCheckBox)e.getSource()).isSelected();
				mapPanel.setMapGridVisible(state);
			}
		});
	}
	
	private void addInfo(String name,String icon,  JLabel data)
	{
		view.add(new JLabel(name, ResourceLoader.loadIcon(icon), 0));
		view.add(Box.createHorizontalStrut(7));
		view.add(data);
		view.add(Box.createHorizontalStrut(20));
		data.setForeground(new Color(100,100,250));
	}
	
	private void addCheckbox(String name, boolean state, ActionListener action)
	{
		JCheckBox box=new JCheckBox(name,state);
		box.addActionListener(action);
		view.add(box);
		view.add(Box.createHorizontalStrut(20));
	}
	
	public void setNumberOfTiles(int anz) {
		this.numberOfTiles.setText(anz+"");
	}
	
	public void setMapSize(int w, int h)
	{
		this.mapSize.setText(w+" x "+h+" ("+(w*h)+")");
	}
	
	public JToolBar getView() {
		return view;
	}
	
}
