package com.starbattle.network.client;

public interface SendServerListener {

	public void sendTCP(Object message);
	
	public void sendUDP(Object message);
	
	
	
}
