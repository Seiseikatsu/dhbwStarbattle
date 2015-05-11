package com.starbattle.gameserver.game.mode;

import com.starbattle.gameserver.game.GameContainer;
import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;
import com.starbattle.gameserver.game.item.GameItem;
import com.starbattle.gameserver.player.GamePlayer;

public interface GameModeInterface {

	
	public void onGameInit(GameContainer game);
	
	public void onTakingDamage(GamePlayer player, Damage damage);
	
	public void onCollectingItem(GamePlayer player, GameItem item);
	
	public void onEnteringTile(GamePlayer player, int tileID);
	
	public void onLandingOnTile(GamePlayer player, int tileID);
	
	public void onPlayerRespawn(GamePlayer player);
	
	public void onFallingOutOfMap(GamePlayer player);
	
	public void onSuffocation(GamePlayer player);
	
	public Team[] initTeams(int players);
}
