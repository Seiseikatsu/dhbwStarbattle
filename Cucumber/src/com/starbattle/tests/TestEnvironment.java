package com.starbattle.tests;

import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.TestAccountManager;
import com.starbattle.accounts.manager.impl.AccountManagerImpl;
import com.starbattle.accounts.manager.impl.TestAccountManagerImpl;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.server.main.StarbattleServer;

public class TestEnvironment {

	private ClientAutomate client;
	private ClientAutomate anotherClient;
	private StarbattleServer server;
	private AccountManager accountManager;
	private TestAccountManager testAccountManager;

	public TestEnvironment() {
		server = new StarbattleServer();
		ClientTestInterface.shutdownDelaySeconds = 1f;
		ClientTestInterface.stepDelay = 0.5f;
		client = ClientTestInterface.createNewTestClient();
		accountManager = server.getManager().getPlayerManager().getAccountManager();
		testAccountManager = new TestAccountManagerImpl((AccountManagerImpl) accountManager);

		initDB();
	}
	
	public void initDB()
	{
		try {
			testAccountManager.deleteDbValues();
			TestUsersConfig.createTestUsers(testAccountManager);
		} catch (AccountException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ClientAutomate openAnotherClient() {
		anotherClient = ClientTestInterface.createNewTestClient();
		return anotherClient;
	}

	public void closeAnotherClient() {
		anotherClient.shutdown();
	}

	public void tidyUp() {
		client.shutdown();
		client = ClientTestInterface.createNewTestClient();
	}

	public void close() {
		server.close();
		client.shutdown();
		if (anotherClient != null) {
			anotherClient.shutdown();
		}
	}

	public ClientAutomate getClient() {
		return client;
	}

	public ClientAutomate getAnotherClient() {
		return anotherClient;
	}

	public StarbattleServer getServer() {
		return server;
	}

	public AccountManager getAccountManager() {
		return accountManager;
	}

	public TestAccountManager getTestAccountManager() {
		return testAccountManager;
	}
}
