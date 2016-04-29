package de.gmcs.benchmark.result;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.io.IOException;
import java.io.Writer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@Ignore
public class JsonResultWriterTest {

    @InjectMocks
    private JsonResultWriter subject;

    @Mock
    private Writer writer;

    @Test
    public void testPrintBenchmarkStart() throws Exception {
        subject.printBenchmarkStart();
        String html =
                "<html>\n<head>\n<title>Benchmark result</title>\n</head>\n<body>\n<table>\n<thead>\n<tr>\n<th>Task</th>\n<th>Time (ms)</th>\n</tr>\n</thead>\n<tbody>\n";

        verify(writer).write(html);
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkStart_ioException() throws Exception {
        String html =
                "<html>\n<head>\n<title>Benchmark result</title>\n</head>\n<body>\n<table>\n<thead>\n<tr>\n<th>Task</th>\n<th>Time (ms)</th>\n</tr>\n</thead>\n<tbody>\n";

        doThrow(IOException.class).when(writer).write(html);
        subject.printBenchmarkStart();
    }

    @Test
    public void testPrintBenchmarkEnd() throws Exception {
        subject.printBenchmarkEnd();
        verify(writer).write("</tbody>\n</table>\n</body>\n</html>\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("</tbody>\n</table>\n</body>\n</html>\n");
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
        subject.printTaskEnd("task", 350L);
        verify(writer).write("<tr>\n<td>task</td>\n<td>350</td>\n</tr>\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintTaskEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("<tr>\n<td>task</td>\n<td>350</td>\n</tr>\n");
        subject.printTaskEnd("task", 350L);
    }
}
