package com.starbattle.gameserver.main;

import java.util.List;

import com.starbattle.gameserver.exceptions.ServerMapException;
import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.server.PlayerConnection;

public class StarbattleGameControl implements StarbattleGame {

	private BattleEndListener battleEndListener;
	private BattleSettings battleSettings;
	private List<BattleParticipant> battleParticipants;

	private GameContainer game;
	private int gameID;

	public StarbattleGameControl(int id) {
		this.gameID = id;
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

	@Override
	public void startBattle(BattleInitialization battleInitialization, BattleEndListener battleEndListener) {
		this.battleSettings = battleInitialization.getBattleSettings();
		this.battleParticipants = battleInitialization.getBattleParticipants();
		this.battleEndListener = battleEndListener;
		try {
			game = new GameContainer(battleInitialization);
		} catch (ServerMapException e) {
			e.printStackTrace();
		}
		game.startGame();
	}

	@Override
	public NP_GameUpdate getGameUpdate() {
		return game.getGameUpdate().getGameUpdate();
	}

	@Override
	public void updatePlayer(NP_PlayerUpdate update, String accountName) {
		if(game==null)
		{
			System.err.println("Game not init");
			return;
		}
		game.getGameUpdate().receivedPlayerUpdate(update, accountName);
	}

	@Override
	public void playerConnected(String accountName) {
		if(game==null)
		{
			System.err.println("Game not init");
			return;
		}
		game.getGameUpdate().playerConnected(accountName);
	}

	@Override
	public void playerDisconnected(String accountName) {
		if(game==null)
		{
			System.err.println("Game not init");
			return;
		}
		game.getGameUpdate().playerDisonnected(accountName);
	}

	@Override
	public void updateGame(double delta) {
		game.updateGame((float)delta);
	}

	@Override
	public void sendToAllPlayersUDP(Object object) {
		for (BattleParticipant battleParticipant : battleParticipants) {
			battleParticipant.getConnection().sendUDP(object);
		}
	}


}
