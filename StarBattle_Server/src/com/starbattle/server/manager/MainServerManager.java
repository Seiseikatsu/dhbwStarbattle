package com.starbattle.server.manager;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.network.connection.ConnectionListener;
import com.starbattle.network.server.NetworkServer;
import com.starbattle.network.server.PlayerConnection;
import com.starbattle.network.connection.objects.*;
import com.starbattle.server.player.PlayerContainer;

public class MainServerManager {

	private NetworkServer server;
	private PlayerManager playerManager;
	private PlayerContainer playerContainer;

	public MainServerManager(NetworkServer server) {
		this.server = server;
		playerContainer=new PlayerContainer();
		playerManager=new PlayerManager(server.getSendConnection(),playerContainer);
	}

	public ConnectionListener createListener() {
		return new ConnectionListener() {

			@Override
			public void onDisconnect() {
				// TODO Auto-generated method stub
				System.out.println("Main Server disconnected, Shut down...");
				server.close();
				System.exit(0);
			}

			@Override
			public void onConnect() {
				// TODO Auto-generated method stub
				System.out.println("Main Server online!");
			}

			@Override
			public void onReceive(Connection connection, Object object) {
				// TODO Auto-generated method stub
				receivedObject(connection, object);
			}
		};
	}
	
	private void receivedObject(Connection connection, Object object)
	{
		PlayerConnection player=(PlayerConnection)connection;
		if(object instanceof NP_Login)
		{
			playerManager.tryLogin(player, (NP_Login)object);
		}
		else if(object instanceof NP_Register)
		{
			playerManager.tryRegister(player, (NP_Register)object);
		}
	}
}
