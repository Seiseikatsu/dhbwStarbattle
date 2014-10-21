package com.starbattle.accounts.player;

public enum FriendRelationState {

	Friends, Pending, Request;   

	/**
	 * This is the relation from the view of the player.
	 * 
	 * Friends = your friends
	 * Pending = this relation is a friend request sent by you 
	 * Request = this relation is a friend request from another player you have to decline or accept
	 * 
	 */
	
	FriendRelationState() {

	}

}
