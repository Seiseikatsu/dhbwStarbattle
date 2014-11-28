package com.starbattle.client.player;

public class PlayerValues {

	
	private static String playerDisplayName;

	public static void setPlayerDisplayName(String playerDisplayName) {
		PlayerValues.playerDisplayName = playerDisplayName;
	}

	
	public static String getPlayerDisplayName() {
		return playerDisplayName;
	}
	
}
