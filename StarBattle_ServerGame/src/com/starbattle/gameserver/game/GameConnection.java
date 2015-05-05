package com.starbattle.gameserver.game;

import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class GameConnection  {

	private GameContainer game;
	
	public  GameConnection(GameContainer game) {
		this.game=game;
	}
	

	public void playerConnected(String accountName) {
		
	}


	public void playerDisonnected(String accountName) {
		
	}

	
	public void receivedPlayerUpdate(NP_PlayerUpdate data, String accountName) {
		
		if(accountName==null)
		{
			System.err.println("Wrong Player Update!");
			return;
		}
		
		PlayerList players = game.getPlayerList();
		GamePlayer player = players.getPlayerByAccount(accountName);
		player.processInput(data);
	}
	
	public NP_GameUpdate getGameUpdate()
	{
		NP_GameUpdate update=new NP_GameUpdate();
		PlayerList players = game.getPlayerList();
		
		update.playerData=players.generatePlayerData();
		return update;
	}

}
