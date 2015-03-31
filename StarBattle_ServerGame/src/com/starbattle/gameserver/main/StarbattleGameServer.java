package com.starbattle.gameserver.main;

import com.starbattle.gameserver.exceptions.ServerStartException;
import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.gameserver.server.GameServerInterface;
import com.starbattle.gameserver.server.SimpleDeployedServer;

public class StarbattleGameServer {

	private BattleEndListener battleEndListener;
	private BattleSettings battleSettings;

	private GameServerInterface serverInterface;
	private GameContainer game;
	private int gameID;

	public StarbattleGameServer(int id) {
		this.gameID=id;
	}


	public void start(BattleSettings settings, BattleEndListener battleEndListener) throws ServerStartException {
		this.battleSettings = settings;
		this.battleEndListener = battleEndListener;
		createGameServer();
		game=new GameContainer( serverInterface);
		serverInterface.openConnection();
		game.startGame();
	}
	
	private void createGameServer()
	{
		serverInterface=new SimpleDeployedServer();
	}

	public void closeServer()
	{
		serverInterface.closeConnection();
	}
	
	public BattleSettings getBattleSettings() {
		return battleSettings;
	}
	
	public GameContainer getGame() {
		return game;
	}
	
	public int getGameID() {
		return gameID;
	}

}
