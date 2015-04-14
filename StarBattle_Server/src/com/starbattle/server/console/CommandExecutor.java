package com.starbattle.server.console;

import java.util.List;
import java.util.Set;

import com.starbattle.server.game.GameModes;
import com.starbattle.server.game.PlayableGameMode;
import com.starbattle.server.main.StarbattleServer;

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
				System.out.println( modes.name() + " (" + modes.getName() + ")");
			}
			break;
		case GAMEMODES:
			System.out.println("ID / Mode / Map / Players");
			List<PlayableGameMode> modes = server.getManager().getGameManager().getGameModes().getModes();
			for (int i = 0; i < modes.size(); i++) {
				PlayableGameMode mode = modes.get(i);
				System.out.println(i + ": " + mode.getMode().name() + " " + mode.getMapFile() + " " + mode.getPlayer());
			}
			break;
		case ADDMODE:
			try {
				if (hasParameter(3)) {
					GameModes m = GameModes.valueOf(getParameter(0).getStringValue().toUpperCase());
					String map = getParameter(1).getStringValue();
					int players = getParameter(2).getIntValue();
					PlayableGameMode newmode = new PlayableGameMode(m, map, players);
					server.getManager().getGameManager().getGameModes().addMode(newmode);
					System.out.println("Mode Added!");
				}
			} catch (Exception e) {
			}
			break;
		case REMOVEMODE:
			try {
				if (hasParameter()) {
					int nr = getParameter().getIntValue();
					server.getManager().getGameManager().getGameModes().removeMode(nr);
				}
				System.out.println("Mode removed!");
			} catch (Exception e) {
				// TODO: handle exception
			}
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
