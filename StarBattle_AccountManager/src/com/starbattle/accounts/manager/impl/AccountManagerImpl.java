package com.starbattle.accounts.manager.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.mail.GeneratePassword;
import com.starbattle.accounts.mail.MailService;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerImpl implements AccountManager {

	private DatabaseConnection databaseConnection;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection conn;
	private String[] tables = {"ACCOUNT", "PLAYER", "INVENTAR"};

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

	public void registerAccount(PlayerAccount account) throws AccountException {
		try {

			if (canRegisterAccount(account).equals(RegisterState.Register_Ok)) {

				String sqlPlayer = "INSERT INTO PLAYER (display_name) VALUES (?)";
				String sqlAccount = "INSERT INTO ACCOUNT (NAME, PLAYER_ID, PASSWORD, EMAIL) VALUES ( ?, ?, ?, ? )";
				stmt = databaseConnection.getConnection().prepareStatement(sqlPlayer, PreparedStatement.RETURN_GENERATED_KEYS);

				stmt.setString(1, account.getName());
				stmt.execute();
				rs = stmt.getGeneratedKeys();

				int id = 0;
				if (rs.next()) {
					id = (int) rs.getInt(1);
				}

				stmt = databaseConnection.getConnection().prepareStatement(
						sqlAccount);
				stmt.setString(1, account.getName());
				stmt.setInt(2, id);
				stmt.setString(3, account.getPassword());
				stmt.setString(4, account.getEmail());
				stmt.execute();
			}

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}


	public void deleteAccount(int id) throws AccountException {
		
		for (int i = 0; i < tables.length; i++) {
			try {
				stmt = databaseConnection.getConnection().prepareStatement("DELETE " + tables[i]+ " WHERE PLAYER_ID = ?");
				stmt.setInt(1, id);
				stmt.execute();
	
			} catch (SQLException e) {
				throw new AccountException("SQL Failure", e);
			}
		}

	}

	public void deleteAccount(String name) throws AccountException {
		int id = getId(name);
		
		for (int i = 0; i < tables.length; i++) {
			try {
				stmt = databaseConnection.getConnection().prepareStatement("DELETE " + tables[i]+ " WHERE PLAYER_ID = ?");
				stmt.setInt(1, id);
				stmt.execute();
	
			} catch (SQLException e) {
				throw new AccountException("SQL Failure", e);
			}
		}

	}

	public LoginState canLogin(String name, String password) throws AccountException {
		try {
			stmt = databaseConnection.getConnection().prepareStatement("SELECT password, name FROM account WHERE name = ?");
			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) { // if there is no resultset, the uname is wrong
				if (rs.getString("password").equalsIgnoreCase(password)) {
					return LoginState.Login_Ok;
				} else {
					return LoginState.Wrong_Password;
				}
			} else {
				return LoginState.Wrong_Username;
			}

		} catch (SQLException e) {
			throw new AccountException("Error in SQL-Statement");
		}

	}

	public RegisterState canRegisterAccount(PlayerAccount account) {	

		try {
			stmt = conn.prepareStatement("SELECT count(*) FROM account WHERE name = ?");
			stmt.setString(1, account.getName());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));

			if (rs.getInt(1) > 0) { // User already exists
				System.out.println("user already exists");
				return RegisterState.Username_Exists;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return RegisterState.Register_Ok;
	}

	public PlayerAccount readAccount(String accountName) throws AccountException {
		PlayerAccount player = new PlayerAccount();
		
		try {
			stmt = conn.prepareStatement("SELECT player_id, name, email, gold FROM account WHERE name = ?");
			stmt.setString(1, accountName);
			ResultSet rs = stmt.executeQuery();
			
			player.setEmail( rs.getString("email"));
			player.setName( rs.getString("accountName"));
			player.setGold(  rs.getInt("gold"));
			
			stmt = conn.prepareStatement("SELECT display_name FROM player WHERE player_id = ?");
			stmt.setInt(1, rs.getInt("player_id"));
			
			player.setDisplayName("display_name");
			
			return player;
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	public void updateAccount(String accountName, AccountUpdate update) {

	}
	
	public void newPassword(){
		
	}
	
	
	public void newPassword(String accountName) throws AccountException{
		try {
			stmt = conn.prepareStatement("SELECT email FROM account WHERE name = ?");
			stmt.setString(1, accountName);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			String email = rs.getString(1);
			String password = GeneratePassword.generatePsw();
			
			stmt = conn.prepareStatement("UPDATE account SET password = ? WHERE name = ?"); 
			stmt.setString(1, password);
			stmt.setString(2, accountName);
			stmt.executeQuery();
			
			MailService.sendMail(email, accountName, password);
			
			
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
		
	}
	
	public int getId(String name) throws AccountException{
		try {
			stmt = conn.prepareStatement("SELECT  player_id FROM account WHERE name = ?");
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			System.out.println("getID() --> ID : " + rs.getInt(1));
			
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	@Override
	public List<Integer> getItemList(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

}
