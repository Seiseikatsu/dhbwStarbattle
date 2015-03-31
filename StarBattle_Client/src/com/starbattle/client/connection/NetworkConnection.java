package com.starbattle.client.connection;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.starbattle.client.connection.listener.NetworkCommunctionListener;
import com.starbattle.client.connection.listener.NetworkFriendListener;
import com.starbattle.client.connection.listener.NetworkFriendRequestListener;
import com.starbattle.client.connection.listener.NetworkRegistrationListener;
import com.starbattle.network.client.NetworkClient;
import com.starbattle.network.client.SendServerConnection;
import com.starbattle.network.connection.ConnectionListener;

public class NetworkConnection {

	private NetworkClient client;
	private NetworkObjectResolver networkObjectResolver;
	private NetworkConnectionListener networkConnectionListener;
	private NetworkCommunctionListener networkCommunctionListener;

	public NetworkConnection(NetworkConnectionListener networkConnectionListener) {
		this.networkConnectionListener = networkConnectionListener;
		networkObjectResolver = new NetworkObjectResolver();
		client = new NetworkClient();
	}

	public void start(String ip, int tcp_port, int udp_port) throws IOException {
		client.setConnectionListener(new Listener());
		client.connect(ip, tcp_port, udp_port);
	}

	public void setNetworkCommunctionListener(NetworkCommunctionListener networkCommunctionListener) {
		this.networkCommunctionListener = networkCommunctionListener;
	}

	public void setRegistrationListener(NetworkRegistrationListener listener) {
		networkObjectResolver.setRegistrationListener(listener);
	}

	public void setFriendRequestListener(NetworkFriendRequestListener listener) {
		networkObjectResolver.setFriendRequestListener(listener);
	}

	public void setFriendListener(NetworkFriendListener listener) {
		networkObjectResolver.setFriendListener(listener);
	}

	public SendServerConnection getSendConnection() {
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
			if (networkCommunctionListener != null) {
				networkCommunctionListener.received(message);
			}
		}

	}

	public NetworkClient getClient() {
		return client;
	}

}
