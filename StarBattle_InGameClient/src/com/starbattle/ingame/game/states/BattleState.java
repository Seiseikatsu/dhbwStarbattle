package com.starbattle.ingame.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.GameManager;
import com.starbattle.ingame.network.ObjectReceiveListener;
import com.starbattle.ingame.render.InfoRender;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;

public class BattleState extends BasicGameState {

	private GameManager manager;
	private InfoRender infoRender;
	private NP_GameUpdate update;

	public BattleState(GameManager manager) {
		this.manager = manager;
		infoRender = new InfoRender(manager);
	}

	@Override
	public void enter(GameContainer container,final StateBasedGame game) throws SlickException {

		super.enter(container, game);
		// set mouse cursor
		Image cursor = manager.getResourceContainer().getHudGraphics().getCursor();
		container.setMouseCursor(cursor, 12, 12);
		manager.getNetwork().setReceiveListener(new ObjectReceiveListener() {

			@Override
			public void updateGame(NP_GameUpdate message) {
				// store update
				update = message;
			}

			@Override
			public void startGame() {
				//no function, game already running
			}

			@Override
			public void endGame() {
				//end game
				game.enterState(GameStates.END_STATE.ordinal());
			}
		});
		manager.getGameCore().start();
	}

	private void doGameUpdates() {
		if (update != null) {
			manager.getGameCore().receiveUpdate(update);
			update = null;
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		manager.getGameCore().renderGame(g);
		infoRender.render(g);

		// do game updates after render to prevent changing of locations while
		// rendering
		doGameUpdates();

	}

	@Override
	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {

		// update game
		manager.getGameCore().updateGame(delta);
	}

	@Override
	public int getID() {
		return GameStates.BATTLE_STATE.ordinal();
	}

}
