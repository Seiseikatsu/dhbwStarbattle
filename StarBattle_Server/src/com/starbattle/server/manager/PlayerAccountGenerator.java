package com.starbattle.server.manager;

import com.starbattle.accounts.manager.PlayerAccount;
import com.starbattle.network.connection.objects.NP_Register;

public class PlayerAccountGenerator {

	public static PlayerAccount generateAccount(NP_Register registerObject) {
		PlayerAccount player = new PlayerAccount();

		player.setName(registerObject.accountName);
		player.setDisplayName(registerObject.displayName);
		player.setEmail(registerObject.email);
		player.setPassword(registerObject.password);

		return player;
	}

}
