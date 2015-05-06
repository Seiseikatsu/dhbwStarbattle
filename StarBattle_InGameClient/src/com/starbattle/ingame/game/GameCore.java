package com.starbattle.ingame.game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.GameRender;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
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

		//TODO: remove debug  background here
		Image back=	resourceContainer.getBackgroundGraphics().getSpaceBackground();
		back.rotate(0.08f);
		
		float x=back.getWidth()/2;
		float y=back.getHeight()/2;
		back.setCenterOfRotation(x, y);
		back.drawCentered(500, 450);
		
		map.renderBackground(viewport);
		gameRender.renderGame(g,viewport);
		map.renderForeground(viewport);
	
	}

	public void updateGame(int delta) {

		// focus viewport to my player
		viewport.view(players.getMyPlayer());

		particleContainer.update(delta);
		
		Location l=players.getMyPlayer().getLocation();
		particleContainer.spawnEffect("Air",l);
		
		// calc float delta
		players.update(delta);
	}
	
	public void receiveUpdate(NP_GameUpdate message) {
		
		players.update(message.playerData);
		//update viewport
		viewport.view(players.getMyPlayer());

	}

	
	public PlayerContainer getPlayers() {
		return players;
	}
	
	public ParticleContainer getParticleContainer() {
		return particleContainer;
	}


}
