package com.starbattle.accounts.manager;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManager {

	private DatabaseConnection databaseConnection;
	private UpdateAccount accountUpdater;
	
	
	
	public AccountManager()
	{
		
	}
	
   public void registerAccount(PlayerAccount account) throws AccountException{
	   
   }
	
	public void deleteAccount(String accountName) throws AccountException{
		
	}
	
	public LoginState canLogin(String name, String password){
		
		return LoginState.Login_Ok;
	}
	
	public RegisterState canRegisterAccount(PlayerAccount account){
		
		return RegisterState.Register_Ok;
	}
	
	public PlayerAccount readAccount(String accountName) throws AccountException{
		
		return null;
	}
	
	
    public UpdateAccount getAccountUpdater() {
		return accountUpdater;
	}
	
}
