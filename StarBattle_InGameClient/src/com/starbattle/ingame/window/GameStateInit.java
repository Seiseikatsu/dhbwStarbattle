package com.starbattle.ingame.window;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.network.ClientNetworkConnection;
import com.starbattle.ingame.render.RenderSettings;

public class GameStateInit extends StateBasedGame
{

    private ClientNetworkConnection networkConnection;
    
    public GameStateInit(ClientNetworkConnection networkConnection)
    {
        super("Starbattle Client");
        this.networkConnection=networkConnection;
        
    }
    
    public void setRenderSettings(RenderSettings settings)
    {
        
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        
        
    }

    
    
}
