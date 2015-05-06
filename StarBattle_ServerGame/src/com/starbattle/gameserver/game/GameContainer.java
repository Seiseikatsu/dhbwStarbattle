package com.starbattle.gameserver.game;

import com.starbattle.gameserver.exceptions.ServerMapException;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.map.ServerMap;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;

public class GameContainer {

	private PlayerList playerList = new PlayerList();
	private ServerMap serverMap;
	private GameMode gameMode;
	private GameConnection gameUpdate;

	public GameContainer(BattleInitialization init) throws ServerMapException {

		//load map
		serverMap=new ServerMap(init.getBattleSettings().getMapName());
		// init mode
		gameMode = init.getBattleSettings().getMode();
		//create collision mapping
		CollisionDetection collisionDetection=new CollisionDetection(serverMap.getCollisionMap());
		// init players
		for (BattleParticipant participant : init.getBattleParticipants()) {
			playerList.initPlayer(participant,collisionDetection);
		}
	}

	public void startGame() {
		// setup objects
		playerList.setRespawnListener(new PlayerRespawnListener() {
			@Override
			public void playerRespawned(GamePlayer player) {
				gameMode.onPlayerRespawn(player);
			}
		});
		//set spawn location for players
		playerList.initSpawn(serverMap.getSpawnPoints());
		gameUpdate = new GameConnection(this);
		
	}

	public void updateGame(float delta) {
		for(GamePlayer player: playerList.getPlayers())
		{
			player.update(delta);
		}
	}

	public GameConnection getGameUpdate() {
		return gameUpdate;
	}

	public PlayerList getPlayerList() {
		return playerList;
	}

	public ServerMap getServerMap() {
		return serverMap;
	}
}
