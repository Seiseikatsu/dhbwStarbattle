package com.starbattle.accounts.manager;

import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public interface AccountManager {

	public void registerAccount(PlayerAccount account) throws AccountException;

	public void deleteAccount(String accountName) throws AccountException;

	public LoginState canLogin(String name, String password);

	public RegisterState canRegisterAccount(PlayerAccount account);

	public PlayerAccount readAccount(String accountName) throws AccountException;

	public void updateAccount(String accountName, AccountUpdate update);

}
