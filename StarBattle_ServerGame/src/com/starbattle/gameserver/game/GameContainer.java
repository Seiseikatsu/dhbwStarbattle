package com.starbattle.gameserver.game;

import com.starbattle.gameserver.battle.BattleField;
import com.starbattle.gameserver.exceptions.ServerMapException;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.game.mode.PlayerRespawnListener;
import com.starbattle.gameserver.main.BattleInitialization;
import com.starbattle.gameserver.main.BattleParticipant;
import com.starbattle.gameserver.map.MapBorder;
import com.starbattle.gameserver.map.ServerMap;
import com.starbattle.gameserver.map.collision.CollisionDetection;
import com.starbattle.gameserver.object.GameControl;
import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;

public class GameContainer {

	private PlayerList playerList = new PlayerList();
	private ServerMap serverMap;
	private GameMode gameMode;
	private GameConnection gameUpdate;
	private BattleField battleField;

	public GameContainer(BattleInitialization init) throws ServerMapException {

		System.out.println("Init Game Container:");

		// load map
		System.out.println("Load Map...");
		serverMap = new ServerMap(init.getBattleSettings().getMapName());

		// init mode
		System.out.println("Load Mode...");
		gameMode = init.getBattleSettings().getMode();
		// create collision mapping
		CollisionDetection collisionDetection = new CollisionDetection(serverMap.getCollisionMap());
		gameUpdate = new GameConnection(this);
		EffectTrigger effectTrigger = gameUpdate.createEffectTrigger();

		// init battlefield
		MapBorder mapBorder = serverMap.getMapBorder();
		battleField = new BattleField(mapBorder,playerList,gameMode);

		// create game control interface for player objects to control the game actions
		GameControl control = new GameControl();
		control.setCollisionDetection(collisionDetection);
		control.setEffectTrigger(effectTrigger);
		control.setMapBorder(mapBorder);
		control.setProjectileEmitter(battleField.createEmitter());

		// init players
		System.out.println("Load Players...");
		playerList.setRespawnListener(new PlayerRespawnListener() {
			@Override
			public void playerRespawned(GamePlayer player) {

				gameMode.onPlayerRespawn(player);
			}
		});
		for (BattleParticipant participant : init.getBattleParticipants()) {
			playerList.initPlayer(participant, control);
		}

	}

	public void startGame() {
		// set spawn location for players
		playerList.initSpawn(serverMap.getSpawnPoints());
		// start game mode
		gameMode.onGameInit(this);
		System.out.println("GameContainer started!");
	}

	public void updateGame(float delta) {

		battleField.update(delta);
		for (GamePlayer player : playerList.getPlayers()) {
			player.update(delta);
			// check for player falling out of map
			if (player.isAlive()) {
				// do suffocation air reduction
				player.getAttributes().getHealth().takeDamage(new Damage(gameMode.getAirLose()));
				if (!player.isAlive()) {
					// inform game mode for suffocation
					gameMode.onSuffocation(player);
				}
				if (serverMap.getMapBorder().isBelowBorder(player.getLocation())) {
					// inform game mode
					gameMode.onFallingOutOfMap(player);
				}
			}		
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
