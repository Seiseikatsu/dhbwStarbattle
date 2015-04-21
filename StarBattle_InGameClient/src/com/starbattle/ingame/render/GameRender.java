package com.starbattle.ingame.render;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.GameCore;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.resource.ResourceContainer;

public class GameRender {

	private DebugRender debugRender;
	private PlayerRender playerRender;
	private HudRender hudRender;
	private GameCore game;

	public GameRender(ResourceContainer resourceContainer, GameCore gameCore) {
		debugRender = new DebugRender(resourceContainer);
		hudRender = new HudRender(resourceContainer);
		playerRender = new PlayerRender(resourceContainer);
		this.game = gameCore;
	}

	public void renderGame(Graphics g, Viewport viewport) {

		PlayerContainer players = game.getPlayers();
		// render players
		for (int i = 0; i < players.getNumberOfPlayers(); i++) {
			PlayerObject player = players.getPlayer(i);
			playerRender.render(g, player, viewport);
			
		}

		// debugRender.render(g);
		hudRender.renderHud(g);
	}
}
