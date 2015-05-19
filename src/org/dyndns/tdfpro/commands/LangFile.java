package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;
import org.dyndns.tdfpro.main.WordPractice;

public class LangFile implements Command {

	@Override
	public boolean triggers(CommandParser cp, String nextLine) {
		return nextLine.contains("init file") && nextLine.split(" ").length >= 5;
	}

	@Override
	public void activate(CommandParser cp, String nextLine) {
		String[] input = nextLine.split(" ");
		String[] langs = new String[input.length - 3];
		for (int i = 3; i < input.length; i++) {
			langs[i - 3] = input[i];
		}
		WordPractice wp = new WordPractice(cp, input[2], langs);
		if (wp.init()) {
			cp.wp = wp;
		}
		cp.setActivity("practice");

	}

}
