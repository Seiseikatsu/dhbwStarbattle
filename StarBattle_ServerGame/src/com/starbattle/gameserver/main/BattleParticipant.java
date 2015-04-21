package com.starbattle.gameserver.main;

import com.starbattle.gameserver.game.Team;
import com.starbattle.network.server.PlayerConnection;

public class BattleParticipant {

	private PlayerConnection connection;
	private String accountName,displayName;
	private Team team;

	public BattleParticipant(PlayerConnection connection,String displayName, Team team) {
		this.connection = connection;
		this.team = team;
		this.displayName=displayName;
		this.accountName = connection.getAccountName();
	}

	public String getAccountName() {
		return accountName;
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public PlayerConnection getConnection() {
		return connection;
	}

	public Team getTeam() {
		return team;
	}
}
