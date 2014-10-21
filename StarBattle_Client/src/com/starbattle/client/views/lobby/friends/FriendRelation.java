package com.starbattle.client.views.lobby.friends;

public class FriendRelation {

	
	public final static int  RELATION_FRIENDS=0;
	public final static int  RELATION_REQUEST=1;
	public final static int  RELATION_PENDING=2;
	
	
	private String name;
	private int state;
	private boolean online;
	
	public FriendRelation(String name, int state, boolean online)
	{
		this.name=name;
		this.state=state;
		this.online=online;
	}
	
	
	public String getName() {
		return name;
	}
	
	public int getState() {
		return state;
	}
	
	public boolean isOnline() {
		return online;
	}
	
}
