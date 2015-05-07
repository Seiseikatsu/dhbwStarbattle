package com.starbattle.ingame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import com.starbattle.ingame.resource.ResourceContainer;

public class KillscreenRender extends RenderResource{

	
	public KillscreenRender(ResourceContainer resourceContainer) {
		super(resourceContainer);
	}
	
	public void renderKillScreen(Graphics g, int respawnTime)
	{
		g.setColor(new Color(200,0,0,150));
		g.fillRect(0,0,screenWidth,screenHeight);
			
		g.setColor(new Color(1f,1f,1f));
		String text="Respawn in";
		Font font=g.getFont();
		float y=screenHeight/2-30;
		g.drawString(text,screenWidth/2-font.getWidth(text)/2,y);
		text=""+respawnTime;
		y+=30;
		g.drawString(text,screenWidth/2-font.getWidth(text)/2,y);
		
	}
}
