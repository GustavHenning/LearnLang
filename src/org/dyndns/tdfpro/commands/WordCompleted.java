package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;

public class WordCompleted implements Command {

	@Override
	public boolean triggers(CommandParser cp, String nextLine) {
		return nextLine.toLowerCase().equals(cp.wp.currentWord().toLowerCase());
	}

	@Override
	public void activate(CommandParser cp, String line) {
		System.out.println("Correct: " + cp.wp.currentWord() + " < " + cp.wp.currentTranslation());
		cp.wp.nextWord();
	}

}
