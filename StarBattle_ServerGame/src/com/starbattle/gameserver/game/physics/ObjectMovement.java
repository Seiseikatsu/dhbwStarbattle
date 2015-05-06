package com.starbattle.gameserver.game.physics;

public class ObjectMovement {

	private Location location;
	private ObjectGravity gravity;
	private boolean gravityActive=true;
	
	public ObjectMovement() {
		location=new Location();
		gravity=new ObjectGravity(this);
		
	}

	public void setGravityMode(boolean active)
	{
		gravityActive=active;
		if(gravityActive==false)
		{
			//stop gravity
			gravity.cancelMovement();
		}
	}
		
	public void update(float delta)
	{
		//update input movement
		
		//TODO: check ground collision for gravity starting
		if(gravityActive)
		{
			
		}
		
		//update gravity
	//	gravity.update(delta);
	}
	
	public void verticalMovement(float yDelta) 
	{
		//TODO: Check map collision
		if(yDelta>0)
		{
			//TODO: on falling down, stop gravity on ground hit
			
		}
		//update y location
		location.moveY(yDelta);
	}
	
	public void horizontalMovement(float xDelta)
	{
		//TODO: Check map collision
		
		//update x location
		location.moveX(xDelta);
	}
	
	public ObjectGravity getGravity() {
		return gravity;
	}
	
	public Location getLocation() {
		return location;
	}

	public void teleport(Location l) {
		this.location.moveTo(l);
	}
}
