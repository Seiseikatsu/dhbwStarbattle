package com.starbattle.accounts.player;

public class FriendRelation {

	
	private String accountName;
	private FriendRelationState relationState;
	
	public FriendRelation(String accountName, FriendRelationState relationState) {
		this.accountName=accountName;
		this.relationState=relationState;
	}
	
	
	public String getAccountName() {
		return accountName;
	}
	
	public FriendRelationState getRelationState() {
		return relationState;
	}
}
