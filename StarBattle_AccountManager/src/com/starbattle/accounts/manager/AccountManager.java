package com.starbattle.accounts.manager;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public interface AccountManager {

	public void registerAccount(PlayerAccount account) throws AccountException, SQLException, NoSuchAlgorithmException;

	public void deleteAccount(int id) throws AccountException, SQLException;

	public LoginState canLogin(String name, String password) throws SQLException, NoSuchAlgorithmException;

	public RegisterState canRegisterAccount(PlayerAccount account);

	public PlayerAccount readAccount(String accountName) throws AccountException;

	public void updateAccount(String accountName, AccountUpdate update);

}
