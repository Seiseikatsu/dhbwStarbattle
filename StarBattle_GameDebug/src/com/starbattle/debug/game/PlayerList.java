package com.starbattle.debug.game;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {

	private List<String> accounts = new ArrayList<String>();
	private List<String> names = new ArrayList<String>();

	public PlayerList() {

		//add players
		addTestPlayer("TimoTester", "SuperTimo");
	}

	private void addTestPlayer(String accountName, String displayName) {
		accounts.add(accountName);
		names.add(displayName);
	}

	public int getCount()
	{
		return accounts.size();
	}
	
	public List<String> getAccounts() {
		return accounts;
	}

	public List<String> getNames() {
		return names;
	}

}
