package com.starbattle.gameserver.game.mode.impl;

import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.item.GameItem;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.player.GamePlayer;

public class StandardMode extends GameMode{

	private int pointLimit;
	private int respawnTime=5;
	
	public StandardMode(GameContainer game, int pointLimit) {
		super(game);	
		this.pointLimit=pointLimit;
	}
	
	@Override
	public void onGameInit() {
		
	}

	@Override
	public void onTakingDamage(GamePlayer player, Damage damage) {
		int pointsLose=1;
		int killerPointsWin=2;
		//default respawn and damage
		defaultDamageProcess(player, damage, pointsLose, killerPointsWin);
		//default check for team with enough points to win
		defaulTeamEndCheck(pointLimit);
	}
	
	@Override
	protected int getRespawnTime(GamePlayer player) {
		return respawnTime;
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

	

}
