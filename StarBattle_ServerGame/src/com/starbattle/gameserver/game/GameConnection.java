package com.starbattle.gameserver.game;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.gameserver.player.GamePlayer;
import com.starbattle.gameserver.player.container.PlayerList;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PlayerUpdate;
import com.starbattle.network.connection.objects.game.NP_TriggerEffect;

public class GameConnection {

	private GameContainer game;
	private List<NP_TriggerEffect> effects = new ArrayList<NP_TriggerEffect>();

	public GameConnection(GameContainer game) {
		this.game = game;
	}

	public void playerConnected(String accountName) {

	}

	public void playerDisonnected(String accountName) {

	}

	public EffectTrigger createEffectTrigger() {
		return new EffectTrigger() {

			@Override
			public void triggerEffect(NP_TriggerEffect effect) {
				// add to trigger list
				effects.add(effect);
			}
		};
	}

	public void receivedPlayerUpdate(NP_PlayerUpdate data, String accountName) {

		if (accountName == null) {
			System.err.println("Wrong Player Update!");
			return;
		}

		PlayerList players = game.getPlayerList();
		GamePlayer player = players.getPlayerByAccount(accountName);
		player.processInput(data);
	}

	public NP_GameUpdate getGameUpdate() {
		NP_GameUpdate update = new NP_GameUpdate();
		PlayerList players = game.getPlayerList();

		update.playerData = players.generatePlayerData();

		// store current effects in update
		update.triggerEffect = effects.toArray(new NP_TriggerEffect[0]);

		// clear old trigger effects
		effects.clear();

		return update;
	}

}
