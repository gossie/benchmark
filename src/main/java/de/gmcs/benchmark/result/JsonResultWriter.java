package de.gmcs.benchmark.result;

import java.io.Writer;

public class JsonResultWriter extends AbstractResultWriter {

    private boolean printSeparator;

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
    public void printTaskStart(String name) {
    }

    @Override
    public void printTaskEnd(String name, long time) {
        if (printSeparator) {
            write(",{\"name\":\"" + name + "\",\"time\":" + time + "}");
        } else {
            write("{\"name\":\"" + name + "\",\"time\":" + time + "}");
        }
        printSeparator = true;
    }

    @Override
    public void printBenchmarkStart() {
        write("{\"tasks\":[");
    }

    @Override
    public void printBenchmarkEnd() {
        write("]}");
    }
}
