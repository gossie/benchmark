package de.gmcs.benchmark.result;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.Writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleResultWriterTest {

    @InjectMocks
    private SimpleResultWriter subject;

    @Mock
    private Writer writer;

    @Test
    public void testPrintWarmupStart() throws Exception {
        subject.printWarmupStart();
        verify(writer).write("Warmup is in progress\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintWarmupStart_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("Warmup is in progress\n");
        subject.printWarmupStart();
    }

    @Test
    public void testPrintWarmupEnd() throws Exception {
        subject.printWarmupEnd();
        verify(writer).write("Warmup is finished\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintWarmupEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("Warmup is finished\n");
        subject.printWarmupEnd();
    }

	@Test
	public void testPrintTaskGroupStart() throws Exception {
		subject.printTaskGroupStart("task group");
        verify(writer).write("Task group [task group] is running\n");
	}

	@Test(expected=RuntimeException.class)
	public void testPrintTaskGroupStart_ioException() throws Exception {
		doThrow(IOException.class).when(writer).write("Task group [task group] is running\n");
		subject.printTaskGroupStart("task group");
	}

	@Test
	public void testPrintTaskGroupEnd() throws Exception {
		subject.printTaskGroupEnd("task group");
        verify(writer).write("Task group [task group] is finished\n");
	}
	
	@Test(expected=RuntimeException.class)
	public void testPrintTaskGroupEnd_ioException() throws Exception {
		doThrow(IOException.class).when(writer).write("Task group [task group] is finished\n");
		subject.printTaskGroupEnd("task group");
	}

    @Test
    public void testPrintTaskStart() throws Exception {
        subject.printTaskStart("task");
        verify(writer).write("Task [task] is running\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintTaskStart_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("Task [task] is running\n");
        subject.printTaskStart("task");
    }

    @Test
    public void testPrintTaskEnd() throws Exception {
        subject.printTaskEnd("task", "350ms");
        verify(writer).write("Task [task] is finished: 350ms\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintTaskEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("Task [task] is finished: 350ms\n");
        subject.printTaskEnd("task", "350ms");
    }

    @Test
    public void testPrintBenchmarkStart() throws Exception {
        subject.printBenchmarkStart();
        verify(writer).write("Benchmark is running\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkStart_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("Benchmark is running\n");
        subject.printBenchmarkStart();
    }

    @Test
    public void testPrintBenchmarkEnd() throws Exception {
        subject.printBenchmarkEnd();
        verify(writer).write("Benchmark is finished\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("Benchmark is finished\n");
        subject.printBenchmarkEnd();
    }
}
