package de.gmcs.benchmark.result;

public interface ResultWriter {

    void printWarmupStart();

    void printWarmupEnd();

    void printTaskStart(String name);

    void printTaskEnd(String name, long time);

    void printBenchmarkStart();

    void printBenchmarkEnd();
}
