package com.starbattle.gameserver.main;

import java.io.IOException;

import com.starbattle.gameserver.exceptions.ServerStartException;
import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.network.server.NetworkServer;

public class StarbattleGameServer {

	private BattleEndListener battleEndListener;
	private BattleSettings battleSettings;
	private NetworkServer server;
	private GameContainer game;
	private int tcp_port, udp_port;

	public StarbattleGameServer(int tcp_port, int udp_port) {

		this.tcp_port=tcp_port;
		this.udp_port=udp_port;
	}


	public void start(BattleSettings settings, BattleEndListener battleEndListener) throws ServerStartException {
		this.battleSettings = settings;
		this.battleEndListener = battleEndListener;

		server = new NetworkServer();
		try {
			server.open(tcp_port, udp_port);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerStartException(tcp_port, udp_port);
		}
		game.startGame();
	}

}
