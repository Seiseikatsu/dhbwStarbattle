package com.starbattle.server.modes;

import javax.xml.bind.annotation.XmlEnum;

import com.starbattle.network.connection.objects.NP_EnterMatchQueue;

@XmlEnum
public enum GameModes {

	TEAMDEATHMATCH("Team Deathmatch", true, "testmap", 4, 6, 8), DEATHMATCH("Deathmatch", false, "testmap", 3, 5, 7), CAPTURETHEFLAG(
			"Capture the Flag", true, "testmap", 4, 6, 8),
			DEBUGMODE("Debug Mode",false,"testmap",1,1,1);

	private String name;
	private String defaultMap;
	private boolean isTeamMode;

	private int cMin, cMean, cMax;

	private GameModes(String name, boolean isTeamMode, String defaultMap, int minCount, int defaultCount, int maxCount) {
		this.name = name;
		this.isTeamMode = isTeamMode;
		this.defaultMap = defaultMap;

		this.cMin = minCount;
		this.cMax = maxCount;
		this.cMean = defaultCount;
	}

	public String getName() {
		return name;
	}

	public boolean isTeamMode() {
		return isTeamMode;
	}

	public String getDefaultMap() {
		return defaultMap;
	}

	public int getPlayerCount(int searchMode) {
		switch (searchMode) {
		case NP_EnterMatchQueue.MODE_FAST:
			return cMin;
		case NP_EnterMatchQueue.MODE_LONG:
			return cMax;
		}
		return cMean;
	}
}
