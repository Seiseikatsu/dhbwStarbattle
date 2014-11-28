package com.starbattle.client.views.profile;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.starbattle.client.resource.GUIDesign;

public class ProfilePainter {

	private Font bigFont =GUIDesign.labelFont.deriveFont(Font.BOLD, 30);
	
	public ProfilePainter()
	{
		
	}
	
	
	public void paintProfile(Graphics g, ProfileViewData player)
	{
		int x=100;
		int y=50;
		
		paintLevel(g, x, y, player.getLevel(), player.getExp());
		y+=50;
		paintExpBar(g, x,y,player.getMinexp(), player.getExp(), player.getMaxexp());
		
	}
	
	private void paintLevel(Graphics g,int x, int y, int level , int exp)
	{
		g.setColor(new Color(0,0,0,50));	
		g.fillRect(x,y,800,36);
		g.setColor(new Color(50,50,50));
		g.fillRect(x,y+36,800,2);
		
		g.setColor(Color.WHITE);
		g.setFont(bigFont);
		g.drawString("Level",x,y+30);
		g.drawString("EXP",x+300,y+30);
		
		g.setColor(new Color(90,200,255));
		g.drawString(""+level,x+100,y+30);
		g.drawString(""+exp,x+370,y+30);
		
		
	}
	
	private void paintExpBar(Graphics g,int x, int y, int minexp, int exp, int maxexp)
	{		
		int w=800;
		int h=40;
		
		int b=2;
		
		g.setColor(new Color(50,50,50));
		g.fillRect(x-b,y-b,w+b*2,h+b*2);
		
		g.setColor(new Color(100,100,100));
		g.fillRect(x,y,w,h);
		
		g.setColor(new Color(90,200,255));
		float proz=((float)exp-minexp)/((float)maxexp-minexp);
		int fillw=(int) (proz*w);
		g.fillRect(x,y,fillw,h);
		
		g.setColor(new Color(0,0,0,20));
		for(int i=0; i<w; i+=20)
		{
			g.fillRect(i+x,y,2,h);
		}
		
		g.setColor(new Color(220,220,220));
		g.setFont(bigFont);
		g.drawString(""+minexp,x+10,y+h-10);
		
		FontMetrics fm=g.getFontMetrics(bigFont);
		int sx=x+w-10-fm.stringWidth(""+maxexp);
		g.drawString(""+maxexp,sx,y+h-10);
	}
}
