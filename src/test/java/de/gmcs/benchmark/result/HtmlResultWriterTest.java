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
public class HtmlResultWriterTest {

    @InjectMocks
    private HtmlResultWriter subject;

    @Mock
    private Writer writer;

    @Test
    public void testPrintBenchmarkStart() throws Exception {
        subject.printBenchmarkStart();
        String html = "<html>\n<head>\n<title>Benchmark result</title>\n</head>\n<body>\n";

        verify(writer).write(html);
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkStart_ioException() throws Exception {
        String html = "<html>\n<head>\n<title>Benchmark result</title>\n</head>\n<body>\n";

        doThrow(IOException.class).when(writer).write(html);
        subject.printBenchmarkStart();
    }

    @Test
    public void testPrintBenchmarkEnd() throws Exception {
        subject.printBenchmarkEnd();
        verify(writer).write("</body>\n</html>\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintBenchmarkEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("</body>\n</html>\n");
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
	public void testPrintTaskGroupStart() throws Exception {
		subject.printTaskGroupStart("task group");
        verify(writer).write("<h2>task group</h2>\n<table>\n<thead>\n<tr>\n<th>Task</th>\n<th>Time (ms)</th>\n</tr>\n</thead>\n<tbody>\n");
	}

	@Test(expected=RuntimeException.class)
	public void testPrintTaskGroupStart_ioException() throws Exception {
		doThrow(IOException.class).when(writer).write("<h2>task group</h2>\n<table>\n<thead>\n<tr>\n<th>Task</th>\n<th>Time (ms)</th>\n</tr>\n</thead>\n<tbody>\n");
		subject.printTaskGroupStart("task group");
	}

	@Test
	public void testPrintTaskGroupEnd() throws Exception {
		subject.printTaskGroupEnd("task group");
        verify(writer).write("</tbody>\n</table>\n");
	}

	@Test(expected=RuntimeException.class)
	public void testPrintTaskGroupEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("</tbody>\n</table>\n");
        subject.printTaskGroupEnd("task group");
	}

    @Test
    public void testPrintTaskStart() throws Exception {
        subject.printTaskStart("task");
        verifyZeroInteractions(writer);
    }

    @Test
    public void testPrintTaskEnd() throws Exception {
        subject.printTaskEnd("task", "350ms");
        verify(writer).write("<tr>\n<td>task</td>\n<td>350ms</td>\n</tr>\n");
    }

    @Test(expected = RuntimeException.class)
    public void testPrintTaskEnd_ioException() throws Exception {
        doThrow(IOException.class).when(writer).write("<tr>\n<td>task</td>\n<td>350ms</td>\n</tr>\n");
        subject.printTaskEnd("task", "350ms");
    }
}
