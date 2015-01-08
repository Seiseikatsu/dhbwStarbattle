package com.starbattle.ingame.network;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.ingame.exception.ServerConnectionException;
import com.starbattle.network.client.NetworkClient;
import com.starbattle.network.connection.ConnectionListener;

public abstract class ClientNetworkConnection
{
    
    protected NetworkClient client;
    protected NetworkObjectResolver resolver;
    protected ConnectionChangeInterface changeInterface;
    
    public ClientNetworkConnection()
    {
       
    }
    
    public void openConnection() throws ServerConnectionException
    {
        createNetworkConnection();
        client.setConnectionListener(new ConnectionListener() {
            
            @Override
            public void onReceive(Connection connection, Object message)
            {
              resolver.resolveMessage(message);
            }
            
            @Override
            public void onDisconnect(Connection connection)
            {
                changeInterface.clientDisconnected();
            }
            
            @Override
            public void onConnect(Connection connection)
            {
               //connected to server
            }
        });
    }
    
    public void setObjectReceiveListener(ObjectReceiveListener listener){
        resolver.setObjectReceiveListener(listener);
    }
    
    public void setConnectionChangeInterface(ConnectionChangeInterface changeInterface)
    {
        this.changeInterface = changeInterface;
    }
    
    protected abstract void createNetworkConnection() throws ServerConnectionException;
    
    public abstract void closeNetworkConnection();
    
 
    
}
