package com.starbattle.gameserver.player;

import com.starbattle.gameserver.game.Team;
import com.starbattle.gameserver.game.action.Damage;

public class PlayerAttributes {

	private String playerName;	
	private Team team=Team.NO_TEAM;
	private Health health;
	private int points;
	private int playerID;
	private boolean carriesFlag;
	private float weaponAngle;
	
	public PlayerAttributes() {
		health=new Health(100);
	}
	
	public void takeDamge(Damage damage) {
		if (!health.isDead()) {
			health.takeDamage(damage);
		}
	}
	

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public boolean isCarriesFlag() {
		return carriesFlag;
	}

	public void setCarriesFlag(boolean carriesFlag) {
		this.carriesFlag = carriesFlag;
	}

	public float getWeaponAngle() {
		return weaponAngle;
	}

	public void setWeaponAngle(float weaponAngle) {
		this.weaponAngle = weaponAngle;
	}
	
	
}
