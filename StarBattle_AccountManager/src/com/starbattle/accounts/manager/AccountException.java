package com.starbattle.accounts.manager;

public class AccountException extends Exception{

	public AccountException(String error) {
		super(error);
	}
	
	public AccountException(String error, Exception e) {		
		super(error);
		e.printStackTrace();
	}
	
}
