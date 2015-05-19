package com.starbattle.client.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.starbattle.client.resource.ResourceLoader;

public class LoadingWindow {

	private JFrame window;
	private Dimension size=new Dimension(400,300);
	private float loadingPercent=0;
	private int progress=0,maxProgress;
	
	public LoadingWindow()
	{
		initWindow();
	}
	
	private void initWindow()
	{
		window=new JFrame();
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setIconImage(ResourceLoader.loadImage("windowIcon.png"));
		window.setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 2 - size.width / 2;
		int y = screenSize.height / 2 - size.height / 2 - 40;
		window.setLocation(x, y);
		window.setSize(size);
		window.setBackground(new Color(0, 0, 0, 0)); // transparent background for window
		initLayout();
	
	}
	
	private void initLayout()
	{
		window.setContentPane(new LoadingContent());
	}
	
	private class LoadingContent extends JPanel{
		
		private Image title=ResourceLoader.loadImage("title.png");
		private Image planet=ResourceLoader.loadImage("backgroundPlanet.png",0.5f);
		
		public LoadingContent()
		{
			this.setOpaque(false);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			int tx=0;
			int ty=100;		
			int cx=tx+title.getWidth(null)/2;
			int cy=ty+title.getHeight(null)/2;		
			int px=cx-planet.getWidth(null)/2;
			int py=cy-planet.getHeight(null)/2;
			g.drawImage(planet,px,py,null);
			g.drawImage(title,tx,ty-20,null);
			
			//draw loading bar
			g.setColor(new Color(50,50,50));
			int w=250;
			int h=10;
			int y=cy+10;
			int x=cx-w/2;
			g.fillRect(x,y,w,h);
			
			int cw=(int) (w*loadingPercent);
			
			g.setColor(new Color(100,200,250));
			g.fillRect(x,y,cw,h);
			
			g.setColor(new Color(200,200,200));
			g.drawRect(x,y,w,h);
		}
		
	}
	
	public void setMaxProgress(int maxProgress) {
		progress=0;
		this.maxProgress = maxProgress;
	}
	
	public void loadProgress()
	{
		progress++;
		setLoadingPercent((float)progress/(float)maxProgress);
	}
	
	public void setLoadingPercent(float p)
	{
		loadingPercent=p;
		if(loadingPercent<0)
		{
			loadingPercent=0;
		}
		else if(loadingPercent>1)
		{
			loadingPercent=1;
		}
		window.repaint();
	}
	
	public void open()
	{
		window.setVisible(true);
	}
	
	public void close()
	{		
		window.dispose();
	}
}
