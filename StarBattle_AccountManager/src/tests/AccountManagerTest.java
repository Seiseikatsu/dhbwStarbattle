package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.accounts.manager.impl.AccountManagerImpl;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;

public class AccountManagerTest {

	static DatabaseConnection db;
	static private PreparedStatement stmt;
	static private ResultSet rs;
	static private Connection conn;
	static private PlayerAccount account1;
	static private PlayerAccount account2;
	static private PlayerAccount account3;
	static private PlayerAccount account4;
	private static AccountManagerImpl ami;

	@BeforeClass
	public static void setUpBeforeClass() {
		ami = new AccountManagerImpl();

		account1 = new PlayerAccount("Sebastian1", "RufeMichAn!:)2", "hallo@web.de");
		account2 = new PlayerAccount("Sebastian1", "RufeMichAn!:)2", "hallo@web.de");
		account3 = new PlayerAccount("Geraldine1", "RufeMichAn!:)2", "geri@web.de");
		account4 = new PlayerAccount("Roland1", "29RufeMichAn!:)293", "roland@web.de");

		assertTrue("Registration successful", ami.canRegisterAccount(account1).equals(RegisterState.Register_Ok));
		assertTrue("Registration successful", ami.canRegisterAccount(account3).equals(RegisterState.Register_Ok));
		assertTrue("Registration successful", ami.canRegisterAccount(account4).equals(RegisterState.Register_Ok));
		
		try {
			ami.registerAccount(account1);
			ami.registerAccount(account3);
			ami.registerAccount(account4);
		} catch (AccountException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void userAlreadyExists() {
		assertTrue("Registration not successful",ami.canRegisterAccount(account2).equals(RegisterState.Username_Exists));
	}

	@Test
	public void loginTest() throws AccountException {

		assertTrue(ami.canLogin("Geraldine1", "RufeMichAn!:)2").equals(LoginState.Login_Ok));
		assertTrue(ami.canLogin("Nene", "RufeMichAn!:)2").equals(LoginState.Wrong_Username));
		assertTrue(ami.canLogin("Geraldine1", "xxx").equals(LoginState.Wrong_Password));
	}

	 @AfterClass
	 public static void afterTest() throws AccountException{
	
	 ami.deleteAccount("Geraldine1");
	 ami.deleteAccount("Roland1");
	 ami.deleteAccount("Sebastian1");
	 }

}
