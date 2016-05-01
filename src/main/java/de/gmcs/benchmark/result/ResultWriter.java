package de.gmcs.benchmark.result;

public interface ResultWriter {

    void printWarmupStart();

    void printWarmupEnd();
    
    void printTaskGroupStart(String name);
    
    void printTaskGroupEnd(String name);

    void printTaskStart(String name);

    void printTaskEnd(String name, String time);

    void printBenchmarkStart();

    void printBenchmarkEnd();
}
