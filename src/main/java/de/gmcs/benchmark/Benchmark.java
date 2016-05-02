package de.gmcs.benchmark;

import java.util.List;

import de.gmcs.benchmark.options.Options;
import de.gmcs.benchmark.result.ResultWriter;
import de.gmcs.benchmark.time.StopWatch;

public class Benchmark<T> {

    private Options options;

    public Benchmark(Options options) {
        this.options = options;
    }

    public void perform(List<TaskGroup<T>> taskGroups) {
        ResultWriter resultWriter = options.getWriter();

        resultWriter.printBenchmarkStart();

        performWarmup(options.getWarmupLoopsize(), resultWriter, taskGroups);
        performBenchmark(options.getBenchmarkLoopsize(), taskGroups, resultWriter, options.getStopWatch());

        resultWriter.printBenchmarkEnd();
    }

    private void performBenchmark(int loopsize, List<TaskGroup<T>> taskGroups, ResultWriter resultWriter, StopWatch stopWatch) {
        taskGroups.forEach(t -> performTaskGroup(loopsize, resultWriter, stopWatch, t));
    }

    private void performWarmup(int loopsize, ResultWriter resultWriter, List<TaskGroup<T>> taskGroups) {
        resultWriter.printWarmupStart();
        for (int i = 0; i < loopsize; i++) {
            for (TaskGroup<T> taskGroup : taskGroups) {
                taskGroup.getTasks().forEach(t -> t.execute(t.getData()));
            }
        }
        resultWriter.printWarmupEnd();
    }

    private void performTaskGroup(int loopsize, ResultWriter resultWriter, StopWatch stopWatch, TaskGroup<T> taskGroup) {
        String taskGroupName = taskGroup.getName();

        resultWriter.printTaskGroupStart(taskGroupName);
        taskGroup.getTasks().stream().forEach(t -> performTask(loopsize, resultWriter, stopWatch, t, t.getData()));
        resultWriter.printTaskGroupEnd(taskGroupName);
    }

    private void performTask(int loopsize, ResultWriter resultWriter, StopWatch stopWatch, Task<T> task, T data) {
        String taskName = task.getName();

        resultWriter.printTaskStart(taskName);
        stopWatch.start();
        for (int i = 0; i < loopsize; i++) {
            task.execute(data);
        }
        String time = stopWatch.end();
        resultWriter.printTaskEnd(taskName, time);
    }
}
