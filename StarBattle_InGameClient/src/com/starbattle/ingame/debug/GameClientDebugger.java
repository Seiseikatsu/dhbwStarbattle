package com.starbattle.ingame.debug;

import com.starbattle.ingame.exception.ClientLaunchException;
import com.starbattle.ingame.exception.ServerConnectionException;
import com.starbattle.ingame.main.InGameClient;

public class GameClientDebugger
{
    
    private InGameClient client;
    
    public GameClientDebugger()
    {
        client=new InGameClient(new DebugSettings());
        try
        {
            InGameClient.DEBUG_MODE=true;
            client.open(new DebugNetwork());                 
        }
        catch (ClientLaunchException | ServerConnectionException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        new GameClientDebugger();           
    }
    

}
