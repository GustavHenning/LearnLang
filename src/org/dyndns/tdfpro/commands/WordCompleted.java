package org.dyndns.tdfpro.commands;

import org.dyndns.tdfpro.main.CommandParser;
import org.dyndns.tdfpro.main.WordPractice;

public class WordCompleted implements Command {

	@Override
	public boolean triggers(CommandParser cp, String nextLine) {
		return nextLine.toLowerCase().trim().equals(cp.wp.currentWord().toLowerCase().trim()) || allSuggested(cp.wp);
	}

	private boolean allSuggested(WordPractice wp) {
		for(int i = 0; i < wp.rightGuessesByChar.length; i++){
			if(!wp.rightGuessesByChar[i])
				return false;
		}
		return true;
	}

	@Override
	public void activate(CommandParser cp, String line) {
		System.out.println("Correct: " + cp.wp.currentWord() + " < " + cp.wp.currentTranslation());
		cp.wp.nextWord();
	}

}
