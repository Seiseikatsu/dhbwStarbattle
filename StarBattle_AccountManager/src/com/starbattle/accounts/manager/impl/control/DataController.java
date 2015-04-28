package com.starbattle.accounts.manager.impl.control;


import com.starbattle.accounts.manager.impl.DatabaseControl;

public abstract class DataController {

	protected DatabaseControl databaseControl;
	
	public DataController(DatabaseControl databaseControl){
		this.databaseControl=databaseControl;
	}
	
	public void close(){
		
	};
	
}
