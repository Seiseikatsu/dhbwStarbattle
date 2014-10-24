package com.starbattle.accounts.manager.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.mail.GeneratePassword;
import com.starbattle.accounts.mail.MailService;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.player.PlayerFriends;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.PasswordHasher;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerImpl implements AccountManager {

	private DatabaseConnection databaseConnection;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Connection conn;
	private String[] tables = {"PLAYER", "FRIENDS", "ACCOUNT"};

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
	@Override
	public void registerAccount(PlayerAccount account) throws AccountException {
		try {

			if (canRegisterAccount(account).equals(RegisterState.Register_Ok) && canRegisterPlayer(account).equals(RegisterState.Register_Ok)) {

				String sqlAccount = "INSERT INTO ACCOUNT (NAME, PASSWORD, EMAIL) VALUES ( ?, ?, ? )";
				String sqlPlayer = "INSERT INTO PLAYER (display_name, account_id) VALUES (?, ?)";

				stmt = databaseConnection.getConnection().prepareStatement(sqlAccount, PreparedStatement.RETURN_GENERATED_KEYS);

				stmt.setString(1, account.getName());
				stmt.setString(2, account.getPassword());
				stmt.setString(3, account.getEmail());
				stmt.execute();
				rs = stmt.getGeneratedKeys();

				int id = 0;
				if (rs.next()) {
					id = (int) rs.getInt(1);
				}

				stmt = databaseConnection.getConnection().prepareStatement(sqlPlayer);
				stmt.setString(1, account.getDisplayName());
				stmt.setInt(2, id);
				stmt.execute();
			}

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}


	public void deleteAccount(int id) throws AccountException {
		
		try {
		stmt = databaseConnection.getConnection().prepareStatement("SELECT player_id from player where account_id = ?");
		stmt.setInt(1, id);
		stmt.executeQuery();
		
		int j = 1;
		while(rs.next()){
			stmt = databaseConnection.getConnection().prepareStatement("DELETE INVENTAR WHERE player_id = ?");
			stmt.setInt(1, rs.getInt(j));
			stmt.executeQuery();
			j++;
		}
		
		for (int i = 0; i < tables.length; i++) {
			
				stmt = databaseConnection.getConnection().prepareStatement("DELETE " + tables[i]+ " WHERE account_id = ?");
				stmt.setInt(1, id);
				stmt.execute();
	
		}
		
		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
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

	public RegisterState canRegisterPlayer(PlayerAccount account){
		// display_name invalid
		

		try {
			stmt = conn.prepareStatement("SELECT count(*) FROM player WHERE display_name = ?");
			stmt.setString(1, account.getDisplayName());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));

			if (rs.getInt(1) > 0) { // Display_name already exists
				return RegisterState.Displayname_Exists;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return RegisterState.Register_Ok;
		
	}
	public RegisterState canRegisterAccount(PlayerAccount account) {	
		// accountName invalid

		try {
			stmt = conn.prepareStatement("SELECT count(*) FROM account WHERE name = ?");
			stmt.setString(1, account.getName());
			ResultSet rs = stmt.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));

			if (rs.getInt(1) > 0) { // User already exists
				System.out.println("user already exists");
				return RegisterState.Accountname_Exists;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return RegisterState.Register_Ok;
	}

	public PlayerAccount readAccount(String accountName) throws AccountException {
		PlayerAccount player = new PlayerAccount();
		
		try {
			stmt = conn.prepareStatement("SELECT account_id, name, email FROM account WHERE name = ?");
			stmt.setString(1, accountName);
			ResultSet rs = stmt.executeQuery();
			
			player.setEmail( rs.getString("email"));
			player.setName( rs.getString("accountName"));
			
			stmt = conn.prepareStatement("SELECT display_name, gold FROM player WHERE player_id = ?");
			stmt.setInt(1, rs.getInt("account_id"));
			
			player.setDisplayName("display_name");
			player.setGold(rs.getInt("gold"));
			
			return player;
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	public void updateAccount(String accountName, AccountUpdate update) {

	}
	
	public void tryResetPassword(String accountName, String email) throws AccountException{
		try {
			stmt = conn.prepareStatement("SELECT account_id FROM account WHERE name = ? AND email = ? ");
			stmt.setString(1, accountName);
			stmt.setString(2, email);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			
			if(rs.getInt(1) > 0){
				String password = GeneratePassword.generatePsw();
				
				stmt = conn.prepareStatement("UPDATE account SET password = ? WHERE name = ?"); 
				stmt.setString(1, PasswordHasher.hashPassword(password));
				stmt.setString(2, accountName);
				stmt.execute();
				
				MailService.sendMail(email, accountName, password);
			} else {
				System.out.println("email und passwort stimmen nicht �berein");
			}
			
			
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
		
	}
	
	public int getId(String name) throws AccountException{
		try {
			stmt = conn.prepareStatement("SELECT  account_id FROM account WHERE name = ?");
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
	public List<Integer> getItemList(int playerId) throws AccountException {
		List<Integer> items = new ArrayList<Integer>();
		try {
			stmt = conn.prepareStatement("SELECT  item_id FROM inventar WHERE name = ?");
			stmt.setInt(1, playerId);
			ResultSet rs = stmt.executeQuery();
			
			int i = 1;
			while(rs.next()){
				items.add(rs.getInt(i));
				i++;
			}
			
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
		return items;
	}
	
	public String getPassword(String accountName){

		try {
			stmt = conn.prepareStatement("SELECT password FROM account WHERE name = ?");
			stmt.setString(1, accountName);
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			return "false";
		}	
		
	}

	@Override
	public PlayerFriends getFriendRelations(String accountName) throws AccountException {
		try {
			stmt = databaseConnection.getConnection().prepareStatement("SELECT account_id from account where name = ?");
			stmt.setString(1, accountName);
			stmt.executeQuery();
			
			rs.getInt(1);
			
			for (int i = 0; i < tables.length; i++) {
				
					stmt = databaseConnection.getConnection().prepareStatement("DELETE " + tables[i]+ " WHERE account_id = ?");
					stmt.setInt(1, 2);
					stmt.execute();
		
			}
			
			} catch (SQLException e) {
				throw new AccountException("SQL Failure", e);
			}
		return null;
	}

	@Override
	public boolean newFriendRequest(String accountName, String friendDisplayname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleFriendRequest(String accountName,
			String friendDisplayname, boolean accept) {
		// TODO Auto-generated method stub
		
	}

}
