package com.starbattle.ingame.render.hud;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

import com.starbattle.ingame.resource.ResourceContainer;

public class AirBarRender {

	private final static Color AIR_BAR_COLOR = new Color(120, 220, 255);
	private final static Color AIR_BAR_COLOR2 = new Color(200,230,255);
	private final static float AIR_BAR_WIDTH = 482;
	private final static float AIR_BAR_HEIGHT = 19;
	private final static float AIR_BAR_X = 4;
	private final static float AIR_BAR_Y = 14;
	private ResourceContainer resourceContainer;

	public AirBarRender(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
		
	}

	public void render(Graphics g, float x, float y, float airPercent) {
		Image hud = resourceContainer.getHudGraphics().getAirHUD();
		hud.draw(x, y);
		// draw air bar
		
	
		float xp = x + AIR_BAR_X;
		float yp = y + AIR_BAR_Y;

		if (airPercent > 1) {
			airPercent = 1;
		} else if (airPercent < 0) {
			airPercent = 0;
		}

		float w = AIR_BAR_WIDTH * airPercent;
		
		xp+=AIR_BAR_WIDTH-w;
		
		GradientFill gradientFill=new GradientFill(0, 0, AIR_BAR_COLOR2, 0, 0, AIR_BAR_COLOR);	
		gradientFill.setStart(xp, yp);
		gradientFill.setEnd(xp+AIR_BAR_WIDTH, yp+AIR_BAR_HEIGHT);
		
		
		Rectangle bar=new Rectangle(xp, yp, w, AIR_BAR_HEIGHT);
		g.fill(bar, gradientFill);
	
	}
}
