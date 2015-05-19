package org.dyndns.tdfpro.main;

public class Main {
	static Output out;
	static CommandParser in;

	public static void main(String[] args) {
		out = new Output(System.out);
		in = new CommandParser(System.in, out);
		in.sendLine("init file B2.txt de sv");
		in.listen();
	}

}
