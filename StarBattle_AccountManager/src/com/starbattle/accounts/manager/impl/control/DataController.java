package com.starbattle.accounts.manager.impl.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.sql.SqlSelectStatement;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;

public abstract class DataController {

    protected DatabaseControl databaseControl;

    public DataController(DatabaseControl databaseControl) {
        this.databaseControl = databaseControl;
    }

    public void close() {
        this.databaseControl.close();
    };

    protected String getDisplayNameForAccountID(int accountID) throws AccountException {
        SqlSelectStatement select = new SqlSelectStatement();
        try {
            select.select(PlayerTable.NAME);
            select.from(PlayerTable.class);
            select.where(PlayerTable.ACCOUNT_ID);
            select.values(accountID);
            ResultSet rs = select.execute(databaseControl);
            rs.next();
            return rs.getNString(1);
        } catch (SQLException e) {
            throw new AccountException("Failed to get Displayname for AccountId.");
        }
    }

    protected String getDisplayNameForAccountName(String accountName) throws AccountException {
        try {
            PreparedStatement stmt = databaseControl.getConnection().prepareStatement(
                    "SELECT display_name from player, account where account.account_id = player.account_id AND account.name= ? ");
            stmt.setString(1, accountName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new AccountException("Failed to get Displayname for Accountname.");
        }

    }

    protected String getAccountNameForDisplayname(String displayName) throws AccountException {
        try {
            PreparedStatement stmt = databaseControl.getConnection().prepareStatement(
                    "SELECT name from account, player where account.account_id = player.account_id AND player.display_name = ? ");
            stmt.setString(1, displayName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString(1);
        } catch (SQLException e) {
            throw new AccountException("SQL error");
        }
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
            throw new AccountException("Failed to get AccountId for Accountname.");
        }
    }

    protected int getAccountIdForDisplayname(String displayName) throws AccountException {
        SqlSelectStatement select = new SqlSelectStatement();
        try {
            select.select(PlayerTable.ACCOUNT_ID);
            select.from(PlayerTable.class);
            select.where(PlayerTable.NAME);
            select.values(displayName);
            ResultSet rs = select.execute(databaseControl);
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new AccountException("Failed to get AccountId for Displayname.");
        }

    }

    protected String getAccountNameForAccountID(int accountId) throws AccountException {

        SqlSelectStatement select = new SqlSelectStatement();
        try {
            select.select(AccountTable.NAME);
            select.from(AccountTable.class);
            select.where(AccountTable.ACCOUNT_ID);
            select.values(accountId);
            ResultSet rs = select.execute(databaseControl);
            rs.next();
            return rs.getNString(1);
        } catch (SQLException e) {
            throw new AccountException("Failed to get AccountId for Accountname.");
        }
    }

}
