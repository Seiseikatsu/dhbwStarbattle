package com.starbattle.client.views.profile;

public class ProfileViewData {

	
	private String playerName;
	private int level,exp,minexp,maxexp;
	
	public ProfileViewData()
	{
		playerName="Dani Debug";
		level=0;
		minexp=0;
		exp=50;
		maxexp=100;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getExp() {
		return exp;
	}
	
	public int getMaxexp() {
		return maxexp;
	}
	
	public int getMinexp() {
		return minexp;
	}
}
