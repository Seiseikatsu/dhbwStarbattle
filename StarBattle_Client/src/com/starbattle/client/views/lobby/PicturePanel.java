package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.starbattle.client.layout.BackgroundViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class PicturePanel extends BackgroundViewModel {

	
	public PicturePanel() {
		initLayout();
	}
	
	private void initLayout()
	{
		view.setBackgroundImage(ResourceLoader.loadImage("space_background.jpg"));
		view.startRotating(300, 300, 0.2f);
		view.setBorder(BorderFactory.createLoweredBevelBorder());
		view.setLayout(new BorderLayout());
	
		
		view.add(new JLabel(ResourceLoader.loadIcon("title.png")),BorderLayout.CENTER);		
		
		
		JToolBar titleBar=new JToolBar();
		titleBar.setFloatable(false);
		
		JLabel version=new JLabel("Version 0.1",ResourceLoader.loadIcon("information.png"),0);
		titleBar.add(version);
		
		view.add(titleBar,BorderLayout.NORTH);
	}
}
