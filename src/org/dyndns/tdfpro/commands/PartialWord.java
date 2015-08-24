package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;

public class PartialWord implements Command{

	@Override
	public boolean triggers(CommandParser cp, String nextLine) {
		return nextLine.length() > 0;
	}

	@Override
	public void activate(CommandParser cp, String line) {
		cp.wp.setPartials(line);
		
	}

}
