package cucumber.features;

import com.starbattle.client.testinterface.main.ClientTestInterface;
import com.starbattle.client.testinterface.tester.ClientAutomate;
import com.starbattle.client.views.login.LoginView;
import static org.junit.Assert.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {

	
	private ClientAutomate client;
	
	
	private void initClient()
	{
		client=ClientTestInterface.createNewTestClient();
	}
	
	@Given("^I am logged in as \"(.*?)\"$")
	public void i_am_logged_in_as(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click on button \"(.*?)\"$")
	public void i_click_on_button(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I type \"(.*?)\"$")
	public void i_type(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


	@Then("^List counter \"(.*?)\" increased by (\\d+)$")
	public void list_counter_increased_by(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I expand list \"(.*?)\"$")
	public void i_expand_list(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I click on \"(.*?)\" on list item \"(.*?)\"$")
	public void i_click_on_on_list_item(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^List counter \"(.*?)\" decreased by (\\d+)$")
	public void list_counter_decreased_by(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I am on the lobby view$")
	public void i_am_on_the_lobby_view() throws Throwable {
	   
	}

	@When("^I click on \"(.*?)\" in the chat \"(.*?)\"$")
	public void i_click_on_in_the_chat(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I see \"(.*?)\" in the chat \"(.*?)\"$")
	public void i_see_in_the_chat(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}



	@When("^I type name \"(.*?)\"$")
	public void i_type_name(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I type password \"(.*?)\"$")
	public void i_type_password(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am on the login view$")
	public void i_am_on_the_login_view() throws Throwable {
		
		initClient(); 
		
		assertEquals(true,client.isInView(LoginView.VIEW_ID));
	}

	@Then("^I receive an error message saying \"(.*?)\"$")
	public void i_receive_an_error_message_saying(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


	@When("^I type accname \"(.*?)\"$")
	public void i_type_accname(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I type disname \"(.*?)\"$")
	public void i_type_disname(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I type reppassword \"(.*?)\"$")
	public void i_type_reppassword(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I type email \"(.*?)\"$")
	public void i_type_email(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I am on the registration view$")
	public void i_am_on_the_registration_view() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
