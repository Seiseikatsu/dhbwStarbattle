package com.starbattle.gameserver.map.collision;

import org.newdawn.slick.geom.Shape;

import com.starbattle.gameserver.game.physics.Location;

public class CollisionDetection {

	private CollisionMap collisionMap;
	
	public CollisionDetection(CollisionMap collisionMap) {
		this.collisionMap=collisionMap;
	}
	
	
	/**
	 * 
	 * 
	 * @param oldLocation
	 * @param newLocation
	 * @param objectShape
	 * @return  null if no collision detected, else the new location to set
	 */
	private Location processCollisions(Location oldLocation, Location newLocation, Shape objectShape)
	{
		
		
		return null;
	}
	//TODO
//	private List<Shape> getSuroundingMapTiles(Location );
}
