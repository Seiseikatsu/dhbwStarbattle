package com.starbattle.ingame.game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.ingame.render.DebugRender;
import com.starbattle.ingame.render.GameRender;
import com.starbattle.ingame.resource.ResourceContainer;

public class GameCore {

	private ParticleContainer particleContainer = new ParticleContainer();
	private GameMap map = new GameMap();
	private Input input;
	private float mapx = 0;
	private float mapy = 0;
	private ResourceContainer resourceContainer;
	private GameRender gameRender;

	public GameCore(ResourceContainer resources) {
		this.resourceContainer = resources;
		gameRender = new GameRender(resources,this);
	}

	public void loadMap(String mapName) {
		map.loadMap(mapName);
	}

	public void renderGame(Graphics g) {

		resourceContainer.getBackgroundGraphics().getSpaceBackground().draw();
		map.renderBackground((int) mapx, (int) mapy);
		gameRender.renderGame(g);
		map.renderForeground((int) mapx, (int) mapy);
		particleContainer.render(g);

	}

	private float angle, radius = 300;

	public void updateGame(int delta) {
		particleContainer.update(delta);

	}

	public void setInput(Input input) {
		this.input = input;

	}

}
