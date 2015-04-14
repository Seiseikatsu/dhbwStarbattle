package com.starbattle.accounts.manager;

public interface TestAccountManager {

	public void deleteDbValues() throws AccountException;
	
	public void addTestAccount(String accountName, String displayName, String password, String email) throws AccountException;
	
	public void setFriends(String accountNameSender, String displayNameReceiver) throws AccountException;

	public void setFriendRequest(String accountNameSender, String displayNameReceiver) throws AccountException;
	
	public int getFriendState(String accountNameSender, String displayNameReceiver) throws AccountException;
	
}
