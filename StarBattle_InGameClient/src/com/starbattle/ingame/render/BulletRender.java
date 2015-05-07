package com.starbattle.ingame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.bullets.BulletObject;
import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.resource.ResourceContainer;

public class BulletRender {

	private ResourceContainer resourceContainer;
	
	public BulletRender(ResourceContainer resourceContainer) {
		this.resourceContainer=resourceContainer;
	}
	
	public void renderBullet(Graphics g, BulletObject bullet, Viewport view)
	{
		Location pos=view.getScreenLocation(bullet.getLocation());
		float x=pos.getXpos();
		float y=pos.getYpos();
		
		g.setColor(new Color(200,0,0));
		g.fillOval(x-10,y-10,20,20);
	}
}
