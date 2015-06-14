package com.starbattle.client.views.play.queue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import com.starbattle.client.layout.CustomPaintPanelInterface;
import com.starbattle.client.layout.ViewModel;
import com.starbattle.client.resource.GUIDesign;
import com.starbattle.client.resource.ResourceLoader;

public class WaitingBackground extends ViewModel {

	private Image background = ResourceLoader.loadImage("space_background.jpg");
	private int waitingTime=0;
	private String mode,map;
	
	public WaitingBackground() {
	
		this.setCustomPaintPanel(new CustomPaintPanelInterface() {
			
			@Override
			public void paint(Graphics g, Dimension componentSize) {
				
				
				g.drawImage(background, 0,0,null);
				
				g.setColor(new Color(255,255,255));
				
				g.setFont(GUIDesign.labelFont);
				
				int x=componentSize.width/2;
				int y=100;
				FontMetrics fm=g.getFontMetrics();
				
				String s="Searching "+mode;	
				g.drawString(s,x-fm.stringWidth(s)/2,y);
				y+=20;
				s="On "+map;	
				g.drawString(s,x-fm.stringWidth(s)/2,y);
				y+=30;
				
				g.setColor(new Color(100,100,250));
				s=waitingTime+"s";	
				g.drawString(s,x-fm.stringWidth(s)/2,y);
				
			}
		});
		
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				waitingTime++;
				view.repaint();
			}
		}, 0, 1000);
	}
	
	public void init(String mode, String map)
	{
		waitingTime=0;
		this.map=map;
		this.mode=mode;
		if(map==null)
		{
			this.map="Random Map";
		}
	}
	
	
	
	
}
