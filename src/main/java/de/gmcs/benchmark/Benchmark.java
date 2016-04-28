package de.gmcs.benchmark;

import java.io.PrintStream;
import java.util.List;

import de.gmcs.benchmark.options.Options;

public class Benchmark<T> {
	
	private static final int LOOPSIZE = 50_000;
	private static final int WARMUP_LOOPSIZE = LOOPSIZE / 2;
	
	private Options options;

	public Benchmark(Options options) {
		this.options = options;
	}

	public void perform(List<Task<T>> tasks) {
		performWarmup(options.getPrintStream(), tasks);
		tasks.forEach(t -> performBenchmark(options.getPrintStream(), t));
	}
	
	private void performWarmup(PrintStream out, List<Task<T>> tasks) {
		out.println("Warmup is in progress");
		for(int i=0; i<WARMUP_LOOPSIZE; i++) {
			tasks.forEach(t -> t.perform(t.getData()));
		}
		out.println("Warmup is finished");
	}
	
	private void performBenchmark(PrintStream out, Task<T> task) {
		out.println("Benchmark [" + task.getName() + "] is running");
		T data = task.getData();
		long start = System.currentTimeMillis();
		for(int i=0; i<LOOPSIZE; i++) {
			task.perform(data);
		}
		long end = System.currentTimeMillis();
		out.println("Benchmark [" + task.getName() + "] is done: " + (end - start) + "ms");
	}
}
