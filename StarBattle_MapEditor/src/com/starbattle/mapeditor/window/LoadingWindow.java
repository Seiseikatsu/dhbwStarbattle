package com.starbattle.mapeditor.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

import com.starbattle.mapeditor.resource.ResourceLoader;

public class LoadingWindow {

	private WindowContainer window;
	
	public LoadingWindow()
	{
		window=new WindowContainer(new Dimension(300,100), "Map Editor Loading...");
		window.setView(new LoadingContent());
	}
	
	public void open()
	{
		window.open();
	}
	
	public void close()
	{
		window.close();
	}
	
	private class LoadingContent extends ContentPanel{
	
		public LoadingContent()
		{
			view.setLayout(new BorderLayout());
			view.setBackground(new Color(219,219,219));
			
			JLabel l=new JLabel("Loading Textures...",ResourceLoader.loadAnimatedIcon("loading.gif"),0);
			view.add(l,BorderLayout.CENTER);
		}
		
	}
	
}
