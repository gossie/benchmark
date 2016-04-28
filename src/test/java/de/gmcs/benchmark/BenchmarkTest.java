package de.gmcs.benchmark;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.gmcs.benchmark.options.Options;

public class BenchmarkTest {
	
	private Benchmark<Object> subject;

	@Before
	public void setUp() {
		Options options = mock(Options.class);
		when(options.getPrintStream()).thenReturn(System.out);
		
		subject = new Benchmark<>(options);
	}
	
	@Test
	public void testPerform() throws Exception {
		Task<Object> task1 = createTask("task1");
		Task<Object> task2 = createTask("task2");
		
		List<Task<Object>> tasks = new ArrayList<>();
		tasks.add(task1);
		tasks.add(task2);
		
		subject.perform(tasks);
		
		verify(task1, times(75_000)).perform();
		verify(task2, times(75_000)).perform();
	}

	
	@SuppressWarnings("unchecked")
	private Task<Object> createTask(String name) {
		Task<Object> task = mock(Task.class);
		when(task.getName()).thenReturn(name);
		
		return task;
	}
}
