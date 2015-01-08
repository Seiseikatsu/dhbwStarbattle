package com.starbattle.ingame.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.particles.ParticleContainer;

public class TestState extends BasicGameState
{
    
    private ParticleContainer particleContainer=new ParticleContainer();
    
    public TestState()
    {
    }

    @Override
    public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void render(GameContainer arg0, StateBasedGame arg1, Graphics g) throws SlickException
    {
       particleContainer.render(g);
    }

    private float angle,radius=300;
    @Override
    public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException
    {
       particleContainer.update(delta);
       
       angle+=0.05;
       float x=(float) (500+Math.cos(angle)*radius);
       float y=(float) (350+Math.sin(angle)*radius);
       particleContainer.spawnEffect("Splash", x, y);
        x=(float) (500+Math.cos(angle+Math.PI)*radius);
        y=(float) (350+Math.sin(angle+Math.PI)*radius);
       particleContainer.spawnEffect("Splash2", x, y);
       
       
       if(container.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
       {
    	    x=container.getInput().getMouseX();
    	    y=container.getInput().getMouseY();
    	   particleContainer.spawnEffect("Splash", x, y);
       }
    }

    @Override
    public int getID()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
