package com.starbattle.ingame.render;



import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.resource.PlayerGraphics;
import com.starbattle.ingame.resource.ResourceContainer;

public class DebugRender {

	
	private ResourceContainer resourceContainer;
	private PlayerRender playerRender;
	
	public DebugRender(ResourceContainer resourceContainer) {
		this.resourceContainer=resourceContainer;
		playerRender=new PlayerRender(resourceContainer);
	}
	
	
	private float[] angles={0,0,0,0,0};
	
	private float angl=0.1f;
	public void render(Graphics g)
	{
		float x=Mouse.getX();
		float y=Display.getHeight()-Mouse.getY()+70;
		
		//beine
		angles[0]=(float) (Math.sin(angl*0.9)*50);
		angles[2]=(float) (-Math.sin(angl)*60);
		
		//arme
		angles[1]=(float) (Math.sin(angl/0.8)*40);
		angles[3]=(float) (-Math.sin(angl/1.5)*100);
		
		
		angles[4]++;
		
		angl*=1.01f;
		
		//playerRender.render(g, new Location(x, y), PlayerGraphics.ASTRONAUT, angles,false);	
		
		float[] anlges2=angles.clone();
		anlges2[4]*=2;
	//	playerRender.render(g, new Location(x+150, y+50), PlayerGraphics.ALIEN, anlges2,true);
	
	}
}
