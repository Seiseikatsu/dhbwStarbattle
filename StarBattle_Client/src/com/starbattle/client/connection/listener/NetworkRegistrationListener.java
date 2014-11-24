package com.starbattle.client.connection.listener;

public interface NetworkRegistrationListener {

	
	public void registrationOk();
	
	public void registrationFailed(String error);
	
}
