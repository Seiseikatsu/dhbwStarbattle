package com.starbattle.gameserver.game;

public enum Team {

	NO_TEAM(-1), BLUE_TEAM(0), RED_TEAM(1);

	private int id;

	Team(int id) {
		this.id = id;
	}

	public int getTeamId() {
		return id;
	}

	public boolean isSameTeam(Team team) {
		if (team == NO_TEAM) {
			return false;
		}
		return id == team.getTeamId();
	}

	public boolean isEnemyTeam(Team team) {
		if (team == NO_TEAM) {
			return true;
		}
		return id != team.getTeamId();
	}

}
