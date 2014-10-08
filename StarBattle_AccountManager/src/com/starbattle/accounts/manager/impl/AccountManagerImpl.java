package com.starbattle.accounts.manager.impl;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerImpl implements AccountManager {

	private DatabaseConnection databaseConnection;
	
	public AccountManagerImpl() {

	}

	public void registerAccount(PlayerAccount account) throws AccountException {

	}

	public void deleteAccount(String accountName) throws AccountException {

	}

	public LoginState canLogin(String name, String password) {

		return LoginState.Login_Ok;
	}

	public RegisterState canRegisterAccount(PlayerAccount account) {

		return RegisterState.Register_Ok;
	}

	public PlayerAccount readAccount(String accountName) throws AccountException {

		return null;
	}

	public void updateAccount(String accountName, AccountUpdate update) {

	}

}
