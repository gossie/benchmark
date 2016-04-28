package de.gmcs.benchmark.options;

import java.io.PrintStream;

public class Options {
	
	private PrintStream out;

	protected Options(PrintStream out) {
		this.out = out;
	}
	
	public PrintStream getPrintStream() {
		return out;
	}
}
