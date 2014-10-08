package com.starbattle.network.client;

import java.io.IOException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.starbattle.network.connection.ConnectionListener;
import com.starbattle.network.connection.NetworkRegister;

public class NetworkClient {

	private Client client;
	private ClientConnectionController connectionController;
	public final static int TIMEOUT = 5000;
	
	public NetworkClient() {

		client = new Client();
		
		//add connection controller
		connectionController=new ClientConnectionController(client);
		
		// register network classes to client
		NetworkRegister register = new NetworkRegister();
		register.register(client);

		// add client listeners
		client.addListener(new ThreadedListener(new Listener() {

			public void connected(Connection connection) {
				connectionController.connected();
			}

			public void disconnected(Connection connection) {
				connectionController.disconnected();
			}

			public void received(Connection connection, Object object) {
				connectionController.receivedObject(connection,object);
			}

		}));
		client.start();
		
	}
	
	public void setConnectionListener(ConnectionListener listener)
	{
		connectionController.setListener(listener);
	}
	
	public SendServerConnection getSendConnection()
	{
		return connectionController.getSendConnection();
	}
	
	public void connect(String ip, int tcp_port, int udp_port) throws IOException
	{
		client.connect(TIMEOUT, ip	, tcp_port, udp_port);
	}
	
	public boolean isConnected()	
	{
		return client.isConnected();
	}
	
	public void close()
	{
		client.close();
	}

	
}
