package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;

public interface Command {

	boolean triggers(CommandParser cp, String nextLine);

	void activate(CommandParser cp, String line);

}
