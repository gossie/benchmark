package de.gmcs.benchmark.result;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.IOException;
import java.io.Writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JsonResultWriterTest {

    @InjectMocks
    private JsonResultWriter subject;

    @Mock
    private Writer writer;

    @Test
    public void testPrintBenchmarkStart() throws Exception {
        subject.printBenchmarkStart();
        verify(writer).write("{\"tasks\":[");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkStart_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("{\"tasks\":[");
        subject.printBenchmarkStart();
    }

    @Test
    public void testPrintBenchmarkEnd() throws Exception {
        subject.printBenchmarkEnd();
        verify(writer).write("]}");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("]}");
        subject.printBenchmarkEnd();
    }

    @Test
    public void testPrintWarmupStart() throws Exception {
        subject.printWarmupStart();
        verifyZeroInteractions(writer);
    }

    @Test
    public void testPrintWarmupEnd() throws Exception {
        subject.printWarmupEnd();
        verifyZeroInteractions(writer);
    }

    @Test
    public void testPrintTaskStart() throws Exception {
        subject.printTaskStart("task");
        verifyZeroInteractions(writer);
    }

    @Test
    public void testPrintTaskEnd() throws Exception {
        subject.printTaskEnd("task1", 351L);
        subject.printTaskEnd("task2", 352L);
        verify(writer).write("{\"name\":\"task1\",\"time\":351}");
        verify(writer).write(",{\"name\":\"task2\",\"time\":352}");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintTaskEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("{\"name\":\"task\",\"time\":350}");
        subject.printTaskEnd("task", 350L);
    }
}
