package com.starbattle.network.server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.kryonet.Server;
import com.starbattle.network.connection.NetworkRegister;
import com.starbattle.network.connection.ConnectionListener;

public class NetworkServer {

	private Server server;
	private ServerConnectionController connectionController;

	public NetworkServer() {
		server = new Server() {
			protected Connection newConnection() {
				return new PlayerConnection();
			}
		};

		connectionController = new ServerConnectionController(server);

		// register networkclasses on server
		NetworkRegister register = new NetworkRegister();
		register.register(server);

		server.addListener(new Listener() {
			public void disconnected(Connection c) {
				connectionController.disconnected(c);
			}

			public void connected(Connection c) {
				connectionController.connected(c);
			}

			public void received(Connection connection, Object message) {
				connectionController.receivedObject(connection, message);
			}
		});
	}

	public void open(int tcp_port, int udp_port) throws IOException {
		server.bind(tcp_port, udp_port);
		server.start();
	}

	public Server getServer() {
		return server;
	}

	public ServerConnectionController getConnectionController() {
		return connectionController;
	}

	public void close() {
		server.close();
	}

	public SendClientConnection getSendConnection() {
		return connectionController.getSendConnection();
	}

	public void setServerListener(ConnectionListener listener) {
		connectionController.setListener(listener);
	}
	
	public int getTcpPort()
	{
		return server.getTcpPort();
	}
	
	public int getUdpPort()
	{
		return server.getUdpPort();
	}

}
