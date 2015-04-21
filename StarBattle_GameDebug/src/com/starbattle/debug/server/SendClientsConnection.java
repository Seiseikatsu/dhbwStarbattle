package com.starbattle.debug.server;

public interface SendClientsConnection {

	public void sendToClients(Object object);
	
	public void sendToClient(String name, Object object);
}
