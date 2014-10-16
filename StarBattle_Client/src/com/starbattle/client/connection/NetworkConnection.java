package com.starbattle.client.connection;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.network.client.NetworkClient;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.ConnectionListener;

public class NetworkConnection {

	private NetworkClient client;
	private NetworkObjectResolver networkObjectResolver;
	private NetworkConnectionListener networkConnectionListener;
	
	public NetworkConnection(NetworkConnectionListener networkConnectionListener) {
		this.networkConnectionListener=networkConnectionListener;
		networkObjectResolver = new NetworkObjectResolver();
		client = new NetworkClient();
	}

	public void start(String ip, int tcp_port, int udp_port) throws IOException {
		client.setConnectionListener(new Listener());
		client.connect(ip, tcp_port, udp_port);
	}

	public void setRegistrationListener(RegistrationListener listener) {
		networkObjectResolver.setRegistrationListener(listener);
	}
	
	public SendServerConnection getSendConnection()
	{
		return client.getSendConnection();
	}

	private class Listener implements ConnectionListener {

		@Override
		public void onConnect(Connection connection) {
			networkConnectionListener.onConnect();
		}

		@Override
		public void onDisconnect(Connection connection) {
			networkConnectionListener.onDisconnect();
		}

		@Override
		public void onReceive(Connection connection, Object message) {
			networkObjectResolver.income(message);
		}

	}

}
