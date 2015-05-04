package com.starbattle.accounts.manager.impl.control;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlSelectStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;

public abstract class DataController {

	protected DatabaseControl databaseControl;
	
	public DataController(DatabaseControl databaseControl){
		this.databaseControl=databaseControl;
	}
	
	public void close(){
		
	};
	
	protected String getDisplayNameForAccountID(int accountID) throws AccountException {
//		try {
//			stmt = databaseConnection.getConnection().prepareStatement(
//					"SELECT display_name  from player where account_id = ? ");
//			stmt.setInt(1, accountID);
//			;
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			return rs.getString(1);
//		} catch (SQLException e) {
//			throw new AccountException("SQL error");
//		}
		return "";
	}

	protected String getDisplayNameForAccountName(String accountName) throws AccountException {
//		try {
//			stmt = databaseConnection
//					.getConnection()
//					.prepareStatement(
//							"SELECT display_name from player, account where account.account_id = player.account_id AND account.name= ? ");
//			stmt.setString(1, accountName);
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			return rs.getString(1);
//		} catch (SQLException e) {
//			throw new AccountException("SQL error");
//		}
//	}
		return "";
	}
	
	protected int getAccountIdForAccountname(String accountName) throws AccountException {
		SqlSelectStatement select = new SqlSelectStatement();
		try {
			select.select(AccountTable.ACCOUNT_ID);
			select.from(AccountTable.class);
			select.where(AccountTable.NAME);
			select.values(accountName);
			ResultSet rs = select.execute(databaseControl);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccountException("Faild to get AccountId for Accountname."); 
		}
	}

	protected int getAccountIdForDisplayname(String displayName) throws AccountException {
//		try {
//			stmt = databaseConnection.getConnection().prepareStatement(
//					"SELECT account_id from player where display_name = ? ");
//			stmt.setString(1, displayName);
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			return rs.getInt(1);
//		} catch (SQLException e) {
//			throw new AccountException("SQL error");
//		}
		return 0;
	}
	protected String getAccountNameForAccountID(int accountId) throws AccountException {
//		try {
//			stmt = databaseConnection.getConnection()
//					.prepareStatement("SELECT name from account where account_id = ? ");
//			stmt.setInt(1, accountId);
//			ResultSet rs = stmt.executeQuery();
//			rs.next();
//			return rs.getString(1);
//		} catch (SQLException e) {
//			throw new AccountException("SQL error");
//		}
		return "";
	}

}
