package com.starbattle.accounts.manager.impl.control;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlInsertStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.validation.RegisterState;

public class AccountCrud {

	private DatabaseControl databaseControl;

	public AccountCrud(DatabaseControl databaseControl) {
		this.databaseControl = databaseControl;
	}

	public RegisterState canRegisterAccount(PlayerAccount account) {
		return null;
	}

	public void registerAccount(PlayerAccount account) throws AccountException {
		try {
			RegisterState registerState = canRegisterAccount(account);
			if (registerState == RegisterState.Register_Ok) {

				SqlInsertStatement insertAccount = new SqlInsertStatement();
				insertAccount.insert(AccountTable.getTableName());
				insertAccount.into(AccountTable.NAME.getFieldName(), AccountTable.PASSWORD.getFieldName(),
						AccountTable.EMAIL.getFieldName());
				insertAccount.values(account.getName(), account.getPassword(), account.getEmail());
				ResultSet results = insertAccount.execute(databaseControl);

				int accountId = 0;
				if (results.next()) {
					accountId = results.getInt(1);
				}

				SqlInsertStatement insertPlayer = new SqlInsertStatement();
				insertPlayer.insert(PlayerTable.getTableName());
				insertPlayer.into(PlayerTable.NAME.getFieldName(), PlayerTable.ACCOUNT_ID.getFieldName());
				insertPlayer.values(account.getDisplayName(), accountId);
				insertPlayer.execute(databaseControl);
			}

		} catch (SQLException e) {
			throw new AccountException("SQL Failure", e);
		}

	}

	public void deleteAccount(String accountName) throws AccountException {

	}

}
