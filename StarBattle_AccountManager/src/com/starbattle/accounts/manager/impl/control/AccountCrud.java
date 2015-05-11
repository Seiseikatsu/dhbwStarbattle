package com.starbattle.accounts.manager.impl.control;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlDeleteStatement;
import com.starbattle.accounts.manager.impl.sql.SqlInsertStatement;
import com.starbattle.accounts.manager.impl.sql.SqlSelectStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.FriendTable;
import com.starbattle.accounts.manager.impl.tables.InventoryTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;
import com.starbattle.accounts.player.PlayerAccount;

public class AccountCrud extends DataController {

	public AccountCrud(DatabaseControl databaseControl) {
		super(databaseControl);
	}

	public void registerAccount(PlayerAccount account) throws AccountException {
		try {

			SqlInsertStatement insertAccount = new SqlInsertStatement();
			insertAccount.insert(AccountTable.class);
			insertAccount.into(AccountTable.NAME, AccountTable.PASSWORD, AccountTable.EMAIL);
			insertAccount.values(account.getName(), account.getPassword(), account.getEmail());
			ResultSet results = insertAccount.execute(databaseControl, true);

			int accountId = 0;
			if (results.next()) {
				accountId = results.getInt(1);
			}

			SqlInsertStatement insertPlayer = new SqlInsertStatement();
			insertPlayer.insert(PlayerTable.class);
			insertPlayer.into(PlayerTable.NAME, PlayerTable.ACCOUNT_ID);
			insertPlayer.values(account.getDisplayName(), accountId);
			insertPlayer.execute(databaseControl);

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}

	public void deleteAccount(String accountName) throws AccountException {
		try {
			SqlSelectStatement select = new SqlSelectStatement();
			select.select(PlayerTable.PLAYER_ID);
			select.from(PlayerTable.class);
			select.where(PlayerTable.ACCOUNT_ID);
			int accountID=getAccountIdForAccountname(accountName);
			select.values(accountID);
			ResultSet results = select.execute(databaseControl);

			int j = 1;
			while (results.next()) {

				SqlDeleteStatement deleteInventory = new SqlDeleteStatement();
				deleteInventory.from(InventoryTable.class);
				deleteInventory.where(InventoryTable.PLAYER_ID);
				deleteInventory.values(results.getInt(j));
				deleteInventory.execute(databaseControl);
				j++;
			}

			SqlDeleteStatement deletePlayer = new SqlDeleteStatement();
			deletePlayer.from(PlayerTable.class);
			deletePlayer.where(PlayerTable.ACCOUNT_ID);
			deletePlayer.values(accountID);
			deletePlayer.execute(databaseControl);
			
			SqlDeleteStatement deleteFriend = new SqlDeleteStatement();
			deleteFriend.from(FriendTable.class);
			deleteFriend.where(FriendTable.ACCOUNT_ID);
			deleteFriend.values(accountID);
			deleteFriend.execute(databaseControl);

			SqlDeleteStatement deleteAccount = new SqlDeleteStatement();
			deleteAccount.from(AccountTable.class);
			deleteAccount.where(AccountTable.ACCOUNT_ID);
			deleteAccount.values(accountID);
			deleteAccount.execute(databaseControl);

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}

	public void updateAccount(String accountName, AccountUpdate update) throws AccountException {

	}
}
