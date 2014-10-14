package com.starbattle.accounts.manager;

public class AccountException extends Exception{

	public AccountException(String error) {
		super(error);
	}
	
	public AccountException(String error, Throwable t) {
		super(error, t);
	}
	
}
