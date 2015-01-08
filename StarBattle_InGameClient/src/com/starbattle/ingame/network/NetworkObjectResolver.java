package com.starbattle.ingame.network;

public class NetworkObjectResolver
{
    
    private ObjectReceiveListener listener;
    
    public NetworkObjectResolver()
    {
        
    }
    
    public void resolveMessage(Object message){
         
        if(listener!=null)
        {
            /* if(message instanceof ...)
            {
                listener.(....)
            }*/
        }
    }

    public void setObjectReceiveListener(ObjectReceiveListener listener)
    {
       this.listener=listener;
    }
    
    
    

}
