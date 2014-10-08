package com.starbattle.network.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.starbattle.network.connection.ConnectionListener;

public class ServerConnectionController {

	private ConnectionListener listener;
	private SendClientConnection sendConnection;
	
	public ServerConnectionController(final Server server)
	{
		//create send connection
		sendConnection=new SendClientConnection(new SendClientListener() {
			
			@Override
			public void sendUDPToAll(Object message) {
				// TODO Auto-generated method stub
				server.sendToAllUDP(message);
			}
			
			@Override
			public void sendUDPTo(int id, Object message) {
				// TODO Auto-generated method stub
				server.sendToUDP(id, message);
			}
			
			@Override
			public void sendTCPToAll(Object message) {
				// TODO Auto-generated method stub
				server.sendToAllTCP(message);
			}
			
			@Override
			public void sendTCPTo(int id, Object message) {
				// TODO Auto-generated method stub
				server.sendToTCP(id, message);
			}
		});
		
		//Default Impl
		listener=new ConnectionListener() {
			
			@Override
			public void onReceive(Connection connectionn, Object message) {
				// TODO Auto-generated method stub
				System.out.println("Server received object: "+message);
			}
			
			@Override
			public void onDisconnect() {
				// TODO Auto-generated method stub
				System.out.println("Server disconnected!");
			}
			
			@Override
			public void onConnect() {
				// TODO Auto-generated method stub
				System.out.println("Server connected!");
			}

		};
	}
	
	public SendClientConnection getSendConnection() {
		return sendConnection;
	}
	
	public void receivedObject(Connection connection, Object message)
	{
		listener.onReceive(connection, message);
	}
	
	public void connected()
	{
		listener.onConnect();
	}
	
	public void disconnected()
	{
		listener.onDisconnect();
	}
		
	public void setListener(ConnectionListener listener) {
		this.listener = listener;
	}
	
}
