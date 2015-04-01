package com.starbattle.gameserver.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.exceptions.ServerStartException;
import com.starbattle.gameserver.main.StarbattleGameControl;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.server.NetworkServer;

/**
 * Implementation of one server for all games
 * 
 * @author RoO
 *
 */
public class SingleGameServer implements GameServerInterface {

	private static NetworkServer server = new NetworkServer();
	private static List<GameConnectionResolver> games = new ArrayList<GameConnectionResolver>();

	private static int TCP_PORT = 55777;
	private static int UDP_PORT = 55777;

	private StarbattleGameControl game;
	private GameConnectionResolver connection;

	public SingleGameServer(StarbattleGameControl game) {
		// add game control
		this.game = game;
		connection = new GameConnectionResolver(game);
		SingleGameServer.games.add(connection);
	}

	@Override
	public void setReceiveListener(PlayerUpdateListener playerUpdateListener) {
		// add player update listener to game resolver
		connection.setPlayerUpdate(playerUpdateListener);
	}

	@Override
	public void sendGameUpdate(NP_GameUpdate update) {
		// TODO Auto-generated method stub

	}

	@Override
	public void openConnection() throws ServerStartException {
		// on first game open, open server
		if (!SingleGameServer.server.isRunning()) {
			// start server
			try {
				SingleGameServer.server.open(TCP_PORT, UDP_PORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new ServerStartException(TCP_PORT, UDP_PORT);
			}
		}
	}

	@Override
	public void closeConnection() {
		// never close the game server
		// remove game from connection resolver
		SingleGameServer.games.remove(game);
	}

	@Override
	public ConnectionDetails getConnection() {
		return new ConnectionDetails(TCP_PORT, UDP_PORT);
	}

}
