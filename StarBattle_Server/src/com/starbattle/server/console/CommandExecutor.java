package com.starbattle.server.console;

import java.util.List;
import java.util.Set;

import com.starbattle.server.main.StarbattleServer;
import com.starbattle.server.modes.GameModes;
import com.starbattle.server.modes.PlayableGameMode;

public class CommandExecutor {

	private StarbattleServer server;
	private List<Parameter> parameters;

	public CommandExecutor(StarbattleServer server) {
		this.server = server;
	}

	public void execute(ConsoleCommands cmd, List<Parameter> parameters) {
		this.parameters = parameters;
		switch (cmd) {
		case STOP:
			// Stop server with given user information
			String stopString = "Sorry, the server has to shutdown for maintenance!";
			if (hasParameter()) {
				stopString = getParameter().getStringValue();
			}
			server.shutdown(stopString);

			break;
		case INFO:
			// shows info
			int player = server.getManager().getPlayerContainer().getNumberOfPlayers();
			int games = server.getManager().getGameManager().getNumberOfGames();
			System.out.println("Connected Players: " + player);
			System.out.println("Running Games: " + games);
			break;
		case HELP:
			String output = ">> HELP: Command Name - Description <<";
			for (ConsoleCommands cmds : ConsoleCommands.values()) {
				String name = cmds.name().toLowerCase();
				String des = cmds.getDescription();
				output += "\n" + name + " - " + des;
			}
			output += "\n>> HELP End << ";
			System.out.println(output);
			break;
		case CONNECTED:
			Set<String> playerSet = server.getManager().getPlayerContainer().getPlayer().keySet();
			if (playerSet.isEmpty()) {
				System.out.println("No players connected!");
			}
			for (String name : playerSet) {
				System.out.println("> " + name);
			}
			break;
		case MODETYPES:
			for (GameModes modes : GameModes.values()) {
				System.out.println(modes.name() + " (" + modes.getName() + ")");
			}
			break;
		case RELOADMODES:
			server.getManager().getGameManager().getGameModes().loadModes();
			break;
		case SAVEMODES:
			server.getManager().getGameManager().getGameModes().saveModes();
			break;
		case GAMEMODES:
			server.getManager().getGameManager().getGameModes().listModes();
			break;
		}
	}

	public Parameter getParameter() {
		return getParameter(0);
	}

	public Parameter getParameter(int nr) {
		return parameters.get(nr);
	}

	public boolean hasParameter() {
		return hasParameter(1);
	}

	public boolean hasParameter(int parameter) {
		return parameters.size() >= parameter;
	}

}
