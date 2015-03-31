package com.starbattle.ingame.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.exception.ClientLaunchException;
import com.starbattle.ingame.exception.ServerConnectionException;
import com.starbattle.ingame.game.GameStateInit;
import com.starbattle.ingame.network.ClientNetworkConnection;
import com.starbattle.ingame.network.ConnectionChangeInterface;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.ingame.settings.GameclientSettings;

public class InGameClient {

    public static boolean DEBUG_MODE=false;
    private AppGameContainer gameContainer;
    private GameStateInit gameInit;
    private GameclientSettings settings;
    private ClientNetworkConnection networkConnection;
    
    
	public InGameClient(GameclientSettings settings)
	{
	    this.settings=settings;
	}

	public void open(ClientNetworkConnection networkConnection) throws ClientLaunchException, ServerConnectionException {
	
	    gameInit=new GameStateInit(networkConnection);
	    this.networkConnection=networkConnection;
	    try
        {
            gameContainer=new AppGameContainer(gameInit);
            doGameSettings();
            //start
            gameContainer.start();
            //start network
            networkConnection.setConnectionChangeInterface(new ConnectionChange());
            networkConnection.openConnection();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
            throw new ClientLaunchException();
        }    
	}
	
	public void close(){
	    if(gameContainer!=null)
	    {
	        gameContainer.destroy();
	    }
	    if(networkConnection!=null)
	    {
	        networkConnection.closeNetworkConnection();
	    }
	}
	
	private void doGameSettings() throws SlickException
	{
	    //Set Window Resolution 
	    int screenWidht=settings.getWindowResolution().getScreenWidth();
	    int screenHeight=settings.getWindowResolution().getScreenHeight();
	    boolean fullscreen=settings.isFullscreenMode();
	    gameContainer.setDisplayMode(screenWidht,screenHeight,fullscreen);
	    gameContainer.setShowFPS(false);
	    
	    //Sound Settings
	    gameContainer.setMusicOn(!settings.isMusicOff());
	    gameContainer.setSoundOn(!settings.isSoundOff());
	    gameContainer.setMusicVolume(settings.getMusicVolume());
	    gameContainer.setSoundVolume(settings.getSoundVolume());
	    
	    //Graphic Settings
	    RenderSettings renderSettings=new RenderSettings(settings.getGraphicSettings());
	    gameContainer.setTargetFrameRate(renderSettings.getTargetFPS());
	    gameContainer.setVSync(renderSettings.isVSync());
	    gameContainer.setSmoothDeltas(renderSettings.isSmoothDeltas());
	    
	    if(DEBUG_MODE)
	    {
	        gameContainer.setShowFPS(true);
	    }
	}
	
	public AppGameContainer getGameContainer()
    {
        return gameContainer;
    }
	
	public ClientNetworkConnection getNetworkConnection()
    {
        return networkConnection;
    }
	
	private class ConnectionChange implements ConnectionChangeInterface{

        @Override
        public void clientDisconnected()
        {
            close();
        }
	    
	}
	
}
