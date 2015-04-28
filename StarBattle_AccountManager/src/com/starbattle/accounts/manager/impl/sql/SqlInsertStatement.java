package com.starbattle.accounts.manager.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.tables.AccountTable;

public class SqlInsertStatement implements SqlStatement{

	private String statement="";
	private Object[] values;
	
	public static void main(String[] args) throws SQLException {
		SqlInsertStatement insertAccount = new SqlInsertStatement();
		insertAccount.insert(AccountTable.getTableName());
		insertAccount.into(AccountTable.NAME.getFieldName(), AccountTable.PASSWORD.getFieldName(),
				AccountTable.EMAIL.getFieldName());
		insertAccount.values("Hans","passwort","hans@web.de");
		insertAccount.print();
	}

	public SqlInsertStatement() {
	}

	// String sqlPlayer =
	// "INSERT INTO PLAYER (display_name, account_id) VALUES (?, ?)";

	public void insert(String tableName) {
		statement += "INSERT INTO " + tableName.toUpperCase();
	}

	public void into(String... fieldNames) {
		statement += " (";
		int count = fieldNames.length;
		for (int i = 0; i < count; i++) {
			String name = fieldNames[i];
			statement += name;
			if (i < count - 1) {
				statement += ", ";
			}
		}
		statement += ") ";
	}

	public void values(Object... values) throws SQLException {
		int count = values.length;
		statement += "VALUES (";
		for (int i = 0; i < count; i++) {
			statement += "?";
			if (i < count - 1) {
				statement += ", ";
			}
		}
		statement += ")";
		this.values = values;
	}
	
	public void print()
	{
		System.out.println(statement);
	}

	public ResultSet execute(DatabaseControl databaseControl) throws SQLException {
		Connection connection = databaseControl.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(statement,PreparedStatement.RETURN_GENERATED_KEYS);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i+1, values[i]);
		}
		preparedStatement.execute();
		return preparedStatement.getResultSet();
	}

}
