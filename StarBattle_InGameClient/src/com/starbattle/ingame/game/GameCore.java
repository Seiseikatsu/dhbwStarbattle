package com.starbattle.ingame.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.particles.ParticleContainer;

public class GameCore {

	private ParticleContainer particleContainer=new ParticleContainer();
	private GameMap map=new GameMap();
	private Input input;  
	private float mapx=0;
	private float mapy=0;
	public GameCore()
	{

	}
	
	public void loadMap(String mapName)
	{
		map.loadMap(mapName);
	}
	
	public void renderGame(Graphics g)
	{
		
			
		map.renderBackground((int)mapx, (int)mapy);
		
		map.renderForeground((int)mapx, (int)mapy);
		particleContainer.render(g);
		  
	}
	
    private float angle,radius=300;
	public void updateGame(int delta)
	{
		mapx-=1;
		 particleContainer.update(delta);
	      
		 //Particle Demo:
		  angle+=0.05;
	       float x=(float) (500+Math.cos(angle)*radius);
	       float y=(float) (350+Math.sin(angle)*radius);
	       particleContainer.spawnEffect("Splash", x, y);
	        x=(float) (500+Math.cos(angle+Math.PI)*radius);
	        y=(float) (350+Math.sin(angle+Math.PI)*radius);
	       particleContainer.spawnEffect("Splash2", x, y);	       
	       if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
	       {
	    	    x=input.getMouseX();
	    	    y=input.getMouseY();
	    	   particleContainer.spawnEffect("Splash", x, y);
	       }
	}
	
	public void setInput(Input input) {
		this.input = input;
	}
	
}
