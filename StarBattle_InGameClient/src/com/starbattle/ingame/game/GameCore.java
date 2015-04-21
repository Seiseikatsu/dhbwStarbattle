package com.starbattle.ingame.game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.GameRender;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameCore {

	private ParticleContainer particleContainer = new ParticleContainer();
	private GameMap map = new GameMap();
	private Input input;
	private ResourceContainer resourceContainer;
	private GameRender gameRender;
	private Viewport viewport;
	
	public GameCore(ResourceContainer resources) {
		viewport=new Viewport(Display.getWidth(), Display.getHeight());
		this.resourceContainer = resources;
		gameRender = new GameRender(resources, this);
	}

	public void loadMap(String mapName) {
		map.loadMap(mapName);
	}

	public void renderGame(Graphics g) {

		viewport.scoll(0.3f,0.3f);
		resourceContainer.getBackgroundGraphics().getSpaceBackground().draw();
		map.renderBackground(viewport);
		gameRender.renderGame(g);
		map.renderForeground(viewport);
		particleContainer.render(g);
	}


	public void updateGame(int delta) {
		particleContainer.update(delta);

	}

	public void setInput(Input input) {
		this.input = input;

	}


}
