package org.dyndns.tdfpro.main;

import java.io.InputStream;
import java.util.Scanner;

import org.dyndns.tdfpro.commands.Command;
import org.dyndns.tdfpro.commands.Pass;
import org.dyndns.tdfpro.commands.Tip;
import org.dyndns.tdfpro.commands.LangFile;
import org.dyndns.tdfpro.commands.WordCompleted;

public class CommandParser {

	private String activity;
	public WordPractice wp;
	Output out;
	

	private InputStream in;
	private Command[] commands = {
			new LangFile(), new Tip(), new Pass(), new WordCompleted()
	};

	public CommandParser(InputStream in, Output out) {
		this.in = in;
		this.out = out;
	}

	public void sendLine(String line){
		parse(line);
	}
	
	public void listen(){
		Scanner sc = new Scanner(in);
		while(sc.hasNextLine()){
			parse(sc.nextLine());
		}
	}

	private void parse(String nextLine) {
		for(Command command : commands){
			if(command.triggers(this, nextLine)){
				command.activate(this, nextLine);
			}
		}
	}
	
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
}
