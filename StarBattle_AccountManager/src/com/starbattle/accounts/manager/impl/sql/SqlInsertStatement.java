package com.starbattle.accounts.manager.impl.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;

public class SqlInsertStatement extends SqlStatement {

	public SqlInsertStatement() {

	}

	public void insert(Class<? extends Enum> table) throws SQLException {
		statement += "INSERT INTO " + getTableName(table);
	}

	public void into(Enum... fieldNames) throws SQLException {
		statement += " (";
		int count = fieldNames.length;
		for (int i = 0; i < count; i++) {
			String name = getFieldName(fieldNames[i]);
			statement += name;
			separate(i, count);
		}
		statement += ") ";
	}

	public void values(Object... values) throws SQLException {
		int count = values.length;
		statement += "VALUES (";
		for (int i = 0; i < count; i++) {
			statement += "?";
			separate(i, count);
		}
		statement += ")";
		this.values = values;
	}

	public ResultSet execute(DatabaseControl databaseControl) throws SQLException {
		Connection connection = databaseControl.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(statement,
				PreparedStatement.RETURN_GENERATED_KEYS);
		for (int i = 0; i < values.length; i++) {
			preparedStatement.setObject(i + 1, values[i]);
		}
		preparedStatement.execute();
		return preparedStatement.getResultSet();
	}

}
