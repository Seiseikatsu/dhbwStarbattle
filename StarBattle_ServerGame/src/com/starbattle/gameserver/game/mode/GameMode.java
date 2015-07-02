package com.starbattle.gameserver.game.mode;

import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.physics.GravityValues;
import com.starbattle.gameserver.main.BattleEndListener;
import com.starbattle.gameserver.main.BattleResults;
import com.starbattle.gameserver.map.SpawnPoint;
import com.starbattle.gameserver.map.SpawnPointList;
import com.starbattle.gameserver.player.GamePlayer;

public abstract class GameMode implements GameModeInterface {

	protected GameContainer game;
	protected GamePoints points;
	protected SpawnPointList spawnPointList;
	protected float airLose = 0.01f;
	protected int pointLimit;
	private BattleEndListener battleEndListener;
	protected float gravity = GravityValues.ERDE.getGravity();
	protected int jumpsInAir = 1;

	public GameMode() {

	}

	public void setBattleEndListener(BattleEndListener battleEndListener) {
		this.battleEndListener = battleEndListener;
	}

	@Override
	public void onGameInit(GameContainer game) {
		this.game = game;
		this.spawnPointList = game.getServerMap().getSpawnPoints();
		this.points = new GamePoints(game.getPlayerList());
		game.getPlayerList().setPhysics(gravity, jumpsInAir);
	}

	protected void endGame(Team winnerTeam) {
		BattleResults results = new BattleResults(game.getPlayerList());
		results.teamWon(winnerTeam);
		battleEndListener.battleEnd(results);
	}

	protected void endGame(GamePlayer winnerPlayer) {

		BattleResults results = new BattleResults(game.getPlayerList());
		results.playerWon(winnerPlayer);
		battleEndListener.battleEnd(results);
	}

	protected void endGame() {
		BattleResults results = new BattleResults(game.getPlayerList());
		battleEndListener.battleEnd(results);
	}

	protected void defaulTeamEndCheck(int pointLimit) {
		Team team = points.getTeamWithMostPoints();
		if (points.getTeamPoints(team) >= pointLimit) {
			endGame(team);
		}
	}

	protected void defaulPlayerEndCheck(int pointLimit) {
		GamePlayer player = points.getPlayerWithMostPoints();
		if (player.getAttributes().getPoints() >= pointLimit) {
			endGame(player);
		}
	}

	protected void defaultRespawn(GamePlayer player, int respawnTime) {
		SpawnPoint spawnpoint = spawnPointList.getRandomSpawnPoint(player.getAttributes().getTeam());
		player.startRespawntimer(spawnpoint, respawnTime);
	}

	protected void defaultDamageProcess(GamePlayer player, Damage damage, int pointLose, int killerPointAdd) {
		player.getAttributes().takeDamge(damage);
		if (player.getAttributes().getHealth().isDead()) {
			// player lose points
			points.addPlayerPoints(player, -pointLose);

			// player add points
			points.addPlayerPoints(damage.getDamageDealer(), killerPointAdd);

			// start respawn
			int time = getRespawnTime(player);
			defaultRespawn(player, time);
		}
	}

	protected void defaultKillPlayer(GamePlayer player, int pointLose) {
		// player lose points
		points.addPlayerPoints(player, -pointLose);
		// kill player
		player.getAttributes().getHealth().kill();
		// start respawn
		int time = getRespawnTime(player);
		defaultRespawn(player, time);
	}

	protected abstract int getRespawnTime(GamePlayer player);

	public float getAirLose() {
		return airLose;
	}

	protected Team[] defaultTeamsInit(int players) {
		Team[] team = new Team[players];
		for (int i = 0; i < players; i++) {
			if (i % 2 == 0) {
				team[i] = Team.BLUE_TEAM;
			} else {
				team[i] = Team.RED_TEAM;
			}
		}
		return team;
	}

	protected Team[] defaultNoTeamsInit(int players) {
		Team[] team = new Team[players];
		for (int i = 0; i < players; i++) {
			team[i] = Team.NO_TEAM;
		}
		return team;
	}
}
