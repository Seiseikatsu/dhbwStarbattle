package com.starbattle.accounts.manager.impl.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;

public abstract class SqlStatement {

	protected String statement="";
	protected Object[] values;

	public abstract ResultSet execute(DatabaseControl databaseControl) throws SQLException;

	protected String getTableName(Class<? extends Enum> tableEnum) throws SQLException {
		String tableName = null;
		if (tableEnum.equals(AccountTable.class)) {
			tableName = AccountTable.getTableName();
		} else if (tableEnum.equals(PlayerTable.class)) {
			tableName = PlayerTable.getTableName();
		}

		if (tableName == null) {
			throw new SQLException("Enum is no Table!");
		}
		return tableName;
	}

	protected String getFieldName(Enum tableField) throws SQLException {
		String tableName = null;
		if (tableField instanceof AccountTable) {
			tableName = ((AccountTable) tableField).getFieldName();
		} else if (tableField instanceof PlayerTable) {
			tableName = ((PlayerTable) tableField).getFieldName();
		}

		if (tableName == null) {
			throw new SQLException("Enum is no Field!");
		}
		return tableName;
	}

	public void print() {
		String output = statement;
		output += "   ...values (";
		for (Object value : values) {
			output += value.toString() + " ";

		}
		output += ")";
		System.out.println(output);

	}

	protected void separate(int index, int count) {
		separate(index, count, ", ");
	}

	protected void separate(int index, int count, String seperator) {
		if (index < count - 1) {
			statement += seperator;
		}
	}

}
