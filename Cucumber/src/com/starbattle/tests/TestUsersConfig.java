package com.starbattle.tests;

import java.util.ArrayList;
import java.util.List;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.TestAccountManager;
import com.starbattle.accounts.validation.PasswordHasher;

public abstract class TestUsersConfig {

	private static List<UserConfig> configs = new ArrayList<UserConfig>();

	static {
		// init test users
		addTestUser("TimoTester", "SuperTimo", "Timotest#1");
		addTestUser("HansHelfer", "SuperHans", "Hanshelf#1");

	}

	private static void addTestUser(String accountName, String dispName, String password) {
		UserConfig c = new UserConfig();
		c.accountName = accountName;
		c.dispName = dispName;
		c.password = password;
		configs.add(c);
	}

	private static class UserConfig {
		public String dispName;
		public String accountName;
		public String password;
	}

	public static void createTestUsers(TestAccountManager testAccountManager) throws AccountException {
		for (UserConfig config : configs) {
			String pw = config.password;
			pw = PasswordHasher.hashPassword(pw);
			testAccountManager.addTestAccount(config.accountName, config.dispName, pw, "tester@starbattle.com");
		}
	}

	public static String getAccountName(int nr) {
		return configs.get(nr).accountName;
	}

	public static String getDisplayName(int nr) {
		return configs.get(nr).dispName;
	}

	public static String getPassword(int nr) {
		return configs.get(nr).password;
	}

}
