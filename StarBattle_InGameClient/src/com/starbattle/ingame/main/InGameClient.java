package com.starbattle.ingame.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.starbattle.ingame.game.GameStateContainer;
import com.starbattle.ingame.network.GameClientConnection;
import com.starbattle.ingame.network.NetworkObjectResolver;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.ingame.settings.GameclientSettings;

public class InGameClient implements GameClientConnection {

	public static boolean DEBUG_MODE = false;
	private AppGameContainer gameContainer;
	private GameStateContainer gameInit;
	private GameclientSettings settings;
	private NetworkObjectResolver objectResolver;

	public InGameClient() {
		this.settings = settings;
		objectResolver = new NetworkObjectResolver();
	}

	private void doGameSettings() throws SlickException {
		// Set Window Resolution
		int screenWidht = settings.getWindowResolution().getScreenWidth();
		int screenHeight = settings.getWindowResolution().getScreenHeight();
		boolean fullscreen = settings.isFullscreenMode();
		gameContainer.setDisplayMode(screenWidht, screenHeight, fullscreen);
		gameContainer.setShowFPS(false);

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

		if (DEBUG_MODE) {
			gameContainer.setShowFPS(true);
		}
	}

	@Override
	public void openInGameClient(GameclientSettings settings) throws GameClientException {

		// create game state container
		gameInit = new GameStateContainer();
		try {
			gameContainer = new AppGameContainer(gameInit);
			this.settings = settings;
			doGameSettings();
			// start
			gameContainer.start();
		} catch (SlickException e) {
			e.printStackTrace();
			throw new GameClientException("Failed to init Game");
		}
	}

	@Override
	public void receivedObject(Object object) {
		objectResolver.resolveMessage(object);
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
	}

}
