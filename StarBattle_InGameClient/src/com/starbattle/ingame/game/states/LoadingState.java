package com.starbattle.ingame.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.starbattle.ingame.game.GameCore;
import com.starbattle.ingame.game.GameManager;
import com.starbattle.ingame.network.ObjectReceiveListener;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.ingame.resource.player.ResourceException;
import com.starbattle.network.connection.objects.game.NP_ClientReady;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class LoadingState extends BasicGameState {

	private ResourceContainer resourceContainer;
	private GameManager manager;
	private boolean finishedLoading = false;
	private boolean openGame = false;
	private NP_PrepareGame prepareGame;

	public LoadingState(GameManager manager, NP_PrepareGame prepareGame) {
		this.manager = manager;
		this.resourceContainer = manager.getResourceContainer();
		this.prepareGame = prepareGame;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);

		// listen for game start from server
		manager.getNetwork().setReceiveListener(new ObjectReceiveListener() {

			@Override
			public void updateGame(NP_GameUpdate message) {
				// not used here
			}

			@Override
			public void startGame() {
				openGame = true;
			}
		});
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// draw loading screen
		g.drawString("Loading...", 10, 30);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

		if (!finishedLoading) {
			System.out.println("Start Loading Game:");
			// load all resources
			try {
				System.out.println("Load Resources...");
				resourceContainer.loadResources();

				manager.initGame(prepareGame);

				// load map
				String mapName = manager.getMapName();
				GameCore gameCore = manager.getGameCore();
				System.out.println("Load Map...");
				gameCore.loadMap(mapName);

				System.out.println("Finished loading!");
				finishedLoading = true;

				// contact server im finished with loading
				manager.getNetwork().sendTCP(new NP_ClientReady());
				System.out.println("Waiting for other players to finish...");
			} catch (ResourceException e) {
				e.printStackTrace();
				container.exit();
			}

		} else {
			// wait for other players
			if (openGame) {
				// open game
				System.out.println("Starting Game!");
				manager.startingGame();
				game.enterState(GameStates.BATTLE_STATE.ordinal());
			}
		}

	}

	@Override
	public int getID() {
		return GameStates.LOADING_STATE.ordinal();
	}

}
