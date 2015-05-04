package com.starbattle.accounts.manager.impl;

import java.util.List;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.manager.impl.control.AccountCrud;
import com.starbattle.accounts.manager.impl.control.AccountDataManager;
import com.starbattle.accounts.manager.impl.control.FriendDataManager;
import com.starbattle.accounts.manager.impl.control.UtilDataManager;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.player.PlayerFriends;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerFacade implements AccountManager {

	private DatabaseControl databaseControl;
	private AccountDataManager accountDataManager;
	private FriendDataManager friendDataManager;
	private UtilDataManager utilDataManager;
	private AccountCrud accountCrud;

	public AccountManagerFacade() throws AccountException {
		databaseControl = new DatabaseControl();
		accountDataManager = new AccountDataManager(databaseControl);
		friendDataManager = new FriendDataManager(databaseControl);
		utilDataManager = new UtilDataManager(databaseControl);
		accountCrud = new AccountCrud(databaseControl);
	}

	@Override
	public void closeDB() {
		accountDataManager.close();
		databaseControl.close();
		friendDataManager.close();
		utilDataManager.close();
		accountCrud.close();
	}

	@Override
	public void registerAccount(PlayerAccount account) throws AccountException {

		if (accountDataManager.canRegisterAccount(account) == RegisterState.Register_Ok) {
			accountCrud.registerAccount(account);
		}
	}

	@Override
	public void deleteAccount(String accountName) throws AccountException {
		accountCrud.deleteAccount(accountName, 0);
	}

	@Override
	public RegisterState canRegisterAccount(PlayerAccount account) {

		return accountDataManager.canRegisterAccount(account);
	}

	@Override
	public PlayerAccount readAccount(String accountName) throws AccountException {
		return accountDataManager.readAccount(accountName);
	}

	@Override
	public void updateAccount(String accountName, AccountUpdate update) {
		try {
			accountCrud.updateAccount(accountName, update);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public LoginState canLogin(String name, String password) throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getItemList(int playerId) throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tryResetPassword(String accountName, String email) throws AccountException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDisplayName(String accountName) throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccountName(String displayName) throws AccountException {
		return accountDataManager.getAccountName(displayName);
	}

	@Override
	public PlayerFriends getFriendRelations(String accountName) throws AccountException {
		return friendDataManager.getFriendRelations(accountName);
	}

	@Override
	public boolean newFriendRequest(String accountName, String friendDisplayname) throws AccountException {
		return friendDataManager.newFriendRequest(accountName, friendDisplayname);
	}

	@Override
	public String handleFriendRequest(String accountName, String displayNameFriend, boolean accept)
			throws AccountException {
		return friendDataManager.handleFriendRequest(accountName, displayNameFriend, accept);
	}

}
