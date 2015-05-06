package com.starbattle.accounts.manager.impl.control;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountUpdate;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlCountStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;
import com.starbattle.accounts.player.PlayerAccount;
import com.starbattle.accounts.utils.Validations;
import com.starbattle.accounts.validation.RegisterState;

public class AccountDataManager extends DataController {

	
	public AccountDataManager(DatabaseControl databaseControl) {
		super(databaseControl);
	}


	public PlayerAccount readAccount(String accountName) throws AccountException {
		return null;
	}

	public RegisterState canRegisterAccount(PlayerAccount account) {
		if (!Validations.isAccountNameVaild(account.getName())) {
			return RegisterState.Accountname_Invalid;
		}
		if (!Validations.isPlayerNameValid(account.getDisplayName())) {
			return RegisterState.Displayname_Invalid;
		}

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


	public String getAccountName(String displayName) throws AccountException {
		return null;
	}
	
	
}
