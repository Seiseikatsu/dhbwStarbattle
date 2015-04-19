package com.starbattle.ingame.render;

import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.GameCore;
import com.starbattle.ingame.resource.ResourceContainer;

public class GameRender {

	private DebugRender debugRender;
	private HudRender hudRender;

	public GameRender(ResourceContainer resourceContainer, GameCore gameCore) {
		debugRender = new DebugRender(resourceContainer);
		hudRender = new HudRender(resourceContainer);
	}

	public void renderGame(Graphics g) {
		debugRender.render(g);
		hudRender.renderHud(g);
	}
}
