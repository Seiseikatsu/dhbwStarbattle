package com.starbattle.gameserver.game.physics;

public class ObjectGravity {

	
	private final static float G_EARTH = 9.81f;
	private final static float TIME_UNITS= 0.03f;
	
	private float g_factor;
	private float time;
	private float jumpSpeed;
	private float lastValue;
	private boolean inAir=false;
	private ObjectMovement objectMovement;
	
	public ObjectGravity(ObjectMovement objectMovement) {
		g_factor=G_EARTH;
		this.objectMovement=objectMovement;
	}
	
	public void setG_factor(float g_factor) {
		this.g_factor = g_factor;
	}
	
	public void update(float delta)
	{
		if(inAir)
		{
		//update time
		float deltaTime=TIME_UNITS*delta;
		time+=deltaTime;
		//update y  (vertikaler wurf formel)
	    float jumpY = (float)(jumpSpeed * time - (g_factor / 2) * Math.pow(time, 2));
	    float yDelta=jumpY-lastValue;
	    lastValue=jumpY;
	    //update location
	    objectMovement.verticalMovement(yDelta);
		}
	}
	
	public void jump(float speed)
	{
		if(!inAir)
		{
		jumpSpeed=speed;
		startGravity();
		}
	}
	
	public void startFalling()
	{
		if(!inAir)
		{
		jumpSpeed=0;
		startGravity();
		}
	}
	
	public void cancelMovement()
	{
		inAir=false;
	}
	
	private void startGravity()
	{
		inAir=true;
		time=0;
		lastValue=0;
	}
	
	public boolean isInAir() {
		return inAir;
	}
}
