package com.starbattle.accounts.validation;

public enum LoginState {

	Login_Ok("Login successful!"),Wrong_Password("Wrong Password!"), Wrong_Username("Wrong Username!");

	String text;

	LoginState(String text) {

		this.text = text;
	}

	public String getText() {
		return text;
	}
}
