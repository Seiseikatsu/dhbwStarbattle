package com.starbattle.accounts.manager.impl.sql;

import java.sql.SQLException;

import com.starbattle.accounts.manager.impl.tables.AccountTable;

public class SqlStatementTest {

	
	public static void main(String[] args) throws SQLException {
		
		
		SqlCountStatement count=new SqlCountStatement();
		count.from(AccountTable.class);
		count.where(AccountTable.NAME);
		count.values("Hans");
		count.print();
		
		SqlInsertStatement insert=new SqlInsertStatement();
		insert.insert(AccountTable.class);
		insert.into(AccountTable.NAME,AccountTable.PASSWORD,AccountTable.EMAIL);
		insert.values("Hans","test","hans@gmx.de");
		insert.print();
		
		SqlSelectStatement select=new SqlSelectStatement();
		select.select(AccountTable.NAME,AccountTable.PASSWORD);
		select.from(AccountTable.class);
		select.where(AccountTable.EMAIL);
		select.values(12);
		select.print();
		
		SqlDeleteStatement delete=new SqlDeleteStatement();
		delete.from(AccountTable.class);
		delete.where(AccountTable.NAME);
		delete.values(12);
		delete.print();
	}
	
}
