package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.input.PlayerInput;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;

public class PlayerControl {

	private GamePlayer player;
	private PlayerInput input=new PlayerInput();
	
	public PlayerControl(GamePlayer player)
	{
		this.player=player;
	}
	
	public void control(NP_PlayerUpdate update)
	{
		input.process(update);
	}
	
	public void updatePlayer(float delta)
	{
		//update player 
		player.update(delta);
		//reset inputs
		input.updateReset(delta);
	}
}
