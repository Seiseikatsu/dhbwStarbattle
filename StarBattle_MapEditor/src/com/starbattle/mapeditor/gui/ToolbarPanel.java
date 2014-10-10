package com.starbattle.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.starbattle.mapeditor.gui.components.RepaintListener;
import com.starbattle.mapeditor.gui.dialogs.DialogViewer;
import com.starbattle.mapeditor.map.Map;
import com.starbattle.mapeditor.resource.ResourceLoader;
import com.starbattle.mapeditor.window.ContentPanel;

public class ToolbarPanel extends ContentPanel{

	private JToolBar toolbar=new JToolBar();
	private ButtonListener buttonListener=new ButtonListener();
	private RepaintListener repaintListener;
	private Map map;
	
	public ToolbarPanel(Map map, RepaintListener repaint)
	{
		this.map=map;
		this.repaintListener=repaint;
		initLayout();
	}
	
	private void initLayout()
	{
		toolbar.setFloatable(false);
		view.setBorder(BorderFactory.createEtchedBorder());
		view.setLayout(new BorderLayout());
		view.add(toolbar,BorderLayout.CENTER);
		addButton("New Map", "map_add.png");
		addButton("Load Map", "folder.png");
		addButton("Save Map", "save_as.png");
		toolbar.addSeparator();
		addButton("New Layer", "layer_add.png");
	}
	
	private void pressedButton(int id)
	{
		switch(id)
		{
		case 0:  break;
		case 1:  break;
		case 2:  break;
		case 3: if(DialogViewer.showNewLayerDialog(map)) repaintListener.refreshLayer(); break;	 
		}
		
	}

	private int buttonID=0;
	private void addButton(String name, String iconname)
	{
		JButton button=new JButton();
		button.setToolTipText(name);
		button.setName(buttonID+"");
		buttonID+=1;
		button.setPreferredSize(new Dimension(32,32));
		button.setBorderPainted(false);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setIcon(ResourceLoader.loadIcon(iconname));
		button.addActionListener(buttonListener);
		toolbar.add(button);
	}
	
	

	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button=(JButton) e.getSource();
			String name=button.getName();
			pressedButton(Integer.parseInt(name));
		}
		
	}
}
