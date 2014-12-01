package test;

import java.sql.SQLException;

import javax.swing.plaf.SliderUI;

import com.starbattle.accounts.database.DatabaseConnection;

public class DBTest {

	
	
	public static void main(String[] args) {
		try {
			new DatabaseConnection();
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
