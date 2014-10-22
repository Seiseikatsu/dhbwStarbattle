package com.starbattle.accounts.validation;

public enum RegisterState {

	Register_Ok("Registration successful!"), Accountname_Exists("Accountname is already existing!"), Accountname_Invalid(
			"Accountname is invalid!"), Displayname_Exists("Displayname is already existing!"), Displayname_Invalid(
			"Displayname is invalid!"), Names_equal("Accountname and Displayname equal!");

	String text;

	RegisterState(String text) {

		this.text = text;
	}

	public String getText() {
		return text;
	}
}
