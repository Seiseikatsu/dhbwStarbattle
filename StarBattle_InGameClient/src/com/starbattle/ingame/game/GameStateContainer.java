package com.starbattle.ingame.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.states.LoadingState;
import com.starbattle.ingame.game.states.TestState;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.ingame.resource.ResourceContainer;

public class GameStateContainer extends StateBasedGame
{

    
    
    public GameStateContainer()
    {
        super("Starbattle Client");

    }
    
    public void setRenderSettings(RenderSettings settings)
    {
        
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
       ResourceContainer resourceContainer=new ResourceContainer();	
      addState(new LoadingState(resourceContainer));
      addState(new TestState(resourceContainer));
    }

    
    
}
