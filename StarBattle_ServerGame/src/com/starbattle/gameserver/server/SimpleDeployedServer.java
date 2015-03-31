package com.starbattle.gameserver.server;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

import com.starbattle.gameserver.exceptions.ServerStartException;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.server.NetworkServer;

/**
 * 
 * This is the implementation of the simple deployed server. It creates a new
 * server for every game.
 * 
 * @author Roland
 *
 */
public class SimpleDeployedServer implements GameServerInterface {

	private NetworkServer server = new NetworkServer();
	private PlayerUpdateListener playerUpdate;

	public SimpleDeployedServer() {
		try {
			ServerSocketChannel s = ServerSocketChannel.open();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setReceiveListener(PlayerUpdateListener playerUpdateListener) {
		this.playerUpdate = playerUpdateListener;
	}

	@Override
	public void sendGameUpdate(NP_GameUpdate update) {
		server.getSendConnection().sendUDPToAll(update);
	}

	@Override
	public void openConnection() throws ServerStartException {
		// find
		try {
			server.open(0, 0);
			server.setServerListener(new DefaultPlayerConnection(playerUpdate));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerStartException(0, 0);
		}
	}

	@Override
	public void closeConnection() {
		server.close();
	}

	@Override
	public ConnectionDetails getConnection() {

		return new ConnectionDetails(server.getTcpPort(), server.getUdpPort());
	}

}
