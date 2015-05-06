package com.starbattle.accounts.manager.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;

public class SqlUpdateStatement extends SqlStatement {
	
	// UPDATE account SET password = ? WHERE name = ?
	
	
	public SqlUpdateStatement() {
		statement = "Update ";
	}

	@Override
	public ResultSet execute(DatabaseControl databaseControl) throws SQLException {
		Connection connection = databaseControl.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(statement);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(1 + i, values[i]);
		}
		preparedStatement.execute();
		return preparedStatement.getResultSet();
	}
	
	
	
	public void update(Enum... fieldNames) throws SQLException
	{
		statement = "update ";
		int count=fieldNames.length;
		for(int i=0; i<count; i++)
		{
			statement+=getFieldName(fieldNames[i]);
			separate(i, count);
		}
	
	}

	public void update(Class<? extends Enum> table) throws SQLException {
		statement += "  " + getTableName(table);
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
	
	public void set(Enum... fieldNames) throws SQLException {

		int count = fieldNames.length;
		statement += " SET ";
		for (int i = 0; i < count; i++) {
			String field = getFieldName(fieldNames[i]);
			statement += field + " = ?";
			separate(i, count, "AND ");
		}
	}

	public void whereValue(Object... values) {
		this.values = values;
	}
	
	public void setValue(Object... values) {
		this.values = values;
	}
}
