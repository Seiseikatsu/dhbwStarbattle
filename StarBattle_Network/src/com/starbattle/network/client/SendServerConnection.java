package com.starbattle.network.client;

public class SendServerConnection {

	private SendServerListener listener;
	
	public SendServerConnection(SendServerListener listener)
	{
		this.listener=listener;
	}
	
	public void sendTCP(Object message)
	{
		listener.sendTCP(message);
	}
	
	public void sendUDP(Object message)
	{
		listener.sendUDP(message);
	}
	
	
}
