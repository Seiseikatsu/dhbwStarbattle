package com.starbattle.ingame.debug;

import com.starbattle.ingame.exception.ServerConnectionException;
import com.starbattle.ingame.network.ClientNetworkConnection;
import com.starbattle.ingame.network.ConnectionChangeInterface;

public class DebugNetwork extends ClientNetworkConnection
{

    public DebugNetwork()
    {
     
    }

    @Override
    protected void createNetworkConnection() throws ServerConnectionException
    {
       
        
    }

    @Override
    public void closeNetworkConnection()
    {
        
    }

}
