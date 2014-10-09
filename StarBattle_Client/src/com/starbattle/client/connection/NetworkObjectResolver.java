package com.starbattle.client.connection;

import com.starbattle.network.connection.objects.NP_Login;
import com.starbattle.network.connection.objects.NP_StartAnswer;

public class NetworkObjectResolver {

	private RegistrationListener registrationListener;
	
	public NetworkObjectResolver()
	{
		
	}
	
	public void setRegistrationListener(RegistrationListener registrationListener) {
		this.registrationListener = registrationListener;
	}
	
	public void income(Object object)
	{
		if(object instanceof NP_StartAnswer)
		{
			NP_StartAnswer answer=(NP_StartAnswer)object;
			if(answer.openGame)
			{
				registrationListener.registrationOk();
			}
			else
			{
				registrationListener.registrationFailed(answer.errorMessage);
			}
		}
	}
	
	
}
