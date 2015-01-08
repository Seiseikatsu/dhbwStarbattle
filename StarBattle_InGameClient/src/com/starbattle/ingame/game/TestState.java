package com.starbattle.ingame.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
       particleContainer.render();
    }

    @Override
    public void update(GameContainer arg0, StateBasedGame arg1, int delta) throws SlickException
    {
       particleContainer.update(delta);
    }

    @Override
    public int getID()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
