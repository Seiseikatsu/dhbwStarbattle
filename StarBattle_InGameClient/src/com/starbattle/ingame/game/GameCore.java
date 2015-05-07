package com.starbattle.ingame.game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.starbattle.ingame.game.bullets.BulletsContainer;
import com.starbattle.ingame.game.input.PlayerInput;
import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.particles.ParticleContainer;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.GameRender;
import com.starbattle.ingame.render.HudRender;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameCore {

	private ParticleContainer particleContainer = new ParticleContainer();
	private BulletsContainer bulletsContainer = new BulletsContainer();
	private GameMap map = new GameMap();
	private ResourceContainer resourceContainer;
	private GameRender gameRender;
	private PlayerContainer players = new PlayerContainer();
	private Viewport viewport;
	private TriggerEffectsProcessor triggerEffectsProcessor;
	private PlayerInput playerInput;
	private HudRender hudRender;

	public GameCore(ResourceContainer resources) {
		this.resourceContainer = resources;
		hudRender = new HudRender(resources);
		gameRender = new GameRender(resources, this);
		triggerEffectsProcessor = new TriggerEffectsProcessor(this);
	}

	public void start() {
		viewport = new Viewport(Display.getWidth(), Display.getHeight());
	}

	public void loadMap(String mapName) {
		map.loadMap(mapName);
		//set bulletscontainer border to remove bullets
		bulletsContainer.createBorder(map);
	}

	public void initPlayers(NP_PrepareGame init) {
		players.init(init);
	}

	public void renderGame(Graphics g) {

		Image back = resourceContainer.getBackgroundGraphics().getSpaceBackground();
		back.rotate(0.02f);
		float x = back.getWidth() / 2;
		float y = back.getHeight() / 2;
		back.setCenterOfRotation(x, y);
		back.drawCentered(500, 450);

		map.renderBackground(viewport);
		gameRender.renderGame(g, viewport);
		map.renderForeground(viewport);

		hudRender.renderNames(g, players, viewport);
		hudRender.renderHud(g);

	}

	public void updateGame(int delta) {

		playerInput.poll();
		// focus viewport to my player

		PlayerObject player = players.getMyPlayer();
		viewport.view(player);

		// update my weapon angle
		player.updateWeaponAngle(playerInput.getMouseCursor());

		particleContainer.update(delta);

		// Location l=players.getMyPlayer().getLocation();
		// particleContainer.spawnEffect("Air",l);

		float floorDelta = ((float) delta + 1000) / 1000f;
		players.update(floorDelta);
		bulletsContainer.update(floorDelta);
	}

	public void receiveUpdate(NP_GameUpdate message) {

		players.update(message.playerData);
		triggerEffectsProcessor.processEffects(message.triggerEffect);
		// update viewport
		viewport.view(players.getMyPlayer());

	}

	public PlayerContainer getPlayers() {
		return players;
	}

	public ParticleContainer getParticleContainer() {
		return particleContainer;
	}

	public void setPlayerInput(PlayerInput playerInput) {
		this.playerInput = playerInput;
	}

	public BulletsContainer getBulletsContainer() {
		return bulletsContainer;
	}
	
	public GameMap getMap() {
		return map;
	}
	


}
