package de.gmcs.benchmark.result;

import java.io.Writer;

public class JsonResultWriter extends AbstractResultWriter {

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
    }

    @Override
    public void printBenchmarkStart() {
    }

    @Override
    public void printBenchmarkEnd() {
    }
}
