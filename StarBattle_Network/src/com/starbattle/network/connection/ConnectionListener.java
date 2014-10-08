package com.starbattle.network.connection;

import com.esotericsoftware.kryonet.Connection;

public interface ConnectionListener {

	
	public void onConnect();
	
	public void onDisconnect();
	
	public void onReceive(Connection connection, Object message);
	
}
