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
import com.starbattle.ingame.game.viewport.PlayerWatcher;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.GameRender;
import com.starbattle.ingame.render.HudRender;
import com.starbattle.ingame.render.RenderResource;
import com.starbattle.ingame.render.RenderSettings;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.network.connection.objects.game.NP_GameUpdate;
import com.starbattle.network.connection.objects.game.NP_PrepareGame;

public class GameCore {

	private ParticleContainer particleContainer;
	private BulletsContainer bulletsContainer;
	private GameMap map = new GameMap();
	private ResourceContainer resourceContainer;
	private GameRender gameRender;
	private PlayerContainer players = new PlayerContainer();
	private Viewport viewport;
	private TriggerEffectsProcessor triggerEffectsProcessor;
	private PlayerInput playerInput;
	private HudRender hudRender;
	private PlayerWatcher playerWatcher;
	
	
	public GameCore(ResourceContainer resources) {
		this.resourceContainer = resources;
		particleContainer=new ParticleContainer(resourceContainer.getGarbageCollector());
		playerWatcher=new PlayerWatcher(players);
		hudRender = new HudRender(resources);
		gameRender = new GameRender(resources, this);
		triggerEffectsProcessor = new TriggerEffectsProcessor(this);
		bulletsContainer= new BulletsContainer(triggerEffectsProcessor);
	}
	
	public void setRenderSettings(RenderSettings renderSettings)
	{
		gameRender.setRenderSettings(renderSettings);
		particleContainer.setRenderSettings(renderSettings);
	}

	public void start() {
		viewport = new Viewport(Display.getWidth(), Display.getHeight());
	}

	public void loadMap(String mapName) {
		map.loadMap(mapName,resourceContainer.getGarbageCollector());
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
		hudRender.renderHud(g,players.getMyPlayer());

	}

	public void updateGame(int delta) {

		playerInput.poll();
		// focus viewport to my player

		PlayerObject player = players.getMyPlayer();
		if(playerWatcher.isWatchingPlayer())
		{
			Location location=playerWatcher.getWatchingLocation();
			//create lost soul effect
			particleContainer.spawnEffect("WallHit", location);
			viewport.view(location);
		}
		else
		{
			viewport.view(player);	

		}

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

		// update viewport
		triggerEffectsProcessor.processEffects(message.triggerEffect);

	}

	public TriggerEffectsProcessor getTriggerEffectsProcessor() {
		return triggerEffectsProcessor;
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
	
	public ResourceContainer getResourceContainer() {
		return resourceContainer;
	}
	
	public PlayerWatcher getPlayerWatcher() {
		return playerWatcher;
	}

}
