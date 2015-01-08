package com.starbattle.ingame.exception;

public class ClientLaunchException extends Exception
{

    public ClientLaunchException()
    {
        super("Failed to start Client - check Startup Settings");
    }
    
}
