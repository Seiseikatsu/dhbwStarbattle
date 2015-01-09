package com.starbattle.gameserver.exceptions;

public class ServerMapException extends Exception{

	
	public ServerMapException(String error)
	{
		super("Server Map Exception: "
				+error);
	}
	
}
