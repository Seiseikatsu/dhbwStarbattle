package com.starbattle.accounts.validation;

public enum RegisterState {

	Register_Ok("Registration successful!"), Invalid_Password("Password is invalid!"), Username_Exists(
			"Username is already existing!");

	String text;

	RegisterState(String text) {

		this.text = text;
	}

	public String getText() {
		return text;
	}
}
