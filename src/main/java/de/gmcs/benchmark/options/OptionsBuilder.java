package de.gmcs.benchmark.options;

import java.io.PrintStream;

public class OptionsBuilder {
	
	private PrintStream out;
	
	private OptionsBuilder() {
		out = System.out;
	}
	
	public static OptionsBuilder newInstance() {
		return new OptionsBuilder();
	}
	
	public OptionsBuilder withPrintStream(PrintStream out) {
		this.out = out;
		return this;
	}
	
	public Options build() {
		Options options = new Options(out);
		return options;
	}
}
