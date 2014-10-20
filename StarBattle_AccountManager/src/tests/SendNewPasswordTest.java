package tests;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.impl.AccountManagerImpl;
import com.starbattle.accounts.validation.PasswordHasher;

public class SendNewPasswordTest {
	private static AccountManagerImpl ami;

	@Test
	public void testSending() throws AccountException {
		String name = "Geri";
		String email = "Gerry.Beery@gmail.com";
		ami = new AccountManagerImpl();
		ami.tryResetPassword(name, email);
		
		String hashPswOld = PasswordHasher.hashPassword("RufeMichAn!:)2");
	
		assertFalse("password changed", ami.getPassword(name).equals(hashPswOld));
	}

}
