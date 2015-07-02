package com.starbattle.gameserver.game.mode.impl;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.item.GameItem;
import com.starbattle.gameserver.game.mode.GameMode;
import com.starbattle.gameserver.player.GamePlayer;

public class DebugMode extends GameMode{

	public DebugMode() {
	}
	
	@Override
	public void onTakingDamage(GamePlayer player, Damage damage) {
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
		endGame();
	}

	@Override
	public void onSuffocation(GamePlayer player) {
		endGame();		
	}

	@Override
	public Team[] initTeams(int players) {
		return defaultNoTeamsInit(players);
	}

	@Override
	protected int getRespawnTime(GamePlayer player) {
		return 10;
	}

}
