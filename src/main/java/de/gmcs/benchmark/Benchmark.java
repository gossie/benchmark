package de.gmcs.benchmark;

import java.util.List;

import de.gmcs.benchmark.options.Options;
import de.gmcs.benchmark.result.ResultWriter;

public class Benchmark<T> {

    private static final int LOOPSIZE = 50_000;
    private static final int WARMUP_LOOPSIZE = LOOPSIZE / 2;

    private Options options;

    public Benchmark(Options options) {
        this.options = options;
    }

    public void perform(List<Task<T>> tasks) {
        ResultWriter resultWriter = options.getWriter();

        resultWriter.printBenchmarkStart();

        performWarmup(resultWriter, tasks);
        tasks.forEach(t -> performBenchmark(resultWriter, t));

        resultWriter.printBenchmarkEnd();
    }

    private void performWarmup(ResultWriter resultWriter, List<Task<T>> tasks) {
        resultWriter.printWarmupStart();
        for (int i = 0; i < WARMUP_LOOPSIZE; i++) {
            tasks.forEach(t -> t.execute(t.getData()));
        }
        resultWriter.printWarmupEnd();
    }

    private void performBenchmark(ResultWriter resultWriter, Task<T> task) {
        resultWriter.printTaskStart(task.getName());
        T data = task.getData();
        long start = System.currentTimeMillis();
        for (int i = 0; i < LOOPSIZE; i++) {
            task.execute(data);
        }
        long end = System.currentTimeMillis();
        resultWriter.printTaskEnd(task.getName(), end - start);
    }
}
