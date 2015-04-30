package com.starbattle.accounts.manager.impl.control;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlCountStatement;
import com.starbattle.accounts.manager.impl.sql.SqlDeleteStatement;
import com.starbattle.accounts.manager.impl.sql.SqlInsertStatement;
import com.starbattle.accounts.manager.impl.sql.SqlSelectStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.FriendTable;
import com.starbattle.accounts.manager.impl.tables.InventoryTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.utils.Validations;
import com.starbattle.accounts.validation.RegisterState;

public class AccountCrud {

	private DatabaseControl databaseControl;

	public AccountCrud(DatabaseControl databaseControl) {
		this.databaseControl = databaseControl;
	}

	public RegisterState canRegisterAccount(PlayerAccount account) {
		if (!Validations.isAccountNameVaild(account.getName())) {
			return RegisterState.Accountname_Invalid;
		}
		if (!Validations.isPlayerNameValid(account.getDisplayName())) {
			return RegisterState.Displayname_Invalid;
		}

		// TODO change it pls

		SqlCountStatement count = new SqlCountStatement();
		try {
			count.from(AccountTable.class);
			count.where(AccountTable.NAME);
			count.values(account.getName());
			ResultSet results = count.execute(databaseControl);

			if (results.getInt(1) > 0) {
				return RegisterState.Accountname_Exists;
			}

			count = new SqlCountStatement();
			count.from(PlayerTable.class);
			count.where(PlayerTable.NAME);
			count.values(account.getDisplayName());
			results = count.execute(databaseControl);

			if (results.getInt(1) > 0) {
				return RegisterState.Displayname_Exists;
			}

		} catch (SQLException e) {
			// TODO Add internal error register state
			e.printStackTrace();
		}

		return RegisterState.Register_Ok;
	}

	public void registerAccount(PlayerAccount account) throws AccountException {
		try {
			RegisterState registerState = canRegisterAccount(account);
			if (registerState == RegisterState.Register_Ok) {

				SqlInsertStatement insertAccount = new SqlInsertStatement();
				insertAccount.insert(AccountTable.class);
				insertAccount.into(AccountTable.NAME, AccountTable.PASSWORD, AccountTable.EMAIL);
				insertAccount.values(account.getName(), account.getPassword(), account.getEmail());
				ResultSet results = insertAccount.execute(databaseControl);

				int accountId = 0;
				if (results.next()) {
					accountId = results.getInt(1);
				}

				SqlInsertStatement insertPlayer = new SqlInsertStatement();
				insertPlayer.insert(PlayerTable.class);
				insertPlayer.into(PlayerTable.NAME, PlayerTable.ACCOUNT_ID);
				insertPlayer.values(account.getDisplayName(), accountId);
				insertPlayer.execute(databaseControl);
			}

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}

	public void deleteAccount(String accountName, int accountID) throws AccountException {
		try {
			SqlSelectStatement select=new SqlSelectStatement();
			select.select(PlayerTable.PLAYER_ID);
			select.from(PlayerTable.class);
			select.where(PlayerTable.ACCOUNT_ID);
			select.values(accountID);
			ResultSet results=select.execute(databaseControl);
			
			int j = 1;
			while (results.next()) {
				
				SqlDeleteStatement deleteInventory = new SqlDeleteStatement();
				deleteInventory.from(InventoryTable.class);
				deleteInventory.where(InventoryTable.PLAYER_ID);
				deleteInventory.values(results.getInt(j));
				deleteInventory.execute(databaseControl);
				j++;
			}
			
			SqlDeleteStatement delete = new SqlDeleteStatement();
			delete.from(PlayerTable.class);
			delete.where(PlayerTable.ACCOUNT_ID);
			delete.values(accountID);
			delete.execute(databaseControl);
				
			delete.from(FriendTable.class);
			delete.where(FriendTable.ACCOUNT_ID);
			delete.values(accountID);
			delete.execute(databaseControl);
			
			delete.from(AccountTable.class);
			delete.where(AccountTable.ACCOUNT_ID);
			delete.values(accountID);
			delete.execute(databaseControl);

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}

}
