package de.gmcs.benchmark;

import java.util.function.Consumer;
import java.util.function.Function;

public class Task<T> {

    private String name;

    private Function<Integer, T> warmupDataSupplier;
    private Function<Integer, T> benchmarkDataSupplier;

    private Consumer<T> test;

    public Task(String name, Function<Integer, T> warmupDataSupplier, Function<Integer, T> benchmarkDataSupplier, Consumer<T> test) {
        this.name = name;
        this.warmupDataSupplier = warmupDataSupplier;
        this.benchmarkDataSupplier = benchmarkDataSupplier;
        this.test = test;
    }

    public String getName() {
        return name;
    }

    public T getWarmupData(Integer loopIndex) {
        return warmupDataSupplier.apply(loopIndex);
    }

    public T getBenchmarkData(Integer loopIndex) {
        return benchmarkDataSupplier.apply(loopIndex);
    }

    public void execute(T data) {
        test.accept(data);
    }
}
