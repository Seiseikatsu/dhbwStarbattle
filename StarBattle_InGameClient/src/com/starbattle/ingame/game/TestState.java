package com.starbattle.ingame.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TestState extends BasicGameState
{
    
    private GameCore gameCore=new GameCore();
     
    public TestState()
    {
    	
    	
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
    	gameCore.loadMap("testmap");
        gameCore.setInput(container.getInput());
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
    	gameCore.renderGame(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException
    {
   
     gameCore.updateGame(delta); 
     
    }

    @Override
    public int getID()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
