package com.starbattle.accounts.utils;

public class Validations {

	public static boolean isAccountNameVaild(String name) {
		if(name.length() >= 5)
			return true;
			else
		return false;
	}

	public static boolean isPlayerNameValid(String displayName) {
		if(displayName.length() >= 3)
			return true;
			else
		return false;
	}

}
