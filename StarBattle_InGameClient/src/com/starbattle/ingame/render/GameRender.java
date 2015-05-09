package com.starbattle.ingame.render;

import java.util.List;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.GameCore;
import com.starbattle.ingame.game.bullets.BulletObject;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.resource.ResourceContainer;

public class GameRender {

	private DebugRender debugRender;
	private PlayerRender playerRender;
	private GameCore game;
	private BulletRender bulletRender;
	private RenderSettings renderSettings;

	public GameRender(ResourceContainer resourceContainer, GameCore gameCore) {
		debugRender = new DebugRender(resourceContainer);
		playerRender = new PlayerRender(resourceContainer);
		bulletRender = new BulletRender(resourceContainer);
		this.game = gameCore;
	}

	public void renderGame(Graphics g, Viewport viewport) {

		PlayerContainer players = game.getPlayers();
		// render players
		for (int i = 0; i < players.getNumberOfPlayers(); i++) {
			PlayerObject player = players.getPlayer(i);
			playerRender.render(g, player, viewport);

		}

		game.getParticleContainer().render(g, viewport);

		// render bullets
		List<BulletObject> bullets = game.getBulletsContainer().getBullets();
		for (int i = 0; i < bullets.size(); i++) {
			bulletRender.renderBullet(g, bullets.get(i), viewport);
		}
		// debugRender.render(g);
	
	}

	public void setRenderSettings(RenderSettings renderSettings) {
		this.renderSettings=renderSettings;
	}
}
