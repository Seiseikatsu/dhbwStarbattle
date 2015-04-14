package com.starbattle.accounts.manager.impl;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.TestAccountManager;

public class TestAccountManagerImpl implements TestAccountManager {

	private AccountManagerImpl accountManagerImpl;
	
	public TestAccountManagerImpl(AccountManagerImpl accountManagerImpl) {
		this.accountManagerImpl=accountManagerImpl;
	}
	
	
	/**
	 * Reset DB, clear all tables
	 */
	@Override
	public void deleteDbValues() throws AccountException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTestAccount(String accountName, String displayName, String password, String email)
			throws AccountException {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * Add Friend Relation row with state 0 (Friend)
	 * (Or edit value if row is existing)
	 */
	@Override
	public void setFriends(String accountNameSender, String displayNameReceiver) throws AccountException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Add Friend Relation row with state 1 (Request)
	 * (Or edit value if row is existing)
	 */
	@Override
	public void setFriendRequest(String accountNameSender, String displayNameReceiver) throws AccountException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Return friend state value of the row
	 */
	@Override
	public int getFriendState(String accountNameSender, String displayNameReceiver) throws AccountException {
		// TODO Auto-generated method stub
		return 0;
	}

}
