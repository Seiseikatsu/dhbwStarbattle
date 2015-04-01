package com.starbattle.server.console;

import java.util.List;
import java.util.Scanner;

import com.starbattle.server.main.StarbattleServer;

public class ServerConsoleControl {

	private boolean closeServer = false;
	private CommandExecutor commandExecutor;
	private CommandParser commandParser = new CommandParser();

	public ServerConsoleControl(StarbattleServer starbattleServer) {

		commandExecutor = new CommandExecutor(starbattleServer);
		// Thread to read from the console input
		Thread readThread = new Thread(new ReadThread());
		readThread.start();
	}

	private void parseCommand(String input) {
		// parse input into command and parameters
		try {
			commandParser.parse(input);
			ConsoleCommands command = commandParser.getCommand();
			List<Parameter> parameters = commandParser.getParameters();
			commandExecutor.execute(command, parameters);
		} catch (SyntaxError e) {
			System.err.println("Syntax Error!");
		}
	}

	private class ReadThread implements Runnable {

		@Override
		public void run() {
			Scanner scanner = new Scanner(System.in);
			do {
				String text = scanner.nextLine();
				if (!text.isEmpty()) {
					parseCommand(text);
				}
			} while (!closeServer);
			scanner.close();
		}
	}

	public void close() {
		closeServer = true;
	}

}
