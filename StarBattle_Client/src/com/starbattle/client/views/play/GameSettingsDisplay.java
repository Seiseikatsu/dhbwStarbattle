package com.starbattle.client.views.play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

import com.starbattle.client.layout.DesignLabel;
import com.starbattle.client.layout.VerticalLayout;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.ResourceLoader;

public class GameSettingsDisplay extends ViewModel{

	
	public GameSettingsDisplay()
	{
		initLayout();
	}
	
	private void initLayout()
	{
		view.setLayout(new BorderLayout());
		JPanel header=new JPanel(new FlowLayout(FlowLayout.LEFT));
		header.setBackground(new Color(100,100,100));
		header.setBorder(BorderFactory.createLineBorder(new Color(50,50,50), 2));
		header.add(Box.createHorizontalStrut(50));
		header.add(new DesignLabel("Playmode", ResourceLoader.loadIcon("group.png")));
		header.add(Box.createHorizontalStrut(100));
		header.add(new DesignLabel("Map", ResourceLoader.loadIcon("tree.png")));
		
		JPanel content=new JPanel();
		content.setBackground(new Color(150,150,150));
		content.setLayout(new VerticalLayout());
		content.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));
		
		for(int i=0; i<5; i++)
		{
			GameModeEntry entry=new GameModeEntry("3v3", "Testmap");
			if(i==3)
			{
				entry.setSelected(true);
			}
			content.add(entry.getView());
		}
		
		view.add(content,BorderLayout.CENTER);
		view.add(header,BorderLayout.NORTH);
		
	}
	
	
	
}
