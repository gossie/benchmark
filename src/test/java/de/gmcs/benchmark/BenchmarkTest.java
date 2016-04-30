package de.gmcs.benchmark;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.gmcs.benchmark.options.Options;
import de.gmcs.benchmark.result.SimpleResultWriter;
import de.gmcs.benchmark.time.MilliSecondStopWatch;

public class BenchmarkTest {

    private Benchmark<Object> subject;

    @Before
    public void setUp() {
        Options options = mock(Options.class);
        when(options.getWriter()).thenReturn(mock(SimpleResultWriter.class));
        when(options.getStopWatch()).thenReturn(mock(MilliSecondStopWatch.class));
        when(options.getBenchmarkLoopsize()).thenReturn(100);
        when(options.getWarmupLoopsize()).thenReturn(50);

        subject = new Benchmark<>(options);
    }

    @Test
    public void testPerform() throws Exception {
        Object data1 = new Object();
        Object data2 = new Object();
        Object data3 = new Object();
        Object data4 = new Object();
        

        Task<Object> task1 = createTask("task1", data1);
        Task<Object> task2 = createTask("task2", data2);
        Task<Object> task3 = createTask("task3", data3);
        Task<Object> task4 = createTask("task4", data4);
        
        TaskGroup<Object> taskGroup1 = createTaskGroup("group1", asList(task1, task2));
        TaskGroup<Object> taskGroup2 = createTaskGroup("group2", asList(task3, task4));


        subject.perform(asList(taskGroup1, taskGroup2));

        verify(task1, times(150)).execute(data1);
        verify(task2, times(150)).execute(data2);
        verify(task3, times(150)).execute(data3);
        verify(task4, times(150)).execute(data4);
    }
    
    @SuppressWarnings("unchecked")
    private TaskGroup<Object> createTaskGroup(String name, List<Task<Object>> tasks) {
    	TaskGroup<Object> taskGroup = mock(TaskGroup.class);
        when(taskGroup.getName()).thenReturn(name);
        when(taskGroup.getTasks()).thenReturn(tasks);
        
        return taskGroup;
    }

    @SuppressWarnings("unchecked")
    private Task<Object> createTask(String name, Object data) {
        Task<Object> task = mock(Task.class);
        when(task.getName()).thenReturn(name);
        when(task.getData()).thenReturn(data);

        return task;
    }
}
