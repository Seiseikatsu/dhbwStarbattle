package com.starbattle.mapeditor.gui.dialogs;


import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.starbattle.mapeditor.window.ContentPanel;
import com.starbattle.mapeditor.window.VerticalLayout;

public class NewMapDialog extends ContentPanel {

	private JTextField width=new JTextField(5);
	private JTextField height=new JTextField(5);
	
	public NewMapDialog()
	{
		width.setText("50");
		height.setText("30");
		
		view.setLayout(new VerticalLayout());
		
		JLabel title=new JLabel("New Map");
		title.setFont(title.getFont().deriveFont(20f));
		
		view.add(title);
		view.add(Box.createVerticalStrut(10));
		view.add(new JLabel("Map Width"));
		view.add(width);
		view.add(Box.createVerticalStrut(5));
		view.add(new JLabel("Map Height"));
		view.add(height);
	}
	
	public int getMapWdith() throws Exception
	{
		return Integer.parseInt(width.getText());
	}
	
	public int getMapHeight() throws Exception
	{
		return Integer.parseInt(height.getText());
	}
	
}
