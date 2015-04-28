package com.starbattle.accounts.manager.impl.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.DatabaseControl;

public interface SqlStatement {

	public ResultSet execute(DatabaseControl databaseControl) throws SQLException;
	
}
