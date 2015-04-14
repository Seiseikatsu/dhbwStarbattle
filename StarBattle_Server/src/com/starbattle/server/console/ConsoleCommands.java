package com.starbattle.server.console;

public enum ConsoleCommands {

	HELP("Lists all commands with descriptions"),
	STOP("Stops all running games and the server. Optional parameter to send a message to connected players."),
	CONNECTED("Lists all connected players with their account name"),
	MODETYPES("Lists all GameMode-Types"),
	GAMEMODES("Lists currently available Gamemodes in the lobby"),
	ADDMODE("Adds a new GameMode-Setting to the Lobby with: GameMode, MapName, NumberOfPlayers"),
	REMOVEMODE("Removes the GameMode from the lobby with given ID"),
	INFO("Information about running games and connected players");
		
	private String description;
	
	private ConsoleCommands(String des) {
		description=des;
	}
	
	public String getDescription() {
		return description;
	}
}
