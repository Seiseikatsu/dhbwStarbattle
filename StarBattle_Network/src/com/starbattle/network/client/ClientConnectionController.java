package com.starbattle.network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.starbattle.network.connection.ConnectionListener;

public class ClientConnectionController {

	private ConnectionListener listener;
	private SendServerConnection sendConnection;

	public ClientConnectionController(final Client client)
	{
		//Send Connections
		sendConnection=new SendServerConnection(new SendServerListener() {
			
			@Override
			public void sendUDP(Object message) {
				// TODO Auto-generated method stub
				client.sendUDP(message);
			}
			
			@Override
			public void sendTCP(Object message) {
				// TODO Auto-generated method stub
				client.sendTCP(message);
			}
		});
		
		//Default Impl
		listener=new ConnectionListener() {
			
			@Override
			public void onReceive(Connection connection, Object message) {
				// TODO Auto-generated method stub
				System.out.println("Client received object: "+message);
			}
			
			@Override
			public void onDisconnect() {
				// TODO Auto-generated method stub
				System.out.println("Client disconnected!");
			}
			
			@Override
			public void onConnect() {
				// TODO Auto-generated method stub
				System.out.println("Client connected!");
			}
		};
	}
	
	public SendServerConnection getSendConnection() {
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
