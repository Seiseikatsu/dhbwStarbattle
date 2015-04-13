package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.input.MovementControl;
import com.starbattle.gameserver.game.input.PlayerInput;

public abstract class PlayerUpdateProcessor {

	public static void processPlayerControl(GamePlayer player, PlayerInput input)
	{
		//check movement updates
		MovementControl movementControl = input.getMovementControl();
		
		
		//check action updates
		
	}
	
}
