package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;

public class Pass implements Command {

	@Override
	public boolean triggers(CommandParser cp, String nextLine) {
		return nextLine.equals("pass");
	}

	@Override
	public void activate(CommandParser cp, String line) {
		System.out.println("The word was : " + cp.wp.currentWord());
		cp.wp.nextWord();
	}

}
