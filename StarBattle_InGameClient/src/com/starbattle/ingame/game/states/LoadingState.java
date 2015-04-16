package com.starbattle.ingame.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.GameCore;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.ingame.resource.player.ResourceException;

public class LoadingState extends BasicGameState
{
    
	private ResourceContainer resourceContainer;
	
    public LoadingState(ResourceContainer resourceContainer)
    {
    	this.resourceContainer=resourceContainer;
    }


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
    	
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
    	//draw loading screen
    	g.drawString("Loading...",10,30);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
    	System.out.println("Start Loading Game...");
    	//load all resources
    	try {
			resourceContainer.loadResources();
			//open game
			System.out.println("Finished loading!");
			game.enterState(GameStates.TEST_STATE.ordinal());
		} catch (ResourceException e) {
			e.printStackTrace();
			container.exit();
		}
    	
    }

    @Override
    public int getID()
    {
        return GameStates.LOADING_STATE.ordinal();
    }

}
