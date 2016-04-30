package de.gmcs.benchmark.result;

import java.io.Writer;

public class SimpleResultWriter extends AbstractResultWriter {

    public SimpleResultWriter(Writer writer) {
        super(writer);
    }

    @Override
    public void printWarmupStart() {
        write("Warmup is in progress\n");
    }

    @Override
    public void printWarmupEnd() {
        write("Warmup is finished\n");
    }

	@Override
	public void printTaskGroupStart(String name) {
		write("Task group [" + name + "] is running\n");
	}

	@Override
	public void printTaskGroupEnd(String name) {
		write("Task group [" + name + "] is finished\n");
	}

    @Override
    public void printTaskStart(String name) {
        write("Task [" + name + "] is running\n");
    }

    @Override
    public void printTaskEnd(String name, long time) {
        write("Task [" + name + "] is finished: " + time + "ms\n");
    }

    @Override
    public void printBenchmarkStart() {
        write("Benchmark is running\n");
    }

    @Override
    public void printBenchmarkEnd() {
        write("Benchmark is finished\n");
    }
}
