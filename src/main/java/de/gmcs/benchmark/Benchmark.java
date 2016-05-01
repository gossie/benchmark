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
        taskGroups.forEach(t -> performBenchmark(options.getBenchmarkLoopsize(), resultWriter, options.getStopWatch(), t));

        resultWriter.printBenchmarkEnd();
    }

    private void performWarmup(int loopsize, ResultWriter resultWriter, List<TaskGroup<T>> taskGroups) {
        resultWriter.printWarmupStart();
        for (int i = 0; i < loopsize; i++) {
        	for(TaskGroup<T> taskGroup : taskGroups) {
        		taskGroup.getTasks().forEach(t -> t.execute(t.getData()));
        	}
        }
        resultWriter.printWarmupEnd();
    }

    private void performBenchmark(int loopsize, ResultWriter resultWriter, StopWatch stopWatch, TaskGroup<T> taskGroup) {
    	resultWriter.printTaskGroupStart(taskGroup.getName());
        for(Task<T> task : taskGroup.getTasks()) {
        	T data = task.getData();
        	stopWatch.start();
	        for (int i = 0; i < loopsize; i++) {
	        	task.execute(data);
	        }
	        String time = stopWatch.end();
	        resultWriter.printTaskEnd(task.getName(), time);
        }
        resultWriter.printTaskGroupEnd(taskGroup.getName());
    }
}
