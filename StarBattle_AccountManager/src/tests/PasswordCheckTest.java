package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

import com.starbattle.accounts.manager.impl.PasswordChecker;

public class PasswordCheckTest {

	@Test
	public void testValid() {
		assertTrue(PasswordChecker.isValid("RufeMichAn!:)2"));
	}
	@Test
	public void testNoNumber() {
		assertFalse(PasswordChecker.isValid("RufeMichAn!:)"));
	}
	@Test
	public void testNoSpecialChar() {
		assertFalse(PasswordChecker.isValid("RufeMichAn2"));
	}
	@Test
	public void testUpperCase() {
		assertFalse(PasswordChecker.isValid("rufemichan!:)2"));
	}
	@Test
	public void testLowerCase() {
		assertFalse(PasswordChecker.isValid("RUFEMICHAN!:)2"));
	}
	@Test
	public void testLength() {
		assertTrue(PasswordChecker.isValid("RufMic!2"));
		assertFalse(PasswordChecker.isValid("RufMi!2"));
	}
}
