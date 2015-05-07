package com.starbattle.ingame.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.starbattle.ingame.game.GameManager;
import com.starbattle.ingame.game.input.ActionSet;
import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.ingame.main.InGameClient;
import com.starbattle.ingame.network.GameNetwork;

public class InfoRender {

	private GameManager game;
	private int y;

	public InfoRender(GameManager game) {
		this.game = game;
	}

	public void render(Graphics g) {
		if (InGameClient.DEBUG_MODE) {
			g.setColor(new Color(1f, 1f, 1f));
			y = 30;
			drawDebug(g, "Ping", GameNetwork.getPing());
			drawDebug(g, "PEC", game.getGameCore().getParticleContainer().getCount());
			drawDebug(g, "BUC", game.getGameCore().getBulletsContainer().getCount());

			GameMap map = game.getGameCore().getMap();

			drawDebug(g, "Mapsize", map.getWidth() + " x " + map.getHeight());

			PlayerObject player = game.getGameCore().getPlayers().getMyPlayer();

			drawDebug(g, ":___ Local Player ___", "");
			drawDebug(g, "Name", player.getName());

			drawDebug(g, "X", player.getLocation().getXpos());
			drawDebug(g, "Y", player.getLocation().getYpos());
			drawDebug(g, "Angle", (float) Math.toDegrees(player.getWeaponAngle()));
			boolean left = player.getDisplay().isLookingLeft();
			String s = "right";
			if (left) {
				s = "left";
			}
			drawDebug(g, "Orientation", s);

			ActionSet action = game.getPlayerInput().getActionSet();
			drawDebug(g, "Move Left", action.isMove_left());
			drawDebug(g, "Move Right", action.isMove_right());
			drawDebug(g, "Move Up", action.isMove_up());
			drawDebug(g, "Move Down", action.isMove_down());

			drawDebug(g, ":___ Playerlist ___", "");
			int nr = game.getGameCore().getPlayers().getNumberOfPlayers();
			for (int i = 0; i < nr; i++) {
				player = game.getGameCore().getPlayers().getPlayer(i);
				Location l = player.getLocation();

				drawDebug(g, i + "", player.getName() + " / Team: " + player.getTeam() + " @ " + l.getXpos() + " x "
						+ l.getYpos());

			}
		}
	}

	private void drawDebug(Graphics g, String text, Object value) {
		g.drawString(text + ": " + value.toString(), 10, y);
		y += 20;
	}

}
