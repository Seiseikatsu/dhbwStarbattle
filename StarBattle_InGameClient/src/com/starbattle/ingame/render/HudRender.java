package com.starbattle.ingame.render;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.render.hud.AirBarRender;
import com.starbattle.ingame.resource.ResourceContainer;

public class HudRender {

	private ResourceContainer resourceContainer;
	private AirBarRender airBarRender;

	public HudRender(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
		airBarRender = new AirBarRender(resourceContainer);
	}
	
	float air = 1f;
	public void renderHud(Graphics g) {
		float x = 485;
		float y = 8;
		
		air-=0.001;
		airBarRender.render(g, x, y, air);
	}

}
