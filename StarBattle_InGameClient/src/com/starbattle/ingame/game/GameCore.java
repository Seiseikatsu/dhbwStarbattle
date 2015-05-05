package com.starbattle.ingame.game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.GameRender;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameCore {

	private ParticleContainer particleContainer = new ParticleContainer();
	private GameMap map = new GameMap();
	private ResourceContainer resourceContainer;
	private GameRender gameRender;
	private PlayerContainer players = new PlayerContainer();
	private Viewport viewport;

	public GameCore(ResourceContainer resources) {
		this.resourceContainer = resources;
		gameRender = new GameRender(resources, this);
	}

	public void start()
	{
		viewport = new Viewport(Display.getWidth(), Display.getHeight());		
	}
	
	public void loadMap(String mapName) {
		map.loadMap(mapName);
	}

	public void initPlayers(NP_PrepareGame init) {
		players.init(init);
	}

	public void renderGame(Graphics g) {

		resourceContainer.getBackgroundGraphics().getSpaceBackground().draw();
		map.renderBackground(viewport);
		gameRender.renderGame(g,viewport);
		map.renderForeground(viewport);
		particleContainer.render(g);
	}

	public void updateGame(int delta) {

		// focus viewport to my player
		viewport.view(players.getMyPlayer());

		particleContainer.update(delta);
		// calc float delta
		players.update(delta);
	}
	
	public PlayerContainer getPlayers() {
		return players;
	}

}
