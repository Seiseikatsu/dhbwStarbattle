package com.starbattle.ingame.render;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.starbattle.ingame.game.bullets.BulletObject;
import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.resource.ParticleGraphics;
import com.starbattle.ingame.resource.ResourceContainer;

public class BulletRender extends RenderResource {


	public BulletRender(ResourceContainer resourceContainer) {
		super(resourceContainer);
	}
	
	public void renderBullet(Graphics g, BulletObject bullet, Viewport view)
	{
		Location pos=view.getScreenLocation(bullet.getLocation());
		float x=pos.getXpos();
		float y=pos.getYpos();
		
		ParticleGraphics particleGraphics = resourceContainer.getParticleGraphics();
		
		Image image=particleGraphics.getPlasmaBall();

		image.drawCentered(x, y);
		
	}
}
