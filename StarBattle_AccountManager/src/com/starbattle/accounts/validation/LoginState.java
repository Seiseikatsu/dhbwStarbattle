package com.starbattle.accounts.validation;

public enum LoginState {

	Login_Ok("Login successful!"), Wrong_Username("This username doesnt exist!"), Wrong_Password("Wrong Password!");

	String text;

	LoginState(String text) {

		this.text = text;
	}

	public String getText() {
		return text;
	}
}
