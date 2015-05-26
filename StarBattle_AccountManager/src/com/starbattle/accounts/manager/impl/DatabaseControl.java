package com.starbattle.accounts.manager.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;

public class DatabaseControl {

    private DatabaseConnection databaseConnection;
    private PreparedStatement stmt;
    private String[] tables = { "PLAYER", "FRIENDS", "ACCOUNT" };
    private String[] allTables = { "PLAYER", "FRIENDS", "ACCOUNT", "INVENTAR" };

    public DatabaseControl() throws AccountException {
        try {
            databaseConnection = new DatabaseConnection();
            return;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new AccountException("Can't open database connection!");
    }

    public String[] getAllTables() {
        return allTables;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    public Connection getConnection() {
        return databaseConnection.getConnection();
    }

    public void close() {

    }

}
