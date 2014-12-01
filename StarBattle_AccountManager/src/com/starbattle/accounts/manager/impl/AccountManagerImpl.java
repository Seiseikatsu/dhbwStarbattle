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
import com.starbattle.accounts.player.FriendRelation;
import com.starbattle.accounts.player.FriendRelationState;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.player.PlayerFriends;
import com.starbattle.accounts.utils.Validations;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.PasswordHasher;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerImpl implements AccountManager {

	private DatabaseConnection databaseConnection;
	private PreparedStatement stmt;
	private Connection conn;
	private String[] tables = { "PLAYER", "FRIENDS", "ACCOUNT" };

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

			if (canRegisterAccount(account).equals(RegisterState.Register_Ok)
					&& canRegisterPlayer(account).equals(RegisterState.Register_Ok)) {

				String sqlAccount = "INSERT INTO ACCOUNT (NAME, PASSWORD, EMAIL) VALUES ( ?, ?, ? )";
				String sqlPlayer = "INSERT INTO PLAYER (display_name, account_id) VALUES (?, ?)";

				stmt = databaseConnection.getConnection().prepareStatement(sqlAccount,
						PreparedStatement.RETURN_GENERATED_KEYS);

				stmt.setString(1, account.getName());
				stmt.setString(2, account.getPassword());
				stmt.setString(3, account.getEmail());
				stmt.execute();
				ResultSet rs = stmt.getGeneratedKeys();

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
			stmt = databaseConnection.getConnection().prepareStatement(
					"SELECT player_id from player where account_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			int j = 1;
			while (rs.next()) {
				stmt = databaseConnection.getConnection().prepareStatement("DELETE INVENTAR WHERE player_id = ?");
				stmt.setInt(1, rs.getInt(j));
				stmt.execute();
				j++;
			}

			for (int i = 0; i < tables.length; i++) {
				stmt = databaseConnection.getConnection().prepareStatement(
						"DELETE " + tables[i] + " WHERE account_id = ?");
				stmt.setInt(1, id);
				stmt.execute();
			}

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}
	}

	public LoginState canLogin(String name, String password) throws AccountException {
		try {
			stmt = databaseConnection.getConnection().prepareStatement(
					"SELECT password, name FROM account WHERE name = ?");
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

	public RegisterState canRegisterPlayer(PlayerAccount account) {
		if (!Validations.isPlayerNameValid(account.getDisplayName())) {
			return RegisterState.Accountname_Invalid;
		}
		if (!Validations.isPlayerNameValid(account.getDisplayName())) {
			return RegisterState.Displayname_Invalid;
		}
		
		if (NamesEqual(account.getDisplayName(), account.getName()))
			return RegisterState.Names_equal;

		try {
			stmt = conn.prepareStatement("SELECT count(*) FROM player WHERE display_name = ?");
			stmt.setString(1, account.getDisplayName());
			ResultSet rs = stmt.executeQuery();
			rs.next();

			if (rs.getInt(1) > 0) { // Display_name already exists
				return RegisterState.Displayname_Exists;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return RegisterState.Register_Ok;

	}

	private boolean NamesEqual(String displayName, String accountName) {
		if (displayName.equalsIgnoreCase(accountName))
			return true;
		else
			return false;
	}

	public RegisterState canRegisterAccount(PlayerAccount account) {
		if (!Validations.isAccountNameVaild(account.getName())) {
			return RegisterState.Accountname_Invalid;
		}
		if (!Validations.isPlayerNameValid(account.getDisplayName())) {
			return RegisterState.Displayname_Invalid;
		}

		try {
			stmt = conn.prepareStatement("SELECT count(*) FROM account WHERE name = ?");
			stmt.setString(1, account.getName());
			ResultSet rs = stmt.executeQuery();
			rs.next();

			if (rs.getInt(1) > 0) { // User already exists
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

			player.setEmail(rs.getString("email"));
			player.setName(rs.getString("accountName"));

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

	public void tryResetPassword(String accountName, String email) throws AccountException {
		try {
			stmt = conn.prepareStatement("SELECT account_id FROM account WHERE name = ? AND email = ? ");
			stmt.setString(1, accountName);
			stmt.setString(2, email);
			ResultSet rs = stmt.executeQuery();

			rs.next();

			if (rs.getInt(1) > 0) {
				String password = GeneratePassword.generatePsw();

				stmt = conn.prepareStatement("UPDATE account SET password = ? WHERE name = ?");
				stmt.setString(1, PasswordHasher.hashPassword(password));
				stmt.setString(2, accountName);
				stmt.execute();

				MailService.sendMail(email, accountName, password);
			} else {
				System.out.println("email und passwort stimmen nicht überein");
			}

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
			while (rs.next()) {
				items.add(rs.getInt(i));
				i++;
			}

		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
		return items;
	}

	public String getPassword(String accountName) {

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

			PlayerFriends friends = new PlayerFriends();
			int accountId = getAccountIdForAccountname(accountName);

			stmt = databaseConnection.getConnection().prepareStatement(
					"Select account_id_friend, status FROM friends WHERE account_id = ?");
			stmt.setInt(1, accountId);
			ResultSet rsFriendRequester = stmt.executeQuery();

			stmt = databaseConnection.getConnection().prepareStatement(
					"Select account_id, status FROM friends WHERE account_id_friend = ?");
			stmt.setInt(1, accountId);
			ResultSet rsFriendAnswer = stmt.executeQuery();

			while (rsFriendRequester.next()) {
				int accountIDFriend = rsFriendRequester.getInt("account_id_friend");
				int status = rsFriendRequester.getInt("status");
				String accountNameFriend = getAccountNameForAccountID(accountIDFriend);
				String displayNameFriend = getDisplayNameForAccountID(accountIDFriend);

				if (status == 1) {
					// Request
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Request));
				} else {
					// Friends
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Friends));
				}
			}

			while (rsFriendAnswer.next()) {
				int accountIDFriend = rsFriendAnswer.getInt("account_id");
				int status = rsFriendAnswer.getInt("status");
				String accountNameFriend = getAccountNameForAccountID(accountIDFriend);
				String displayNameFriend = getDisplayNameForAccountID(accountIDFriend);

				if (status == 1) {
					// Pending
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Pending));
				} else {
					// Friends
					friends.addRelation(new FriendRelation(accountNameFriend, displayNameFriend,
							FriendRelationState.Friends));
				}
			}
			return friends;
		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}
	}

	private String getAccountNameForAccountID(int accountId) throws AccountException {
		try {
			stmt = databaseConnection.getConnection()
					.prepareStatement("SELECT name from account where account_id = ? ");
			stmt.setInt(1, accountId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	@Override
	public boolean newFriendRequest(String accountName, String friendDisplayname) throws AccountException {
		int accountId = getAccountIdForAccountname(accountName);
		int accountIdFriend = 0;
		try {
			accountIdFriend = getAccountIdForDisplayname(friendDisplayname);
		} catch (Exception e) {
			return false; // no friend for display name found
		}

		try {
			stmt = databaseConnection.getConnection().prepareStatement(
					"INSERT INTO FRIENDS (account_id, account_id_friend, status) VALUES ( ?, ?, ? )");
			stmt.setInt(1, accountId);
			stmt.setInt(2, accountIdFriend);
			stmt.setInt(3, 1);
			stmt.execute();

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}
		return true;
	}

	@Override
	public String handleFriendRequest(String accountName, String displayNameFriend, boolean accept)
			throws AccountException {
		String accountNameFriend = null;
		try {
			int accountId = getAccountIdForAccountname(accountName);
			System.out.println("accountId : " + accountId + ", accountName: " + accountName);
			
			int accountIdFriend = getAccountIdForDisplayname(displayNameFriend);
			System.out.println("accountId : " + accountIdFriend + ", accountName: " + accountNameFriend);
			if (accept) {
				stmt = databaseConnection.getConnection().prepareStatement(
						"UPDATE friends SET status = ? WHERE account_id = ? AND account_id_friend = ?");
				System.out.println("UPDATE friends SET status = " + 2 + " WHERE account_id = " + accountIdFriend + " AND account_id_friend = " + accountId);
				stmt.setInt(1, 2);
				stmt.setInt(2, accountIdFriend);
				stmt.setInt(3, accountId);
				//stmt.executeUpdate();
				System.out.println(stmt.executeUpdate());
			} else {
				stmt = databaseConnection.getConnection().prepareStatement(
						"DELETE FROM friends where account_id = ? AND account_id_friend = ?");
				stmt.setInt(1, accountIdFriend);
				stmt.setInt(2, accountId);
				stmt.execute();
			}
			
			databaseConnection.getConnection().commit();
			accountNameFriend = getAccountNameForDisplayname(displayNameFriend);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}

		return accountNameFriend;
	}

	private int getAccountIdForAccountname(String accountName) throws AccountException {
		try {
			stmt = databaseConnection.getConnection()
					.prepareStatement("SELECT account_id from account where name = ? ");
			stmt.setString(1, accountName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}

	}

	private int getAccountIdForDisplayname(String displayName) throws AccountException {
		try {
			stmt = databaseConnection.getConnection().prepareStatement(
					"SELECT account_id from player where display_name = ? ");
			stmt.setString(1, displayName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	private String getDisplayNameForAccountID(int accountID) throws AccountException {
		try {
			stmt = databaseConnection.getConnection().prepareStatement(
					"SELECT display_name  from player where account_id = ? ");
			stmt.setInt(1, accountID);
			;
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	private String getDisplayNameForAccountName(String accountName) throws AccountException {
		try {
			stmt = databaseConnection
					.getConnection()
					.prepareStatement(
							"SELECT display_name from player, account where account.account_id = player.account_id AND account.name= ? ");
			stmt.setString(1, accountName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	private String getAccountNameForDisplayname(String displayName) throws AccountException {
		try {
			stmt = databaseConnection
					.getConnection()
					.prepareStatement(
							"SELECT name from account, player where account.account_id = player.account_id AND player.display_name = ? ");
			stmt.setString(1, displayName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) {
			throw new AccountException("SQL error");
		}
	}

	@Override
	public String getDisplayName(String accountName) throws AccountException {
		return getDisplayNameForAccountName(accountName);
	}

	@Override
	public String getAccountName(String displayName) throws AccountException {
		return getAccountNameForDisplayname(displayName);
	}
}
