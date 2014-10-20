package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.starbattle.accounts.database.DatabaseConnection;
import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.accounts.manager.impl.AccountManagerImpl;
import com.starbattle.accounts.validation.PasswordHasher;
import com.starbattle.accounts.validation.RegisterState;

public class SendNewPasswordTest {
	private static AccountManagerImpl ami;

	@Test
	public void testSending() throws AccountException {
		String name = "Geri";
		String email = "Gerry.Beery@gmail.com";
		ami = new AccountManagerImpl();
		ami.newPassword(name, email);
		
		String hashPswOld = PasswordHasher.hashPassword("RufeMichAn!:)2");
	
		assertFalse("password changed", ami.getPassword(name).equals(hashPswOld));
	}

}
