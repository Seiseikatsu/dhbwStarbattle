package com.starbattle.client.model.validate;

public class PasswordChecker {

	public static boolean isValid(String password) {
		if(password.length() < 8)
			return false;
		char[] pass = password.toCharArray();
		return containsSpecialSign(pass) && containsLowerCase(pass) && 
			containsUpperCase(pass)	&& containsDigit(pass);
	}

	private static boolean containsSpecialSign(char[] pass) {
		for (char c : pass) {
			if ((c >= 32 && c <= 47) || (c >= 58 && c <= 64)
			 || (c >= 91 && c <= 96) || (c >= 123 && c <= 126))
				return true;
		}
		return false;
	}

	private static boolean containsLowerCase(char[] pass) {
		for (char c : pass) {
			if(Character.isLowerCase(c))
				return true;
		}
		return false;
	}

	private static boolean containsUpperCase(char[] pass) {
		for (char c : pass) {
			if(Character.isUpperCase(c))
				return true;
		}
		return false;
	}

	private static boolean containsDigit(char[] pass) {
		for (char c : pass) {
			if(Character.isDigit(c))
				return true;
		}
		return false;
	}

}
