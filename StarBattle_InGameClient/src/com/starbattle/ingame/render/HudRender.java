package com.starbattle.ingame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.hud.AirBarRender;
import com.starbattle.ingame.resource.ResourceContainer;

public class HudRender {

	private ResourceContainer resourceContainer;
	private AirBarRender airBarRender;

	public HudRender(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
		airBarRender = new AirBarRender(resourceContainer);
	}

	public void renderNames(Graphics g, PlayerContainer players, Viewport viewport) {
		for (int i = 0; i < players.getNumberOfPlayers(); i++) {

			PlayerObject player = players.getPlayer(i);
			if (player.getDisplay().isVisible()) {
				Location location = viewport.getScreenLocation(player.getLocation());
				float x = location.getXpos();
				float y = location.getYpos() - 70;
				drawNameString(g, player.getName(), x, y);
			}
		}
	}

	float air = 1f;

	public void renderHud(Graphics g) {
		float x = 485;
		float y = 8;

		air -= 0.001;
		airBarRender.render(g, x, y, air);
	}

	private void drawNameString(Graphics g, String name, float x, float y) {
		Font font = g.getFont();
		int w = font.getWidth(name);
		int h = font.getHeight(name);

		float xp = x - w / 2;
		float yp = y - h / 2;
		g.setColor(new Color(255, 255, 255));
		g.drawString(name, xp, yp);
	}
}
