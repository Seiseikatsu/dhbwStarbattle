package com.starbattle.accounts.manager.impl.control;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.DatabaseControl;
import com.starbattle.accounts.manager.impl.tables.AccountTable;
import com.starbattle.accounts.manager.impl.tables.FriendTable;
import com.starbattle.accounts.manager.impl.tables.InventoryTable;
import com.starbattle.accounts.manager.impl.tables.PlayerTable;

public class ResetDBController extends DataController {
	private Class<? extends Enum>[] allTables;
	
	
	public ResetDBController(DatabaseControl databaseControl) {
		super(databaseControl);
		allTables = new Class[4];
	}
	
	public void resetDB() throws SQLException{
		Connection conn = databaseControl.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SET REFERENTIAL_INTEGRITY FALSE");
		stmt.executeUpdate();
		conn.commit();
		
		allTables[0] = PlayerTable.class;
		allTables[1] = FriendTable.class;
		allTables[2] = AccountTable.class;
		allTables[3] = InventoryTable.class;

		for (int i = 0; i < allTables.length; i++) {
			System.out.println(allTables[i]);
			String sqlTruncate;
			try {
				sqlTruncate = "TRUNCATE TABLE " + (String) allTables[i].getMethod("getTableName", null).invoke(null, null);
				stmt = conn.prepareStatement(sqlTruncate);
				stmt.execute();
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		conn.commit();

		String sqlReferentialTrue = "SET REFERENTIAL_INTEGRITY TRUE";
		stmt = conn.prepareStatement(sqlReferentialTrue);
		stmt.executeUpdate();
		conn.commit();
	}

}
