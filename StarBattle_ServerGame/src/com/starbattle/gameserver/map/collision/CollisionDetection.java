package com.starbattle.gameserver.map.collision;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.game.physics.Location;

public class CollisionDetection {

	private CollisionMap collisionMap;
	
	public CollisionDetection(CollisionMap collisionMap) {
		this.collisionMap=collisionMap;
	}
	
	

	public boolean canStand(Location location, float playerHeight)
	{
		float x=location.getXpos();
		float y=location.getYpos()+playerHeight;
			
		return isBlocked(x, y);
	}
	
	public boolean cantMoveUp(Location location, float playerHeight)
	{
		float x=location.getXpos();
		float y=location.getYpos()-playerHeight;
			
		return isBlocked(x, y);
	}
	
	public boolean canLand(Location oldLocation,Location newLocation, float playerHeight)
	{		
		return !canStand(oldLocation, playerHeight)&&canStand(newLocation, playerHeight);
	}
	
	public boolean cantMove(Location oldLocation, Location newLocation, float playerWidth, float playerHeight)
	{
		float x1=oldLocation.getXpos();
		float x2=newLocation.getXpos();
		float y2=newLocation.getYpos();
		
		float xDiff=x2-x1;
		
		if(xDiff>0)
		{
			//right movement
			x2+=playerWidth;
			
		}else
		{
			//left movement
			x2-=playerWidth;
		}
		
		return isBlocked(x2, y2-playerHeight)||isBlocked(x2, y2+playerHeight);
	}
	
	private boolean isBlocked(float x, float y)
	{
		int tileX=(int) Math.floor(x);
		int tileY=(int) Math.floor(y);	
		boolean blocked= collisionMap.isTileBlocked(tileX,tileY);		
		return blocked;
	}
}
