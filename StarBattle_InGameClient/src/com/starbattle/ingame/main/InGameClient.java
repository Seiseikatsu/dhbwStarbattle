package com.starbattle.ingame.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.game.GameManager;
import com.starbattle.ingame.game.GameStateContainer;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.network.GameNetwork;
import com.starbattle.ingame.network.GameSendConnection;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.ingame.settings.GameclientSettings;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class InGameClient implements GameClientConnection {

	public static boolean DEBUG_MODE = false;
	private AppGameContainer gameContainer;
	private GameStateContainer gameInit;
	private GameclientSettings settings;
	private GameNetwork network;
	private GameManager manager;

	public InGameClient() {

	}

	private RenderSettings doGameSettings() throws SlickException {
		// Set Window Resolution
		int screenWidht = settings.getWindowResolution().getScreenWidth();
		int screenHeight = settings.getWindowResolution().getScreenHeight();
		boolean fullscreen = settings.isFullscreenMode();
		gameContainer.setDisplayMode(screenWidht, screenHeight, fullscreen);
	
		// Sound Settings
		gameContainer.setMusicOn(!settings.isMusicOff());
		gameContainer.setSoundOn(!settings.isSoundOff());
		gameContainer.setMusicVolume(settings.getMusicVolume());
		gameContainer.setSoundVolume(settings.getSoundVolume());

		// Graphic Settings
		RenderSettings renderSettings = new RenderSettings(settings.getGraphicSettings());
		gameContainer.setTargetFrameRate(renderSettings.getTargetFPS());
		gameContainer.setVSync(renderSettings.isVSync());
		gameContainer.setSmoothDeltas(renderSettings.isSmoothDeltas());

		gameContainer.setShowFPS(DEBUG_MODE);
		return renderSettings;
	}

	@Override
	public void openInGameClient(GameclientSettings settings, NP_PrepareGame prepareGame, GameSendConnection send)
			throws GameClientException {

		// create game state container
		network = new GameNetwork(send);
		manager = new GameManager(network);
		gameInit = new GameStateContainer(manager, prepareGame);
		try {
			gameContainer = new AppGameContainer(gameInit);

			this.settings = settings;
			RenderSettings renderSettings=doGameSettings();
			manager.getGameCore().setRenderSettings(renderSettings);
			// start
			gameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
			throw new GameClientException("Failed to init Game");
		}
	}

	@Override
	public void receivedObject(Object object) {
		network.receiveObject(object);
	}

	@Override
	public void connectionLost() {
		// pause game
		gameContainer.pause();
	}

	@Override
	public void reconnected() {
		// resume game
		gameContainer.resume();
	}

	@Override
	public void closeInGameClient() {
		// kill game window
		if (gameContainer != null) {
			gameContainer.destroy();
		}
		if (manager != null) {
			manager.closeGame();
		}
	}

}
