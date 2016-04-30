package de.gmcs.benchmark;

import java.util.List;

import de.gmcs.benchmark.options.Options;
import de.gmcs.benchmark.result.ResultWriter;
import de.gmcs.benchmark.time.StopWatch;

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
        tasks.forEach(t -> performBenchmark(resultWriter, options.getStopWatch(), t));

        resultWriter.printBenchmarkEnd();
    }

    private void performWarmup(ResultWriter resultWriter, List<Task<T>> tasks) {
        resultWriter.printWarmupStart();
        for (int i = 0; i < WARMUP_LOOPSIZE; i++) {
            tasks.forEach(t -> t.execute(t.getData()));
        }
        resultWriter.printWarmupEnd();
    }

    private void performBenchmark(ResultWriter resultWriter, StopWatch stopWatch, Task<T> task) {
        resultWriter.printTaskStart(task.getName());
        T data = task.getData();
        stopWatch.start();
        for (int i = 0; i < LOOPSIZE; i++) {
            task.execute(data);
        }
        long time = stopWatch.end();
        resultWriter.printTaskEnd(task.getName(), time);
    }
}
