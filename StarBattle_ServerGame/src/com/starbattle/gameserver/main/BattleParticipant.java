package com.starbattle.gameserver.main;

import com.starbattle.gameserver.game.Team;
import com.starbattle.network.server.PlayerConnection;

public class BattleParticipant {

	private PlayerConnection connection;
	private String accountName;
	private Team team;

	public BattleParticipant(PlayerConnection connection, Team team) {
		this.connection = connection;
		this.team = team;
		this.accountName = connection.getAccountName();
	}

	public String getAccountName() {
		return accountName;
	}

	public PlayerConnection getConnection() {
		return connection;
	}

	public Team getTeam() {
		return team;
	}
}
