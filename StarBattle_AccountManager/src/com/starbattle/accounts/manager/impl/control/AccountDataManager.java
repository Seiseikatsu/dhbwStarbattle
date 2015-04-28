package com.starbattle.accounts.manager.impl.control;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.validation.RegisterState;

public class AccountDataManager extends DataController {

	private AccountCrud accountCrud;
	
	public AccountDataManager(DatabaseControl databaseControl) {
		super(databaseControl);
		accountCrud=new AccountCrud(databaseControl);
	}


	public PlayerAccount readAccount(String accountName) throws AccountException {
		return null;
	}

	public void updateAccount(String accountName, AccountUpdate update) throws AccountException {

	}

	public String getAccountName(String displayName) throws AccountException {
		return null;
	}
	
	public AccountCrud getAccountCrud() {
		return accountCrud;
	}
}
