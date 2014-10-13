package com.starbattle.accounts.manager.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerImpl implements AccountManager {

	private DatabaseConnection databaseConnection;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection conn;

	public AccountManagerImpl() {
		try {
			databaseConnection = new DatabaseConnection();
			conn = databaseConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registerAccount(PlayerAccount account) throws AccountException, SQLException, NoSuchAlgorithmException {
		stmt = conn.prepareStatement("SELECT count(*) FROM account WHERE name = ?");
		stmt.setString(1, account.getName());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		if (rs.getInt(1) > 0) { // User already exists
			//RegisterState.Username_Exists;
			System.out.println("user already exists");
			return;
		}
		
		String sqlPlayer = "INSERT INTO PLAYER (display_name) VALUES (?)";
		String sqlAccount = "INSERT INTO ACCOUNT (NAME, PLAYER_ID, PASSWORD, EMAIL) VALUES ( ?, ?, ?, ? )";
		stmt = databaseConnection.getConnection().prepareStatement(sqlPlayer,
				PreparedStatement.RETURN_GENERATED_KEYS);

		stmt.setString(1, account.getName());
		stmt.execute();
		rs = stmt.getGeneratedKeys();

		int id = 0;
		if (rs.next()) {
			id = (int) rs.getInt(1);
		}

		stmt = databaseConnection.getConnection().prepareStatement(sqlAccount);
		stmt.setString(1, account.getName());
		stmt.setInt(2, id);
		stmt.setString(3, hashPassword(account.getPassword())); // TODO: hash password
		stmt.setString(4, account.getEmail());
		stmt.execute();

	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		String hash;
		MessageDigest digest = MessageDigest.getInstance("MD5");

		digest.update(password.getBytes(), 0, password.length());

		hash = new BigInteger(1, digest.digest()).toString(16);
		
		return hash;
	}

	public void deleteAccount(int id) throws AccountException, SQLException {
		String sql = "DELETE WHERE ID = ?";
		
		stmt = databaseConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();

	}

	public LoginState canLogin(String name, String password) throws SQLException, NoSuchAlgorithmException {
		stmt = databaseConnection.getConnection().prepareStatement("SELECT user FROM members WHERE user = ?");
		stmt.setString(1, name);

		ResultSet rs = stmt.executeQuery(); 
		

		if (rs.next() ) { //if there is no resultset, the uname is wrong
			if(rs.getString("password").equalsIgnoreCase(hashPassword(password))){
				return LoginState.Login_Ok;
			} else {
				return LoginState.Wrong_Password;
			}
		} else { 
			return LoginState.Wrong_Username;
		}

	}

	public RegisterState canRegisterAccount(PlayerAccount account) {

		return RegisterState.Register_Ok;
	}

	public PlayerAccount readAccount(String accountName)
			throws AccountException {

		return null;
	}

	public void updateAccount(String accountName, AccountUpdate update) {

	}



}
