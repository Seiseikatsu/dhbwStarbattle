package com.starbattle.accounts.manager.impl.sql;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.InventoryTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;

public abstract class SqlStatement {

	protected String statement="";
	protected Object[] values;

	public abstract ResultSet execute(DatabaseControl databaseControl) throws SQLException;

	protected String getTableName(Class<? extends Enum> tableEnum) throws SQLException {
		try {
			return (String) tableEnum.getMethod("getTableName", null).invoke(null, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new SQLException("Enum is no Table!");
		}
	}

	protected String getFieldName(Enum tableField) throws SQLException {
		try {
			return (String) tableField.getClass().getMethod("getFieldName", null).invoke(tableField, null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new SQLException("Enum is no Table!");
		}
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
