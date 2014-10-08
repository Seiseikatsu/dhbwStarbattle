package com.starbattle.accounts.manager;

public class PlayerAccount {

	private String name;
	private String password;
	private String email;
	private int gold;
	
	public PlayerAccount()
	{
		
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getGold() {
		return gold;
	}
	
}
