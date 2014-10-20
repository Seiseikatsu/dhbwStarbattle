package com.starbattle.accounts.manager;

import java.util.List;

import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public interface AccountManager {

	public void registerAccount(PlayerAccount account) throws AccountException;

	public void deleteAccount(int id) throws AccountException;

	public LoginState canLogin(String name, String password) throws AccountException;

	public RegisterState canRegisterAccount(PlayerAccount account);

	public PlayerAccount readAccount(String accountName) throws AccountException;

	public void updateAccount(String accountName, AccountUpdate update);
	
	public List<Integer> getItemList(String accountName);
	
	public void newPassword(String accountName) throws AccountException;

}
