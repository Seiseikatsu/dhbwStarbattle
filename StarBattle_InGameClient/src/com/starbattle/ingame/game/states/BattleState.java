package com.starbattle.ingame.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.GameManager;
import com.starbattle.ingame.network.ObjectReceiveListener;
import com.starbattle.network.connection.objects.game.NP_ClientReady;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;

public class BattleState extends BasicGameState {

	private GameManager manager;

	public BattleState(GameManager manager) {
		this.manager = manager;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		manager.getNetwork().setReceiveListener(new ObjectReceiveListener() {
			
			@Override
			public void updateGame(NP_GameUpdate message) {
				//TODO do game updates from server
			}
			
			@Override
			public void startGame() {
				
			}
		});
		manager.getGameCore().start();
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		manager.getGameCore().renderGame(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {

		//update game
		manager.getGameCore().updateGame(delta);
	}

	@Override
	public int getID() {
		return GameStates.BATTLE_STATE.ordinal();
	}

}
