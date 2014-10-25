package com.starbattle.client.views.lobby;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.resource.ResourceLoader;

public class PicturePanel extends ViewModel {

	
	public PicturePanel() {
		initLayout();
	}
	
	private void initLayout()
	{
		setBackgroundImage(ResourceLoader.loadImage("space_background.jpg"));
		setMovementCircle(340, 340, 1.2f);
		startAnimation();
		view.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 3));
		view.setLayout(new BorderLayout());
	
		JLabel title=new DesignLabel("Star Battle Client Version 0.1",new Color(200,200,200) ,14);
		view.add(title,BorderLayout.NORTH);
		view.add(new JLabel(ResourceLoader.loadIcon("title.png")),BorderLayout.CENTER);		
			
	
	}
}
