package com.starbattle.client.views.register.validate;

public class PasswordChecker {

	private PasswordChecker() {
	}

	public static boolean isValid(String password) {
		if (password.length() < 8)
			return false;
		char[] pass = password.toCharArray();
		return containsSpecialSign(pass) && containsLowerCase(pass) && containsUpperCase(pass) && containsDigit(pass);
	}

	private static boolean containsSpecialSign(char[] pass) {
		for (char c : pass) {
			if (checkSpecialSign(c))
				return true;
		}
		return false;
	}

	private static boolean checkSpecialSign(char c) {
		return AsciiChecker.checkAscii32To47(c) || AsciiChecker.checkAscii58To64(c) || AsciiChecker.checkAscii91To96(c) || AsciiChecker.checkAscii123To126(c);
	}

	private static boolean containsLowerCase(char[] pass) {
		for (char c : pass) {
			if (Character.isLowerCase(c))
				return true;
		}
		return false;
	}

	private static boolean containsUpperCase(char[] pass) {
		for (char c : pass) {
			if (Character.isUpperCase(c))
				return true;
		}
		return false;
	}

	private static boolean containsDigit(char[] pass) {
		for (char c : pass) {
			if (Character.isDigit(c))
				return true;
		}
		return false;
	}

}
