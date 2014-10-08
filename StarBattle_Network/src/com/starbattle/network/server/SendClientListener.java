package com.starbattle.network.server;

public interface SendClientListener {

	public void sendTCPToAll(Object message);

	public void sendUDPToAll(Object message);

	public void sendTCPTo(int id, Object message);

	public void sendUDPTo(int id, Object message);

}
