package com.starbattle.accounts.manager;

import com.starbattle.accounts.player.FriendRelationState;

public interface TestAccountManager {

	public void deleteDbValues() throws AccountException;
	
	public void addTestAccount(String accountName, String displayName, String password, String email) throws AccountException;
	
	public boolean setFriends(String accountNameSender, String displayNameReceiver) throws AccountException;

	public void setFriendRequest(String accountNameSender, String displayNameReceiver) throws AccountException;
	
	public FriendRelationState getFriendState(String accountNameSender, String displayNameReceiver) throws AccountException;
	
}
