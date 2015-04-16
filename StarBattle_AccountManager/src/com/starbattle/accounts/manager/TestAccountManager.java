package com.starbattle.accounts.manager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface TestAccountManager {

	public void deleteDbValues() throws AccountException, SQLException;
	
	public void addTestAccount(String accountName, String displayName, String password, String email) throws AccountException;
	
	public boolean setFriends(String accountNameSender, String displayNameReceiver) throws AccountException;

	public void setFriendRequest(String accountNameSender, String displayNameReceiver) throws AccountException;
	
	public int getFriendState(String accountNameSender, String displayNameReceiver) throws AccountException;
	
}
