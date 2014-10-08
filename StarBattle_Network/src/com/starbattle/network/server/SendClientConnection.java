package com.starbattle.network.server;

public class SendClientConnection {

	
	private SendClientListener listener;
	
	public SendClientConnection(SendClientListener listener)
	{
		this.listener=listener;
	}
	
	public void sendTCPToAll(Object message)
	{
		listener.sendTCPToAll(message);
	}
	
	public void sendUDPToAll(Object message)
	{
		listener.sendUDPToAll(message);
	}
	
	public void sendTCPTo(int id, Object message)
	{
		listener.sendTCPTo(id, message);
	}
	
	public void sendUDPTo(int id, Object message)
	{
		listener.sendUDPTo(id, message);
	}
	
}
