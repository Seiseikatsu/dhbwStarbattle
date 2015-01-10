package com.starbattle.ingame.exception;

public class ServerConnectionException extends Exception
{

    public ServerConnectionException()
    {
        super("Failed connecting to Game Server - Check Network Settings");
    }
    
}
