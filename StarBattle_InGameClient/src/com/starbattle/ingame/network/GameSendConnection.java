package com.starbattle.ingame.network;

public interface GameSendConnection {

	//Send TCP State-Update
	public void sendTCP(Object object);

	public void sendUDP(Object object);

}
