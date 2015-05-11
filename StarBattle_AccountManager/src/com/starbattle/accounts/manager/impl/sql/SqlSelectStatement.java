package com.starbattle.accounts.manager.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;

public class SqlSelectStatement extends SqlStatement {

	//			"SELECT player_id from player where account_id = ?");
	
	public SqlSelectStatement() {
		statement="SELECT * ";
	}
	
	public void select(Enum... fieldNames) throws SQLException
	{
		statement = "SELECT ";
		int count=fieldNames.length;
		for(int i=0; i<count; i++)
		{
			statement+=getFieldName(fieldNames[i]);
			separate(i, count);
		}
	
	}

	public void from(Class<? extends Enum> table) throws SQLException {
		statement += " FROM " + getTableName(table);
	}

	public void where(Enum... fieldNames) throws SQLException {

		int count = fieldNames.length;
		statement += " WHERE ";
		for (int i = 0; i < count; i++) {
			String field = getFieldName(fieldNames[i]);
			statement += field + " = ?";
			separate(i, count, "AND ");
		}
	}

	public void values(Object... values) {
		this.values = values;
	}

	public ResultSet execute(DatabaseControl databaseControl) throws SQLException {
		Connection connection = databaseControl.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(1 + i, values[i]);
		}
		preparedStatement.executeQuery();
		return preparedStatement.getResultSet();
	}

}
