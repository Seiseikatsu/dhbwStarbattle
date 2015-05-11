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
		String text="R.I.P";
		Font font=resourceContainer.getFonts().getBigText();
		g.setFont(font);
		float y=screenHeight/2+50;
		g.drawString(text,screenWidth/2-font.getWidth(text)/2,y);
		text="Reborn in: "+respawnTime;
		 font=resourceContainer.getFonts().getMediumText();
		g.setFont(font);
		y+=60;
		g.drawString(text,screenWidth/2-font.getWidth(text)/2,y);
		
	}
}
