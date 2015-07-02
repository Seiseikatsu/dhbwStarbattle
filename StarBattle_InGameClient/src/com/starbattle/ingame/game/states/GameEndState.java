package com.starbattle.ingame.game.states;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.GameStateContainer;
import com.starbattle.ingame.main.InGameClient;

public class GameEndState extends BasicGameState {

	private GameStateContainer gameStateContainer;

	public GameEndState(GameStateContainer gameStateContainer) {
		this.gameStateContainer=gameStateContainer;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
	
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		
	}

		@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		//end game on next updates
			InGameClient.GAME_ENDED=true;
		try {
			gameStateContainer.getManager().partialClose();
			container.exit();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}

	@Override
	public int getID() {
		return GameStates.END_STATE.ordinal();
	}

}
