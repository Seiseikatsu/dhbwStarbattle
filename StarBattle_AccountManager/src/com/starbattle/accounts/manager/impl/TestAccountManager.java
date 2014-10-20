package com.starbattle.accounts.manager.impl;

import java.sql.SQLException;
import java.util.List;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class TestAccountManager implements AccountManager{


	private PlayerAccount testAccount=new PlayerAccount();
	
	public TestAccountManager() {
		
		testAccount.setName("TimoTester");
		testAccount.setPassword("test123");
		
	}
	
	@Override
	public void registerAccount(PlayerAccount account) throws AccountException {	}

	@Override
	public LoginState canLogin(String name, String password) {
		// TODO Auto-generated method stub
		
		if(name.equals(testAccount.getName()))
		{
			if(password.equals(testAccount.getPassword()))
			{
				return LoginState.Login_Ok;
			}
			else				
			{
				return LoginState.Wrong_Password;
			}
		}
		else
		{
			return LoginState.Wrong_Username;
		}
	}

	@Override
	public RegisterState canRegisterAccount(PlayerAccount account) {
		// TODO Auto-generated method stub
		return RegisterState.Register_Ok;
	}

	@Override
	public PlayerAccount readAccount(String accountName) throws AccountException {
		// TODO Auto-generated method stub
		
		if(accountName.equals(testAccount.getName()))		
		{
			return testAccount;
		}
		
		throw new AccountException("Cant read unknown user!");
	}

	@Override
	public void updateAccount(String accountName, AccountUpdate update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(int id) throws AccountException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Integer> getItemList(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void newPassword(String accountName, String email)
			throws AccountException {
		// TODO Auto-generated method stub
		
	}

}
