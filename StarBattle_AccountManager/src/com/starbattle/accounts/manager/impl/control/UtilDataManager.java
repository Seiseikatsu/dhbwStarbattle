package com.starbattle.accounts.manager.impl.control;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.starbattle.accounts.mail.GeneratePassword;
import com.starbattle.accounts.mail.MailService;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlSelectStatement;
import com.starbattle.accounts.manager.impl.sql.SqlUpdateStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.InventoryTable;
import com.starbattle.accounts.validation.LoginState;

public class UtilDataManager extends DataController{

	public UtilDataManager(DatabaseControl databaseControl) {
		super(databaseControl);
	}

	public LoginState canLogin(String name, String password) throws AccountException {
		SqlSelectStatement select = new SqlSelectStatement();
		try {
			select.select(AccountTable.PASSWORD, AccountTable.NAME);
			select.from(AccountTable.class);
			select.where(AccountTable.NAME);
			select.values(name);
			ResultSet rs = select.execute(databaseControl);
			
			if (rs.next()) {
				if (rs.getString("password").equalsIgnoreCase(password)) {
					return LoginState.Login_Ok;
				} else {
					return LoginState.Wrong_Password;
				}
			}else {
				return LoginState.Wrong_Username;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountException("Can not Login.."); 
		}
	}

	
	public List<Integer> getItemList(int playerId) throws AccountException {
		List<Integer> items = new ArrayList<Integer>();
		SqlSelectStatement select = new SqlSelectStatement();
		
		try {
			select.select(InventoryTable.ITEM_ID);
			select.from(InventoryTable.class);
			select.where(InventoryTable.PLAYER_ID);
			select.values(playerId);
			ResultSet rs = select.execute(databaseControl);
			
			int i = 1;
			while (rs.next()) {
				items.add(rs.getInt(i));
				i++;
			}

		} catch (SQLException e) {
			throw new AccountException("Can't get Item List.. ");
		}
		return items;
	}

	public void tryResetPassword(String accountName, String email) throws AccountException {
		SqlSelectStatement select = new SqlSelectStatement();
		
		try {
			select.select(AccountTable.ACCOUNT_ID);
			select.from(AccountTable.class);
			select.where(AccountTable.NAME, AccountTable.EMAIL);
			select.values(accountName, email);
			ResultSet rs = select.execute(databaseControl);
			
			rs.next();
			
			
			if (rs.getInt(1) > 0) {
				String password = GeneratePassword.generatePsw();
				
				SqlUpdateStatement update = new SqlUpdateStatement();
				update.update(AccountTable.class);
				update.set(AccountTable.PASSWORD);
				update.setValue(password);
				update.where(AccountTable.NAME);
				update.whereValue(accountName);
				
				
				update.execute(databaseControl);

				MailService.sendMail(email, accountName, password);
			} else {
				System.out.println("email und passwort stimmen nicht überein");
			}

		} catch (SQLException e) {
			throw new AccountException("Can't reset Password... ");
		}


	}

	public String getDisplayName(String accountName) throws AccountException {
		return getDisplayNameForAccountName(accountName);
	}
	

}
