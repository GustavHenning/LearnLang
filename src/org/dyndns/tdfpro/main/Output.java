package org.dyndns.tdfpro.main;

import java.io.PrintStream;

public class Output {

	private PrintStream out;

	public Output(PrintStream out) {
		this.out = out;
	}
	
	public void send(String text){
		out.println(text);
	}
	
}
