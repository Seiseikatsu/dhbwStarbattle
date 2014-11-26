package com.starbattle.accounts.player;

public class FriendRelation {

	
	private String accountName;
	private String displayName;
	private FriendRelationState relationState;
	
	public FriendRelation(String accountName,String displayName, FriendRelationState relationState) {
		this.accountName=accountName;
		this.displayName=displayName;
		this.relationState=relationState;
	}
	
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getAccountName() {
		return accountName;
	}
	
	public FriendRelationState getRelationState() {
		return relationState;
	}
}
