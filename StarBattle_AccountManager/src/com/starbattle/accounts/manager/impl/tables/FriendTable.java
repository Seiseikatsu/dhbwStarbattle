package com.starbattle.accounts.manager.impl.tables;

public enum FriendTable {
	
	ACCOUNT_ID("ACCOUNT_ID");
	
	private String fieldName;
	
	private FriendTable(String fieldName)
	{
		this.fieldName=fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public static String getTableName()
	{
		return "FRIENDS";
	}
}
