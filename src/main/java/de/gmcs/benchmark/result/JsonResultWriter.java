package de.gmcs.benchmark.result;

import java.io.Writer;

public class JsonResultWriter extends AbstractResultWriter {

    private boolean printTaskGroupSeparator;
    private boolean printTaskSeparator;

    public JsonResultWriter(Writer writer) {
        super(writer);

    }

    @Override
    public void printWarmupStart() {
    }

    @Override
    public void printWarmupEnd() {
    }
    
	@Override
	public void printTaskGroupStart(String name) {
		if(printTaskGroupSeparator) {
		    write(",{\"name\":\"" + name + "\",\"tasks\":[");
		} else {
			write("{\"name\":\"" + name + "\",\"tasks\":[");
		}
		printTaskGroupSeparator = true;
	}

	@Override
	public void printTaskGroupEnd(String name) {
		write("]}");
        printTaskSeparator = false;
	}

    @Override
    public void printTaskStart(String name) {
    }

    @Override
    public void printTaskEnd(String name, long time) {
        if (printTaskSeparator) {
            write(",{\"name\":\"" + name + "\",\"time\":" + time + "}");
        } else {
            write("{\"name\":\"" + name + "\",\"time\":" + time + "}");
        }
        printTaskSeparator = true;
    }

    @Override
    public void printBenchmarkStart() {
        write("{\"taskGroups\":[");
    }

    @Override
    public void printBenchmarkEnd() {
        write("]}");
    }
}
