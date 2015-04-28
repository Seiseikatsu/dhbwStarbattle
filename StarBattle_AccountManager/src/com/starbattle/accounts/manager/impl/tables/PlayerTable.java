package com.starbattle.accounts.manager.impl.tables;

public enum PlayerTable  {

	NAME("display_name"),
	ACCOUNT_ID("account_id");
	
	
	private String fieldName;
	
	private PlayerTable(String fieldName)
	{
		this.fieldName=fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public static String getTableName()
	{
		return "PLAYER";
	}
}
