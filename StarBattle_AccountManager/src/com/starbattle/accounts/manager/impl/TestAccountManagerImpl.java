package com.starbattle.accounts.manager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.TestAccountManager;
import com.starbattle.accounts.player.FriendRelation;
import com.starbattle.accounts.player.FriendRelationState;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.player.PlayerFriends;
import com.starbattle.accounts.validation.RegisterState;

public class TestAccountManagerImpl implements TestAccountManager {

	private AccountManagerImpl accountManagerImpl;
	private ArrayList<PlayerAccount> accountList;
	
	// TODO Auto-generated method stub
	public TestAccountManagerImpl(AccountManagerImpl accountManagerImpl) {
		this.accountManagerImpl=accountManagerImpl;
		accountList = new ArrayList<>();
	}
	
	
	/**
	 * Reset DB, clear all tables
	 */
	@Override
	public void deleteDbValues() throws AccountException {
		for (PlayerAccount player : accountList) {
			accountManagerImpl.deleteAccount(player.getName());
		}
		
	}

	@Override
	public void addTestAccount(String accountName, String displayName, String password, String email) throws AccountException {
		PlayerAccount account = new PlayerAccount(accountName, displayName, password, email);
		if(accountManagerImpl.canRegisterAccount(account) == RegisterState.Register_Ok){
			accountManagerImpl.registerAccount(account);
		}
		
	}

	
	/**
	 * Add Friend Relation row with state 0 (Friend)
	 * (Or edit value if row is existing)
	 */
	@Override
	public boolean setFriends(String accountNameSender, String displayNameReceiver) throws AccountException {
		return accountManagerImpl.newFriendRequest(accountNameSender, displayNameReceiver);
	}

	/**
	 * Add Friend Relation row with state 1 (Request)
	 * (Or edit value if row is existing)
	 */
	@Override
	public void setFriendRequest(String accountNameSender, String displayNameReceiver) throws AccountException {
		accountManagerImpl.handleFriendRequest(accountNameSender, displayNameReceiver, true);
		
	}

	/**
	 * Return friend state value of the row
	 */
	@Override
	public FriendRelationState getFriendState(String accountNameSender, String displayNameReceiver) throws AccountException {
		List<FriendRelation> friendsList = accountManagerImpl.getFriendRelations(accountNameSender).getFriends();
		for (FriendRelation friendRelation : friendsList) {
			if((accountNameSender.equalsIgnoreCase(friendRelation.getAccountName()) && displayNameReceiver.equalsIgnoreCase(friendRelation.getDisplayName()) || (accountNameSender.equalsIgnoreCase(friendRelation.getDisplayName()) && displayNameReceiver.equalsIgnoreCase(friendRelation.getAccountName())))){
				return friendRelation.getRelationState();
			}
		}
		return null;
	}

}
