package com.starbattle.ingame.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.states.BattleState;
import com.starbattle.ingame.game.states.LoadingState;
import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameStateContainer extends StateBasedGame {

	private GameManager manager;

	public GameStateContainer(GameNetwork network, NP_PrepareGame prepareGame) {
		super("Starbattle Client");
		manager = new GameManager(network);
		manager.initGame(prepareGame);
	}

	public void setRenderSettings(RenderSettings settings) {

	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new LoadingState(manager));
		addState(new BattleState(manager));
	}

}
