package com.starbattle.ingame.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.GameCore;
import com.starbattle.ingame.resource.ResourceContainer;

public class TestState extends BasicGameState
{
    
    private GameCore gameCore;
     
    public TestState(ResourceContainer resources)
    {
    	gameCore=new GameCore(resources);	
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
    	super.enter(container, game);
    	
    	//init game
    	gameCore.loadMap("testmap");
        gameCore.setInput(container.getInput());
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
    	
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
         return GameStates.TEST_STATE.ordinal();
    }

}
