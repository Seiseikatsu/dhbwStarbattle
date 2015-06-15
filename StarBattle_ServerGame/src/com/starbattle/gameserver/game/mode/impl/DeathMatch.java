package com.starbattle.gameserver.game.mode.impl;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.item.GameItem;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.player.GamePlayer;

public class DeathMatch extends GameMode {

	private int respawnTime = 5;

	public DeathMatch(int pointLimit) {
		this.pointLimit = pointLimit;
	}

	@Override
	public void onTakingDamage(GamePlayer player, Damage damage) {
		int pointsLose = 1;
		int killerPointsWin = 2;
		// default respawn and damage
		defaultDamageProcess(player, damage, pointsLose, killerPointsWin);
		defaulPlayerEndCheck(pointLimit);
	}

	@Override
	protected int getRespawnTime(GamePlayer player) {
		//increase respawnTime with every death
		return respawnTime++;
	}

	@Override
	public void onCollectingItem(GamePlayer player, GameItem item) {
	}

	@Override
	public void onEnteringTile(GamePlayer player, int tileID) {
	}

	@Override
	public void onLandingOnTile(GamePlayer player, int tileID) {
	}

	@Override
	public void onPlayerRespawn(GamePlayer player) {
	}

	@Override
	public void onFallingOutOfMap(GamePlayer player) {
		
		defaultKillPlayer(player, 1);
	}

	@Override
	public void onSuffocation(GamePlayer player) {
		defaultKillPlayer(player, 1);
	}

	@Override
	public Team[] initTeams(int players) {
		return defaultNoTeamsInit(players);
	}

}
