package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;

public class Tip implements Command {

	@Override
	public boolean triggers(CommandParser cp, String nextLine) {
		return nextLine.equals("tip") || nextLine.trim().length() == 0;
	}

	@Override
	public void activate(CommandParser cp, String line) {
		if(cp.wp != null){
		cp.wp.tip();
		} else {
			System.err.println("no word practice currently");
		}
	}

}
