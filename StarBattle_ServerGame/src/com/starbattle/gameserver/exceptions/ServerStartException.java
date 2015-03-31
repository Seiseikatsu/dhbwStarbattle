package com.starbattle.gameserver.exceptions;

public class ServerStartException extends Exception{

	public ServerStartException(int tcp, int udp)
	{
		super("Failed to start GameServer with Ports: "+tcp+" / "+udp);
	}
	
}
