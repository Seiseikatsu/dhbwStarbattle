package com.starbattle.ingame.game.bullets;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.ingame.game.location.Location;

public class BulletsContainer {

	private List<BulletObject> bullets=new ArrayList<BulletObject>();
	
	public BulletsContainer() {

	}
	
	public void spawnBullet(Location location, BulletDesign design, float angle, float speed)
	{
		BulletObject bullet=new BulletObject(design, location);
		bullet.setMovement(angle, speed);
		bullets.add(bullet);
	}
	
	public void update(float delta)
	{
		for(int i=0; i<bullets.size(); i++)
		{
			bullets.get(i).update(delta);
		}
	}
	
	public List<BulletObject> getBullets() {
		return bullets;
	}
	
}
