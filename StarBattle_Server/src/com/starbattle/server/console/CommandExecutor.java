package com.starbattle.server.console;

import java.util.List;

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
			if (hasParemeter()) {
				stopString = getParameter().getStringValue();
			}
			server.shutdown(stopString);

			break;
		case INFO:
			// shows info 
			int player=server.getManager().getPlayerContainer().getNumberOfPlayers();
			int games=server.getManager().getGameManager().getNumberOfGames();
			System.out.println("Connected Players: "+player);
			System.out.println("Running Games: "+games);
		break;
		}
	}

	public Parameter getParameter() {
		return getParameter(0);
	}

	public Parameter getParameter(int nr) {
		return parameters.get(nr);
	}

	public boolean hasParemeter() {
		return hasParameter(1);
	}

	public boolean hasParameter(int parameter) {
		return parameters.size() >= parameter;
	}

}
