package com.starbattle.ingame.game.bullets;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import com.starbattle.ingame.game.location.Location;
import com.starbattle.ingame.game.map.GameMap;
import com.starbattle.ingame.game.player.PlayerContainer;
import com.starbattle.ingame.game.player.PlayerObject;
import com.starbattle.maploader.main.CollisionMap;

public class BulletsContainer {

	// Number of Tiles added to the map size for the border
	public final static int BORDER_SIZE = 10;
	private List<BulletObject> bullets = new ArrayList<BulletObject>();
	private Rectangle border;
	private CollisionMap collisionMap;

	public BulletsContainer() {

	}

	public void spawnBullet(int id, Location location, BulletDesign design, float angle, float speed) {
		BulletObject bullet = new BulletObject(design, location, id);
		bullet.setMovement(angle, speed);
		bullets.add(bullet);
	}

	public void update(float delta) {
		for (int i = 0; i < bullets.size(); i++) {
			BulletObject bullet = bullets.get(i);
			bullet.update(delta);
			Location pos = bullet.getLocation();

			// remove bullets in walls
			int bx = (int) Math.floor(pos.getXpos());
			int by = (int) Math.floor(pos.getYpos());
			if (collisionMap.isTileBlocked(bx, by)) {
				bullets.remove(i);
				continue;
			}

			if (!border.contains(pos.getXpos(), pos.getYpos())) {
				// remove bullet because it is not more in visible area of map
				bullets.remove(i);
			}
		}
	}

	public List<BulletObject> getBullets() {
		return bullets;
	}

	public int getCount() {
		return bullets.size();
	}

	public void createBorder(GameMap map) {
		int w = map.getWidth() + BORDER_SIZE * 2;
		int h = map.getHeight() + BORDER_SIZE * 2;
		border = new Rectangle(-BORDER_SIZE, -BORDER_SIZE, w, h);
		this.collisionMap = map.getCollisionMap();
	}

	public void removeBullet(int id) {
		for (int i = 0; i < bullets.size(); i++) {
			BulletObject bullet = bullets.get(i);
			if (bullet.getBulletID() == id) {
				bullets.remove(i);
				return;
			}
		}
	}

}
