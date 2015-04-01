package com.starbattle.server.console;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

	private List<Parameter> parameters;
	private ConsoleCommands command;
	public final static String WHITESPACE = " ";
	public final static String PAREMTER_SEPERATION = ",";

	public CommandParser() {

	}

	public void parse(String text) throws SyntaxError {

		parameters = new ArrayList<Parameter>();
		command = null;

		SyntaxString syntaxString = new SyntaxString(text);
		// find command name
		for (ConsoleCommands cmd : ConsoleCommands.values()) {
			String cmdName = cmd.name().toLowerCase();
			if (syntaxString.startsWith(text)) {
				command = cmd;
				// skip
				syntaxString.skip(cmdName);
				// check for parameters
				while (syntaxString.hasMark(PAREMTER_SEPERATION)) {
					String parameter = syntaxString.cutAttribute(PAREMTER_SEPERATION);
					parameters.add(new Parameter(parameter));
					syntaxString.skip(PAREMTER_SEPERATION);
				}
				// check for last paremeter (if not none parameters)
				String parameter = syntaxString.cutAttribute();
				if (!parameter.isEmpty()) {
					parameters.add(new Parameter(parameter));
				}
				return;
			}
		}
		// unknown command
		throw new SyntaxError();
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public ConsoleCommands getCommand() {
		return command;
	}
}
