package com.starbattle.ingame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.game.viewport.Viewport;
import com.starbattle.ingame.render.hud.AirBarRender;
import com.starbattle.ingame.resource.ResourceContainer;
import com.starbattle.ingame.resource.WeaponGraphics;

public class HudRender {

	private ResourceContainer resourceContainer;
	private AirBarRender airBarRender;
	private KillscreenRender killscreenRender;

	public HudRender(ResourceContainer resourceContainer) {
		this.resourceContainer = resourceContainer;
		airBarRender = new AirBarRender(resourceContainer);
		killscreenRender = new KillscreenRender(resourceContainer);
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

	public void renderHud(Graphics g, PlayerObject player) {
		float x = 485;
		float y = 8;
		if (player.getHealth() <= 0) {

			killscreenRender.renderKillScreen(g, player.getRespawnTime());
		}

		Font font = resourceContainer.getFonts().getTinyText();
		g.setFont(font);
		
		airBarRender.render(g, x, y, player.getHealth());
		int points = player.getPoints().getPoints();
		g.setColor(new Color(255, 255, 255));
		String text = "" + points;
		g.drawString(text, x + 460 - font.getWidth(text), y + 41);
		
		int ammo=player.getAmmo();
		text = "" + ammo;
		g.drawString(text, x + 325 - font.getWidth(text), y + 41);
		
		int wid=player.getWeaponID();
		WeaponGraphics weaponGraphic=WeaponGraphics.values()[wid];
		Image weapon=resourceContainer.getWeaponGraphics(weaponGraphic).getSprite(0, 0);
		int wh=weapon.getHeight();
		weapon.draw(x+220, y+50-wh/2);
		
		int nr=player.getPoints().getPlace();
		if(nr!=-1)
		{
		Image medal=resourceContainer.getHudGraphics().getMedal(nr);
		medal.draw(x+360, y+36);
		}
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
