package com.starbattle.ingame.network;

import com.starbattle.network.connection.objects.game.NP_GameEnd;
import com.starbattle.network.connection.objects.game.NP_GameStart;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;

public class NetworkObjectResolver
{
    
    private ObjectReceiveListener listener;
    
    public NetworkObjectResolver()
    {
        
    }
    
    public void resolveMessage(Object message){
         
        if(listener!=null)
        {
            if(message instanceof NP_GameUpdate)
            {
            	listener.updateGame((NP_GameUpdate)message);            	
            }
            else if(message instanceof NP_GameStart)
            {
            	listener.startGame();
            }
            else if(message instanceof NP_GameEnd)
            {
            	listener.endGame();
            }
        }
    }

    public void setObjectReceiveListener(ObjectReceiveListener listener)
    {
       this.listener=listener;
    }
    
    
    

}
