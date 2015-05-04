package com.starbattle.accounts.manager.impl.control;

import java.util.List;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.validation.LoginState;

public class UtilDataManager extends DataController{

	public UtilDataManager(DatabaseControl databaseControl) {
		super(databaseControl);
	}

	public LoginState canLogin(String name, String password) throws AccountException {
		return null;
	}

	
	public List<Integer> getItemList(int playerId) throws AccountException {
		// TODO Auto-generated method stub
		return null;
	}

	public void tryResetPassword(String accountName, String email) throws AccountException {
		// TODO Auto-generated method stub

	}

	public String getDisplayName(String accountName) throws AccountException {
		return null;
	}

}
