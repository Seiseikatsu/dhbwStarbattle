package com.starbattle.client.connection.listener;

public interface NetworkRegistrationListener {

	
	public void registrationOk(String answerMessage);
	
	public void registrationFailed(String error);
	
}
