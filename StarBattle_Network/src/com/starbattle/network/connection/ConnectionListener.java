package com.starbattle.network.connection;

import com.esotericsoftware.kryonet.Connection;

public interface ConnectionListener {

	
	public void onConnect(Connection connection);
	
	public void onDisconnect(Connection connection);
	
	public void onReceive(Connection connection, Object message);
	
}
