package com.starbattle.gameserver.main;

import java.util.ArrayList;
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
	private boolean running=false;

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
		running=true;
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
	public boolean playerDisconnected(String accountName) {
		if(game==null)
		{
			System.err.println("Game not init");
			return false;
		}
		game.getGameUpdate().playerDisonnected(accountName);
		//check if everyone is disconnected
		return game.getPlayerList().isEveryoneDisconnected();
	}

	@Override
	public void updateGame(double delta) {
		game.updateGame((float)delta);
	}

	@Override
	public void sendToAllPlayersUDP(Object object) {
		for (BattleParticipant battleParticipant : battleParticipants) {
			battleParticipant.getConnection().sendTCP(object);
		}
	}
	

	@Override
	public void sendToAllPlayersTCP(Object object) {
		for (BattleParticipant battleParticipant : battleParticipants) {
			battleParticipant.getConnection().sendTCP(object);
		}
	}


	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public List<PlayerConnection> getPlayers() {
		List<PlayerConnection> players=new ArrayList<PlayerConnection>();
		for(BattleParticipant participant: battleParticipants)
		{
			players.add(participant.getConnection());
		}
		return players;
	}


}
