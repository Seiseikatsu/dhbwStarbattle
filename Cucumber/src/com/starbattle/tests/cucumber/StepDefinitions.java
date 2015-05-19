package com.starbattle.tests.cucumber;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import com.starbattle.accounts.manager.AccountException;
import com.starbattle.accounts.manager.AccountManager;
import com.starbattle.accounts.manager.impl.TestAccountManagerImpl;
import com.starbattle.accounts.validation.LoginState;
import com.starbattle.accounts.validation.RegisterState;
import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.client.views.lobby.LobbyView;
import com.starbattle.client.views.login.LoginView;
import com.starbattle.client.views.register.RegisterView;
import com.starbattle.network.connection.objects.NP_StartAnswer;
import com.starbattle.server.main.StarbattleServer;
import com.starbattle.server.manager.MainServerManager;
import com.starbattle.server.manager.PlayerManager;
import com.starbattle.tests.TestUsersConfig;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	private static ClientAutomate client;
	private static StarbattleServer server;
	private static boolean initServer=false;
		
	@cucumber.api.java.Before	
	public static void init()
	{
		if(initServer==false)
		{
		//start server
		server=new StarbattleServer();

		
		//init db with debug users
		MainServerManager manager = server.getManager();
		PlayerManager playerManager = manager.getPlayerManager();
		AccountManager accountManager =playerManager.getAccountManager();
		TestAccountManagerImpl testAccountManager = new TestAccountManagerImpl(accountManager);

		try {
			testAccountManager.deleteDbValues();
			TestUsersConfig.createTestUsers(testAccountManager);
		} catch (AccountException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
//		   try {
//			server.getManager().getPlayerManager().getAccountManager().deleteAccount("HansTester");
//		} catch (AccountException e) {
//			e.printStackTrace();
//		}
		   
		initServer=true;
		}
		//set simulation parameters
		ClientTestInterface.shutdownDelaySeconds=1f;
		ClientTestInterface.stepDelay=0.5f;
		//init default application
		client = ClientTestInterface.createNewTestClient();		
	}
	
	@cucumber.api.java.After
	public void tidyUp()
	{
		//shut down all applications from this test
		ClientTestInterface.shutdown();	
//		server.shutdown(null);
	}

	
	@Given("^I am on the login view$")
	public void i_am_on_the_login_view() throws Throwable {
		assertEquals(true, client.isInView(LoginView.VIEW_ID));
	}

	@Then("^I delete account \"(.*?)\"$")
	public void i_delete_account(String name) throws Throwable {
	   server.getManager().getPlayerManager().getAccountManager().deleteAccount(name);
	}

	@Then("^I am on the register view$")
	public void i_am_on_the_register_view() throws Throwable {
		assertEquals(true, client.isInView(RegisterView.VIEW_ID));
	}
	
	@When("^I type \"(.*?)\" in \"(.*?)\"$")
	public void i_type_in(String text, String field) throws Throwable {
		client.fillInTextfield(field, text);
	}

	@When("^I click on button \"(.*?)\"$")
	public void i_click_on_button(String buttonName) throws Throwable {
		client.clickButton(buttonName);
	}

	@Then("^I am on the lobby view$")
	public void i_am_on_the_lobby_view() throws Throwable {
		assertEquals(true, client.isInView(LobbyView.VIEW_ID));
	}


	@Then("^I receive an error message saying \"(.*?)\"$")
	public void i_receive_an_error_message_saying(String error) throws Throwable {
	
		NP_StartAnswer startUp = (NP_StartAnswer) client.waitForNetworkReceive(NP_StartAnswer.class);
		String message = startUp.answerMessage;
		assertEquals(false, startUp.openGame); //check if its errror
		switch (error) {
		case "Wrong Username":
			assertEquals(message, LoginState.Wrong_Username.getText());
			break;
		case "Wrong Password":
			assertEquals(message, LoginState.Wrong_Password.getText());
			break;
		case "User already logged in":
			assertEquals(message, PlayerManager.playerAlreadyLoginMessage);
			break;
		case "Existing Accountname":
			assertEquals(message, RegisterState.Accountname_Exists.getText());
			break;
		case "Existing Displayname":
			assertEquals(message, RegisterState.Displayname_Exists.getText());
			break;
		case "Invalid Accountname":
			assertEquals(message, RegisterState.Accountname_Invalid.getText());
			break;
		case "Invalid Displayname":
			assertEquals(message, RegisterState.Displayname_Invalid.getText());
			break;
		}
	}

	@Given("^another application is logged in with \"(.*?)\" and \"(.*?)\"$")
	public void another_application_is_logged_in_with_and(String name, String pw) throws Throwable {

		ClientAutomate anotherClient = ClientTestInterface.createNewTestClient();
		anotherClient.doLogin(name, pw);
		anotherClient.hideClientWindow(); //minimize second client window so we can see our target client
	}
	
	@Given("^I see an error message saying \"(.*?)\"$")
	public void passwordError(String error)throws Throwable{
		switch(error){
			case "Invalid Password":
				assertEquals(true, client.isPwError(RegisterView.pwInvalid));
				break;
			case "Passwords don't match":
				assertEquals(true, client.isPwError(RegisterView.pwDoNotMatch));
				break;
		}
	}
	
	@Given("^Given I am logged in as \"(.*?)\" with password \"(.*?)\"$")
	public void i_am_logged_in_as_with_password(String name, String pw) throws Throwable {
		client.doLogin(name, pw);
	}

}
