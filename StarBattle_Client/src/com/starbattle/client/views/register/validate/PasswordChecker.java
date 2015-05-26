package com.starbattle.client.views.register.validate;

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
			if (checkSpecialSign(c))
				return true;
		}
		return false;
	}
	
	private  static boolean checkSpecialSign(char c){
		return checkAscii32To47(c) || checkAscii58To64(c) || checkAscii91To96(c) || checkAscii123To126(c);
	}
	
	private  static boolean checkAscii32To47(char c){
		return (c >= 32 && c <= 47);
	}
	
	private  static boolean checkAscii58To64(char c){
		return (c >= 58 && c <= 64);
	}
	
	private  static boolean checkAscii91To96(char c){
		return (c >= 91 && c <= 96);
	}
	
	private  static boolean checkAscii123To126(char c){
		return (c >= 123 && c <= 126);
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
